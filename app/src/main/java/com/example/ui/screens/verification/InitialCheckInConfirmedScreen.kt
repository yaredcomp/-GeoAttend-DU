package com.example.ui.screens.verification

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DUAmber
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.theme.DURed
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination

@Composable
fun InitialCheckInConfirmedScreen(
    sessionId: String,
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Phase Tag
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFEEF7F0))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(20.dp).background(DUGreen, CircleShape), contentAlignment = Alignment.Center) {
                    Text("1", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text("Phase 1 of 2 Complete • Initial Check-In Confirmed", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
            }
            Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(Color(0xFFDCFCE7)).padding(horizontal = 6.dp, vertical = 2.dp)) {
                Text("Active Session", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DUGreen)
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Large Success Badge
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFDCFCE7)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(DUGreen),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Success", tint = Color.White, modifier = Modifier.size(32.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(Color(0xFFF1F5F9)).padding(horizontal = 10.dp, vertical = 3.dp)) {
            Text("Odaya'a Campus • Software Engineering", fontSize = 10.sp, color = Color(0xFF475569), fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text("Arrival Verified Successfully!", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
        Text(
            text = "Biometric signature validated and locked to lecture perimeter.",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF64748B)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Session Information Card
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
                    Text("CoSc 4124", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    Box(modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(Color(0xFFDCFCE7)).padding(horizontal = 6.dp, vertical = 2.dp)) {
                        Text("Lab Practical", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                }
                Text("Distributed Systems & Cloud Computing", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text("Dr. Abrham Desta • Tech Block B, Lab 204", fontSize = 11.sp, color = Color(0xFF64748B))

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFF8FAFC))
                        .padding(8.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Recorded Timestamp", fontSize = 10.sp, color = Color(0xFF64748B))
                            Text("08:34:12 AM EAT", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Ethiopian Academic Calendar", fontSize = 10.sp, color = Color(0xFF64748B))
                            Text("Tikimt 12, 2016 E.C.", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Ledger Hash ID: DU-TXN-90214-4124-IN", fontSize = 9.sp, color = Color(0xFF64748B))
                            Box(modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(Color(0xFFEEF2FF)).padding(horizontal = 4.dp, vertical = 1.dp)) {
                                Text("ECDSA-256", fontSize = 9.sp, color = Color(0xFF4F46E5), fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Dual-Check-In Mandate Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column {
                Text("Dual-Check-In Senate Mandate", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text("Legislation 44.1 • Mandatory Attendance", fontSize = 10.sp, color = Color(0xFF64748B))

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Step 1: Arrival Check-In (Completed at 08:34 AM)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(16.dp).background(Color(0xFFFEF3C7), CircleShape), contentAlignment = Alignment.Center) {
                        Box(modifier = Modifier.size(6.dp).background(DUAmber, CircleShape))
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Step 2: Departure Verification (Upcoming: 10:15 – 10:30 AM)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Departure Checkout Window Countdown
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFEEF2FF))
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("DEPARTURE CHECKOUT WINDOW OPENS IN", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4F46E5))
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text("01 Hours", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text(":", fontSize = 14.sp, color = Color(0xFF94A3B8))
                            Text("40 Mins", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text(":", fontSize = 14.sp, color = Color(0xFF94A3B8))
                            Text("44 Secs", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                        Text("Target: 10:15:00 AM inside Lab 204", fontSize = 10.sp, color = Color(0xFF64748B))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFFEF2F2))
                        .padding(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = DURed, modifier = Modifier.size(14.dp).padding(top = 2.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Warning: Both arrival and departure signatures are required to record full course credit. Leaving campus prior to checkout invalidates today's attendance record.",
                            fontSize = 10.sp,
                            lineHeight = 14.sp,
                            color = Color(0xFF991B1B)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Return to Dashboard Button
        Button(
            onClick = { viewModel.navigateTo(ScreenDestination.MainApp) },
            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("return_dashboard_button"),
            colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.Dashboard, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Return to Student Dashboard", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Fast-Forward to Step 2 (Final Checkout trigger for test evaluation)
        OutlinedButton(
            onClick = { viewModel.navigateTo(ScreenDestination.UrgentCheckout(sessionId)) },
            modifier = Modifier.fillMaxWidth().height(44.dp).testTag("simulate_final_checkout_button"),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.AccessTime, contentDescription = null, tint = DUAmber, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Advance to Departure Window (Final Checkout)", fontSize = 12.sp, color = Color(0xFF92400E), fontWeight = FontWeight.Bold)
        }
    }
}
