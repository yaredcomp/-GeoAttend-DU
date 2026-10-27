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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.ui.components.RadarCompassView
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.theme.DURed
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination

@Composable
fun SpatialVerificationScreen(
    sessionId: String,
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val simulatedDist by viewModel.simulatedDistance.collectAsState()
    val isInside by viewModel.isInsideGeofence.collectAsState()
    val showBiometricDialog by viewModel.showBiometricDialog.collectAsState()
    val verificationStep by viewModel.verificationStep.collectAsState()

    if (showBiometricDialog) {
        BiometricDialog(
            onSuccess = {
                viewModel.onBiometricSuccess(sessionId = sessionId, isFinalCheckout = false)
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
        // Top Navigation Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.navigateBack() },
                modifier = Modifier.testTag("verification_back_button")
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text("GeoAttend DU • Verification", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            Box(
                modifier = Modifier.size(32.dp).clip(CircleShape).background(DUGreen),
                contentAlignment = Alignment.Center
            ) {
                Text("YB", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Active Session Hero Banner
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
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(DUGreenLight)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(6.dp).background(DUGreen, CircleShape))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("ACTIVE SESSION", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                        }
                    }

                    Text("00:33 remaining", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text("CoSc 4124: Distributed Systems", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text("Lab 204 • Technology Complex, 2nd Floor", fontSize = 12.sp, color = Color(0xFF475569))
                Text("Lecturer: Dr. Abrham Desta • Section 03", fontSize = 11.sp, color = Color(0xFF64748B))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Simulation Diagnostics Controls (Test Protocol)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("SIMULATION DIAGNOSTICS (TEST PROTOCOL)", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
            Icon(imageVector = Icons.Default.Tune, contentDescription = null, tint = Color(0xFF64748B), modifier = Modifier.size(14.dp))
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // Inside Geofence
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isInside && simulatedDist < 20.0) Color(0xFF00602B) else Color(0xFFF1F5F9))
                    .clickable { viewModel.setSimulationMode("INSIDE") }
                    .padding(vertical = 7.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = if (isInside && simulatedDist < 20.0) Color.White else Color(0xFF475569), modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Inside Geofence", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = if (isInside && simulatedDist < 20.0) Color.White else Color(0xFF475569))
                }
            }

            // Outside Boundary
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (!isInside) DURed else Color(0xFFF1F5F9))
                    .clickable { viewModel.setSimulationMode("OUTSIDE") }
                    .padding(vertical = 7.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Outside Boundary", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = if (!isInside) Color.White else Color(0xFF475569))
            }

            // Weak GPS
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isInside && simulatedDist >= 20.0) Color(0xFFF59E0B) else Color(0xFFF1F5F9))
                    .clickable { viewModel.setSimulationMode("WEAK_GPS") }
                    .padding(vertical = 7.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Weak GPS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = if (isInside && simulatedDist >= 20.0) Color.White else Color(0xFF475569))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Step 1: Enrolled Course
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(24.dp).clip(CircleShape).background(DUGreen), contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("STEP 1 • ENROLLED COURSE", fontSize = 10.sp, color = Color(0xFF64748B), fontWeight = FontWeight.Bold)
                        Text("Validated", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                    Text("CoSc 4124 • Distributed Systems", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    Text("Confirmed with University Registrar (ID: UGR/19842/14)", fontSize = 11.sp, color = Color(0xFF64748B))
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Step 2: Node Coordinates
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(24.dp).clip(CircleShape).background(DUGreen), contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("STEP 2 • NODE COORDINATES", fontSize = 10.sp, color = Color(0xFF64748B), fontWeight = FontWeight.Bold)
                        Text("Node Active", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                    Text("Beacon #DU-OD-B204 (Lab 204)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    Text("Geo-fence centerpoint registered: 6.4061° N, 38.3134° E", fontSize = 11.sp, color = Color(0xFF64748B))
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Step 3 of 6: Geofence Boundary Assessment & Radar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(24.dp).clip(CircleShape).background(DUGreen), contentAlignment = Alignment.Center) {
                            Text("3", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("STEP 3 OF 6", fontSize = 9.sp, color = Color(0xFF64748B), fontWeight = FontWeight.Bold)
                            Text("Geofence Boundary Assessment", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isInside) DUGreenLight else Color(0xFFFEE2E2))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = if (isInside) "Inside Classroom" else "Outside Boundary",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isInside) DUGreen else DURed
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                RadarCompassView(
                    userDistanceMeters = simulatedDist,
                    boundaryRadiusMeters = 25.0,
                    precisionMeters = 2.8,
                    isInsideBoundary = isInside
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Integrity Checklist
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Wifi, contentDescription = null, tint = Color(0xFF64748B), modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Wi-Fi RTT Fine Timing & Cellular Cell-ID", fontSize = 11.sp, color = Color(0xFF475569))
                        }
                        Text("✓ Validated", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.PhoneAndroid, contentDescription = null, tint = Color(0xFF64748B), modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Android Mock Location Provider", fontSize = 11.sp, color = Color(0xFF475569))
                        }
                        Text("✓ Inactive (Clean)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Shield, contentDescription = null, tint = Color(0xFF64748B), modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Root / Frida / Hook Kernel Integrity", fontSize = 11.sp, color = Color(0xFF475569))
                        }
                        Text("✓ Uncompromised", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Step 4 of 6: Hardware-Backed Security Policy
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFEEF2FF))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp).padding(top = 2.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Native Android BiometricPrompt Standard", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    Text(
                        text = "Dilla University security policy requires hardware-backed biometric verification to guarantee attendance integrity. The application does not store your fingerprint or facial data; authentication is processed securely via your Android OS Keystore BiometricPrompt API with a single-use ECDSA cryptographic challenge.",
                        fontSize = 10.sp,
                        lineHeight = 14.sp,
                        color = Color(0xFF475569)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Step 5 of 6: Student Identity Verification Trigger
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("STEP 5 OF 6: Student Identity Verification", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Icon(imageVector = Icons.Default.Fingerprint, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.openBiometricPrompt()
                },
                enabled = isInside,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("authenticate_biometrics_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00602B),
                    disabledContainerColor = Color(0xFFE2E8F0)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Fingerprint, contentDescription = null, modifier = Modifier.size(20.dp))
                    Text("Authenticate via Device Biometrics", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(18.dp))
                }
            }

            if (!isInside) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "⚠️ You are outside the 25m classroom boundary. Move closer to Lab 204 to unlock biometric authentication.",
                    fontSize = 11.sp,
                    color = DURed,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Step 6: Ledger Submission
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFF1F5F9))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Step 6: Ledger Submission", fontSize = 11.sp, color = Color(0xFF64748B), fontWeight = FontWeight.SemiBold)
                Text("• Pending Biometric Confirmation", fontSize = 10.sp, color = Color(0xFF6366F1), fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Report GPS discrepancy link
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.navigateTo(ScreenDestination.AttendanceCorrectionRequest) },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = Color(0xFF64748B), modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Report GPS Location Discrepancy", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
        }
    }
}
