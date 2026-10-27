package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Course
import com.example.data.model.ExcuseClassification
import com.example.data.model.ScheduleSession
import com.example.data.model.StudentProfile
import com.example.data.repository.AttendanceRepository
import com.example.data.repository.NotificationRepository
import com.example.data.repository.StudentRepository
import com.example.data.repository.TimetableRepository
import com.example.ui.components.AppNavDestination
import com.example.util.AppLanguage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ScreenDestination {
    data object Welcome : ScreenDestination()
    data object Registration : ScreenDestination()
    data object Login : ScreenDestination()
    data object ForgotPassword : ScreenDestination()
    data object MainApp : ScreenDestination()
    data class ClassDetails(val courseCode: String) : ScreenDestination()
    data class CampusMap(val courseCode: String) : ScreenDestination()
    data class Verification(val sessionId: String) : ScreenDestination()
    data class InitialConfirmed(val sessionId: String) : ScreenDestination()
    data class UrgentCheckout(val sessionId: String) : ScreenDestination()
    data class FinalReceipt(val sessionId: String) : ScreenDestination()
    data object AttendanceHistory : ScreenDestination()
    data object MissingAttendanceWarning : ScreenDestination()
    data object AttendanceCorrectionRequest : ScreenDestination()
}

class AppViewModel : ViewModel() {

    val studentProfile: StateFlow<StudentProfile> = StudentRepository.studentProfile
    val sessions: StateFlow<List<ScheduleSession>> = TimetableRepository.sessions
    val courses: List<Course> = TimetableRepository.enrolledCourses
    val records = AttendanceRepository.records
    val notifications = NotificationRepository.notifications
    val senateWarnings = AttendanceRepository.senateWarnings

    private val _currentLanguage = MutableStateFlow(AppLanguage.EN)
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    private val _currentScreen = MutableStateFlow<ScreenDestination>(ScreenDestination.MainApp)
    val currentScreen: StateFlow<ScreenDestination> = _currentScreen.asStateFlow()

    private val _currentNavTab = MutableStateFlow(AppNavDestination.HOME)
    val currentNavTab: StateFlow<AppNavDestination> = _currentNavTab.asStateFlow()

    // Simulation states
    private val _simulatedDistance = MutableStateFlow(12.4)
    val simulatedDistance: StateFlow<Double> = _simulatedDistance.asStateFlow()

    private val _isInsideGeofence = MutableStateFlow(true)
    val isInsideGeofence: StateFlow<Boolean> = _isInsideGeofence.asStateFlow()

    private val _showBiometricDialog = MutableStateFlow(false)
    val showBiometricDialog: StateFlow<Boolean> = _showBiometricDialog.asStateFlow()

    private val _biometricVerified = MutableStateFlow(false)
    val biometricVerified: StateFlow<Boolean> = _biometricVerified.asStateFlow()

    private val _verificationStep = MutableStateFlow(5) // Step 5 of 6 (Identity)
    val verificationStep: StateFlow<Int> = _verificationStep.asStateFlow()

    private val _countdownMinutes = MutableStateFlow(6)
    val countdownMinutes: StateFlow<Int> = _countdownMinutes.asStateFlow()

    private val _countdownSeconds = MutableStateFlow(21)
    val countdownSeconds: StateFlow<Int> = _countdownSeconds.asStateFlow()

    init {
        // Run live ticking timer for countdown
        viewModelScope.launch {
            while (true) {
                delay(1000)
                if (_countdownSeconds.value > 0) {
                    _countdownSeconds.value = _countdownSeconds.value - 1
                } else if (_countdownMinutes.value > 0) {
                    _countdownMinutes.value = _countdownMinutes.value - 1
                    _countdownSeconds.value = 59
                }
            }
        }
    }

    fun toggleLanguage() {
        _currentLanguage.value = if (_currentLanguage.value == AppLanguage.EN) AppLanguage.AM else AppLanguage.EN
    }

    fun setLanguage(lang: AppLanguage) {
        _currentLanguage.value = lang
    }

    fun navigateTo(screen: ScreenDestination) {
        _currentScreen.value = screen
    }

    fun navigateToTab(tab: AppNavDestination) {
        _currentNavTab.value = tab
        _currentScreen.value = ScreenDestination.MainApp
    }

    fun navigateBack() {
        when (_currentScreen.value) {
            is ScreenDestination.ClassDetails,
            is ScreenDestination.CampusMap,
            is ScreenDestination.Verification,
            is ScreenDestination.InitialConfirmed,
            is ScreenDestination.UrgentCheckout,
            is ScreenDestination.FinalReceipt,
            is ScreenDestination.AttendanceHistory,
            is ScreenDestination.MissingAttendanceWarning,
            is ScreenDestination.AttendanceCorrectionRequest -> {
                _currentScreen.value = ScreenDestination.MainApp
            }
            is ScreenDestination.Registration,
            is ScreenDestination.Login,
            is ScreenDestination.ForgotPassword -> {
                _currentScreen.value = ScreenDestination.Welcome
            }
            ScreenDestination.Welcome,
            ScreenDestination.MainApp -> {
                // At root, stay
            }
        }
    }

    fun setSimulationMode(mode: String) {
        when (mode) {
            "INSIDE" -> {
                _simulatedDistance.value = 12.4
                _isInsideGeofence.value = true
            }
            "OUTSIDE" -> {
                _simulatedDistance.value = 85.0
                _isInsideGeofence.value = false
            }
            "WEAK_GPS" -> {
                _simulatedDistance.value = 24.8
                _isInsideGeofence.value = true
            }
            "ARRIVAL_0M" -> {
                _simulatedDistance.value = 0.0
                _isInsideGeofence.value = true
            }
        }
    }

    fun openBiometricPrompt() {
        _showBiometricDialog.value = true
    }

    fun dismissBiometricPrompt() {
        _showBiometricDialog.value = false
    }

    fun onBiometricSuccess(sessionId: String, isFinalCheckout: Boolean = false) {
        _showBiometricDialog.value = false
        _biometricVerified.value = true
        _verificationStep.value = 6

        if (isFinalCheckout) {
            TimetableRepository.markSessionFinalCheckIn(sessionId)
            AttendanceRepository.addBiTemporalLog(
                courseCode = "CoSc 4124: Distributed Systems",
                courseTitle = "Distributed Systems & Cloud Computing",
                location = "Tech Block B, Lab 204",
                initialTime = "08:34 AM",
                finalTime = "10:22 AM"
            )
            _currentScreen.value = ScreenDestination.FinalReceipt(sessionId)
        } else {
            TimetableRepository.markSessionInitialCheckIn(sessionId)
            _currentScreen.value = ScreenDestination.InitialConfirmed(sessionId)
        }
    }

    fun submitCorrectionAppeal(
        courseCode: String,
        sessionDate: String,
        excuseType: ExcuseClassification,
        explanation: String,
        fileName: String?
    ) {
        AttendanceRepository.submitAppeal(
            courseCode = courseCode,
            sessionDate = sessionDate,
            excuseType = excuseType,
            explanation = explanation,
            fileName = fileName
        )
        _currentScreen.value = ScreenDestination.AttendanceHistory
    }

    fun toggleBiometricSetting(enabled: Boolean) = StudentRepository.updateBiometricSetting(enabled)
    fun toggleGeofencePolling(enabled: Boolean) = StudentRepository.updateGeofencePolling(enabled)
    fun toggleCheckoutAlarm(enabled: Boolean) = StudentRepository.updateCheckoutAlarm(enabled)
    fun logout() {
        StudentRepository.logout()
        _currentScreen.value = ScreenDestination.Welcome
    }
}
