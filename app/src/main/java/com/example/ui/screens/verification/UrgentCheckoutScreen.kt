package com.example.ui.screens.verification

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.BiometricDialog
import com.example.ui.theme.DUAmber
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.theme.DURed
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination

@Composable
fun UrgentCheckoutScreen(
    sessionId: String,
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val showBiometricDialog by viewModel.showBiometricDialog.collectAsState()

    if (showBiometricDialog) {
        BiometricDialog(
            onSuccess = {
                viewModel.onBiometricSuccess(sessionId = sessionId, isFinalCheckout = true)
            },
            onDismiss = {
                viewModel.dismissBiometricPrompt()
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.navigateBack() },
                modifier = Modifier.testTag("urgent_checkout_back_button")
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text("Urgent Final Attendance Checkout", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(DUGreen), contentAlignment = Alignment.Center) {
                Text("YB", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Urgency Checkout Window Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFFFEF3C7))
                .border(1.dp, Color(0xFFFCD34D), RoundedCornerShape(18.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color(0xFF92400E)).padding(horizontal = 8.dp, vertical = 3.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.AccessTime, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("CHECKOUT WINDOW", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }

                    Text("08:42 left", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF92400E))
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Final Attendance Checkout Required!", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text("Lecture ending for CoSc 4124: Distributed Systems & Cloud Computing (Lab 204).", fontSize = 12.sp, color = Color(0xFF475569))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Senate Rule 44.1 Disqualification Warning Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFEE2E2))
                .border(1.dp, Color(0xFFFCA5A5), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = DURed, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("DU SENATE RULE 44.1 WARNING", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DURed)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Attention Yonas: Incomplete departure verification registers this session as \"Unverified Early Exit\", reducing your semester standing below the 80% exam eligibility threshold.",
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = Color(0xFF991B1B)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Current Standing", fontSize = 10.sp, color = Color(0xFF64748B))
                        Text("94.2% Verified", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                    Icon(imageVector = Icons.Default.TrendingDown, contentDescription = null, tint = DURed, modifier = Modifier.size(16.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Projected Drop", fontSize = 10.sp, color = Color(0xFF64748B))
                        Text("91.8% Risk", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DURed)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Spatial Departure Gate Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Sensors, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Spatial Departure Gate", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    }
                    Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(DUGreenLight).padding(horizontal = 6.dp, vertical = 2.dp)) {
                        Text("• Active Link", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF1F5F9)).padding(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = DUGreen, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Inside Lab 204 (14m)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                    }

                    Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF1F5F9)).padding(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.GpsFixed, contentDescription = null, tint = DUGreen, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("±2.4m High Integrity", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Bi-Temporal Session Log
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Bi-Temporal Session Log", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    Text("⟳ In Progress", fontSize = 10.sp, color = Color(0xFF6366F1), fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF8FAFC)).padding(8.dp)) {
                        Column {
                            Text("Initial Arrival", fontSize = 9.sp, color = Color(0xFF64748B))
                            Text("08:34 AM", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text("GPS Check Verified", fontSize = 9.sp, color = DUGreen, fontWeight = FontWeight.SemiBold)
                        }
                    }

                    Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF8FAFC)).padding(8.dp)) {
                        Column {
                            Text("Classroom Dwell", fontSize = 9.sp, color = Color(0xFF64748B))
                            Text("1 hr 41 mins", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text("Continuous Presence", fontSize = 9.sp, color = Color(0xFF64748B))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Complete Final Verification Button
        Button(
            onClick = {
                viewModel.openBiometricPrompt()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("complete_final_biometric_button"),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00602B)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(imageVector = Icons.Default.Fingerprint, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Complete Final Verification (Biometric)", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = { viewModel.navigateTo(ScreenDestination.AttendanceCorrectionRequest) },
            modifier = Modifier.fillMaxWidth().height(44.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.Feedback, contentDescription = null, tint = Color(0xFF475569), modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Report Early Dismissal by Lecturer", fontSize = 12.sp, color = Color(0xFF475569))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.NotificationsActive, contentDescription = null, tint = Color(0xFF64748B), modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Remind me in 3 minutes", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF64748B))
        }
    }
}
