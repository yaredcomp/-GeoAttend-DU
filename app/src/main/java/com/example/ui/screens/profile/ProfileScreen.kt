package com.example.ui.screens.profile

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.theme.DURed
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination
import com.example.util.AppLanguage
import com.example.util.Strings

@Composable
fun ProfileScreen(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val profile by viewModel.studentProfile.collectAsState()
    val currentLang by viewModel.currentLanguage.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("DU GEOATTEND", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                Text("Profile And Settings", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            }
            Box(
                modifier = Modifier.size(36.dp).clip(CircleShape).background(DUGreen),
                contentAlignment = Alignment.Center
            ) {
                Text("YB", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Student Profile Main Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(DUGreenLight)
                            .border(2.dp, DUGreen, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("YB", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(profile.fullName, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Box(modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(DUGreenLight).padding(horizontal = 6.dp, vertical = 2.dp)) {
                                Text("• Active", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                            }
                        }
                        Text(profile.studentId, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                        Text("${profile.program} • Year ${profile.yearOfStudy}", fontSize = 11.sp, color = Color(0xFF64748B))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = Color(0xFF64748B), modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(profile.email, fontSize = 11.sp, color = Color(0xFF475569))
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFEEF7F0))
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Cumulative Attendance", fontSize = 10.sp, color = Color(0xFF64748B))
                        Text("${profile.overallAttendanceRate}% (In Good Standing)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .border(1.dp, Color(0xFFCBD5E1), RoundedCornerShape(8.dp))
                            .clickable { viewModel.navigateTo(ScreenDestination.AttendanceHistory) }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text("Audit Log", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Hardware & Knox Binding Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Shield, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text("Hardware & Knox Binding", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text("Cryptographic anti-spoofing identity", fontSize = 10.sp, color = Color(0xFF64748B))
                        }
                    }
                    Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(DUGreenLight).padding(horizontal = 6.dp, vertical = 2.dp)) {
                        Text("• Bound", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Bound Device", fontSize = 11.sp, color = Color(0xFF64748B))
                    Text(profile.boundDeviceModel, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Hardware Vault", fontSize = 11.sp, color = Color(0xFF64748B))
                    Text("🔒 Knox HSM StrongBox", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Node ECDSA Signature", fontSize = 11.sp, color = Color(0xFF64748B))
                    Text(profile.nodeSignature, fontSize = 11.sp, color = Color(0xFF4F46E5), fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Rooted / Tampered Check", fontSize = 11.sp, color = Color(0xFF64748B))
                    Text("✓ Integrity Passed (CTS)", fontSize = 11.sp, color = DUGreen, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Device Migration Policy", fontSize = 10.sp, color = Color(0xFF64748B))
                    Text("Request Transfer >", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Spatial Verification Sensors Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Sensors, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text("Spatial Verification Sensors", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("Biometric prompts & campus boundary polling", fontSize = 10.sp, color = Color(0xFF64748B))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Switch 1: Biometric Attendance Lock
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Biometric Attendance Lock", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("Prompt Android BiometricPrompt on check-in", fontSize = 10.sp, color = Color(0xFF64748B))
                    }
                    Switch(
                        checked = profile.isBiometricEnabled,
                        onCheckedChange = { viewModel.toggleBiometricSetting(it) },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = DUGreen)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Switch 2: Background Geofence Polling
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Background Geofence Polling", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("Pre-warm GPS 150m before lecture perimeter", fontSize = 10.sp, color = Color(0xFF64748B))
                    }
                    Switch(
                        checked = profile.backgroundGeofenceEnabled,
                        onCheckedChange = { viewModel.toggleGeofencePolling(it) },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = DUGreen)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Switch 3: Checkout Departure Alarms
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Checkout Departure Alarms", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("Vibration prompt 5 minutes prior to class closure", fontSize = 10.sp, color = Color(0xFF64748B))
                    }
                    Switch(
                        checked = profile.checkoutAlarmsEnabled,
                        onCheckedChange = { viewModel.toggleCheckoutAlarm(it) },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = DUGreen)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFEEF2FF))
                        .padding(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text("Precise Location • Always Allowed", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                            Text("Strictly mandated by DU Senate Legislation Rule 44.1 for academic credit validity.", fontSize = 10.sp, color = Color(0xFF475569))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Application Preferences & Language Switcher Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Tune, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text("Application Preferences", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("Local runtime & language controls", fontSize = 10.sp, color = Color(0xFF64748B))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Language EN / አም toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Display Language", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("English & Amharic translation", fontSize = 10.sp, color = Color(0xFF64748B))
                    }

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFEEF2F6))
                            .padding(2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (currentLang == AppLanguage.EN) DUGreen else Color.Transparent)
                                .clickable { viewModel.setLanguage(AppLanguage.EN) }
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text("EN", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = if (currentLang == AppLanguage.EN) Color.White else Color(0xFF475569))
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (currentLang == AppLanguage.AM) DUGreen else Color.Transparent)
                                .clickable { viewModel.setLanguage(AppLanguage.AM) }
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text("አም", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = if (currentLang == AppLanguage.AM) Color.White else Color(0xFF475569))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Theme Tone", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("Official institutional palette", fontSize = 10.sp, color = Color(0xFF64748B))
                    }
                    Text("• DU Institutional Light", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Out Button
        Button(
            onClick = { viewModel.logout() },
            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("sign_out_button"),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEE2E2)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null, tint = DURed, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign Out from Device", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DURed)
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Signing out will require Knox HSM re-authentication on the next academic sign-in session.",
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF94A3B8),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
