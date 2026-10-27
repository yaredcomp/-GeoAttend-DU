package com.example.data.repository

import com.example.data.model.StudentProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object StudentRepository {
    private val _studentProfile = MutableStateFlow(
        StudentProfile(
            studentId = "DU/UG/14298/14",
            fullName = "Yonas Bekele Tadesse",
            email = "yonas.bekele@du.edu.et",
            college = "College of Engineering & Technology (Odaya'a)",
            department = "Department of Computer Science & Software Engineering",
            program = "B.Sc. Software Engineering",
            yearOfStudy = 4,
            semester = "Semester II",
            academicYear = "2024/2025 (2016/2017 E.C.)",
            campus = "Odaya'a Main Campus",
            overallAttendanceRate = 94.2,
            totalSessionsAttended = 38,
            totalSessionsScheduled = 40,
            senateMinRequirement = 80.0,
            hardwareKnoxId = "DU-SEC-7F91-NODE",
            boundDeviceModel = "Samsung Galaxy S21 5G",
            nodeSignature = "DU-KEY-8199-ECDSA",
            isBiometricEnabled = true,
            backgroundGeofenceEnabled = true,
            checkoutAlarmsEnabled = true
        )
    )
    val studentProfile: StateFlow<StudentProfile> = _studentProfile.asStateFlow()

    private val _isUserLoggedIn = MutableStateFlow(true)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn.asStateFlow()

    fun updateBiometricSetting(enabled: Boolean) {
        _studentProfile.value = _studentProfile.value.copy(isBiometricEnabled = enabled)
    }

    fun updateGeofencePolling(enabled: Boolean) {
        _studentProfile.value = _studentProfile.value.copy(backgroundGeofenceEnabled = enabled)
    }

    fun updateCheckoutAlarm(enabled: Boolean) {
        _studentProfile.value = _studentProfile.value.copy(checkoutAlarmsEnabled = enabled)
    }

    fun login(idOrEmail: String, pass: String): Boolean {
        _isUserLoggedIn.value = true
        return true
    }

    fun logout() {
        _isUserLoggedIn.value = false
    }

    fun register(profile: StudentProfile) {
        _studentProfile.value = profile
        _isUserLoggedIn.value = true
    }
}
