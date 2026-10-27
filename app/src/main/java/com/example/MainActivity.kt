package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.AppNavDestination
import com.example.ui.components.DUAppTopBar
import com.example.ui.components.DUBottomNav
import com.example.ui.screens.auth.ForgotPasswordScreen
import com.example.ui.screens.auth.LoginScreen
import com.example.ui.screens.auth.RegistrationScreen
import com.example.ui.screens.auth.WelcomeScreen
import com.example.ui.screens.dashboard.DashboardScreen
import com.example.ui.screens.history.AttendanceCorrectionRequestScreen
import com.example.ui.screens.history.AttendanceHistoryScreen
import com.example.ui.screens.history.MissingAttendanceWarningScreen
import com.example.ui.screens.map.CampusMapScreen
import com.example.ui.screens.notifications.NotificationsScreen
import com.example.ui.screens.profile.ProfileScreen
import com.example.ui.screens.timetable.ClassDetailsScreen
import com.example.ui.screens.timetable.TimetableScreen
import com.example.ui.screens.verification.FinalAttendanceReceiptScreen
import com.example.ui.screens.verification.InitialCheckInConfirmedScreen
import com.example.ui.screens.verification.SpatialVerificationScreen
import com.example.ui.screens.verification.UrgentCheckoutScreen
import com.example.ui.theme.GeoAttendDUTheme
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination
import com.example.util.LocalAppLanguage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appViewModel: AppViewModel = viewModel()
            val currentLanguage by appViewModel.currentLanguage.collectAsState()

            CompositionLocalProvider(LocalAppLanguage provides currentLanguage) {
                GeoAttendDUTheme {
                    GeoAttendDUApp(viewModel = appViewModel)
                }
            }
        }
    }
}

@Composable
fun GeoAttendDUApp(viewModel: AppViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val currentNavTab by viewModel.currentNavTab.collectAsState()
    val currentLanguage by viewModel.currentLanguage.collectAsState()

    // Handle back button for any sub-screen
    if (currentScreen != ScreenDestination.MainApp && currentScreen != ScreenDestination.Welcome) {
        BackHandler {
            viewModel.navigateBack()
        }
    }

    when (val screen = currentScreen) {
        is ScreenDestination.Welcome -> {
            WelcomeScreen(
                currentLanguage = currentLanguage,
                onRegisterClick = { viewModel.navigateTo(ScreenDestination.Registration) },
                onLoginClick = { viewModel.navigateTo(ScreenDestination.Login) }
            )
        }
        is ScreenDestination.Registration -> {
            RegistrationScreen(
                onNavigateBack = { viewModel.navigateTo(ScreenDestination.Welcome) },
                onContinueToLogin = { viewModel.navigateTo(ScreenDestination.Login) },
                onSuccessRegister = { viewModel.navigateTo(ScreenDestination.MainApp) }
            )
        }
        is ScreenDestination.Login -> {
            LoginScreen(
                onLoginSuccess = { viewModel.navigateTo(ScreenDestination.MainApp) },
                onForgotPasswordClick = { viewModel.navigateTo(ScreenDestination.ForgotPassword) },
                onRegisterClick = { viewModel.navigateTo(ScreenDestination.Registration) }
            )
        }
        is ScreenDestination.ForgotPassword -> {
            ForgotPasswordScreen(
                onNavigateBack = { viewModel.navigateTo(ScreenDestination.Login) },
                onPasswordResetSuccess = { viewModel.navigateTo(ScreenDestination.Login) }
            )
        }
        is ScreenDestination.ClassDetails -> {
            ClassDetailsScreen(courseCode = screen.courseCode, viewModel = viewModel)
        }
        is ScreenDestination.CampusMap -> {
            CampusMapScreen(courseCode = screen.courseCode, viewModel = viewModel)
        }
        is ScreenDestination.Verification -> {
            SpatialVerificationScreen(sessionId = screen.sessionId, viewModel = viewModel)
        }
        is ScreenDestination.InitialConfirmed -> {
            InitialCheckInConfirmedScreen(sessionId = screen.sessionId, viewModel = viewModel)
        }
        is ScreenDestination.UrgentCheckout -> {
            UrgentCheckoutScreen(sessionId = screen.sessionId, viewModel = viewModel)
        }
        is ScreenDestination.FinalReceipt -> {
            FinalAttendanceReceiptScreen(sessionId = screen.sessionId, viewModel = viewModel)
        }
        is ScreenDestination.AttendanceHistory -> {
            AttendanceHistoryScreen(viewModel = viewModel)
        }
        is ScreenDestination.MissingAttendanceWarning -> {
            MissingAttendanceWarningScreen(viewModel = viewModel)
        }
        is ScreenDestination.AttendanceCorrectionRequest -> {
            AttendanceCorrectionRequestScreen(viewModel = viewModel)
        }
        is ScreenDestination.MainApp -> {
            Scaffold(
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                topBar = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .statusBarsPadding()
                    ) {
                        DUAppTopBar(
                            currentLanguage = currentLanguage,
                            onToggleLanguage = { viewModel.toggleLanguage() },
                            onProfileClick = { viewModel.navigateToTab(AppNavDestination.PROFILE) }
                        )
                    }
                },
                bottomBar = {
                    DUBottomNav(
                        currentDestination = currentNavTab,
                        onNavigate = { tab -> viewModel.navigateToTab(tab) },
                        currentLanguage = currentLanguage,
                        unreadAlertsCount = 2
                    )
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    when (currentNavTab) {
                        AppNavDestination.HOME -> DashboardScreen(viewModel = viewModel)
                        AppNavDestination.TIMETABLE -> TimetableScreen(viewModel = viewModel)
                        AppNavDestination.VERIFY -> SpatialVerificationScreen(sessionId = "SESS-4124-MON", viewModel = viewModel)
                        AppNavDestination.ALERTS -> NotificationsScreen(viewModel = viewModel)
                        AppNavDestination.PROFILE -> ProfileScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
