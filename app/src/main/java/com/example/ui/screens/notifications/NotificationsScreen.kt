package com.example.ui.screens.notifications

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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.theme.DURed
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination

@Composable
fun NotificationsScreen(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val notifications by viewModel.notifications.collectAsState()
    var selectedFilter by remember { mutableStateOf("All (12)") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("DU GEOATTEND", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                Text("Notifications & Alerts", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            }
            Box(
                modifier = Modifier.size(36.dp).clip(CircleShape).background(DUGreen),
                contentAlignment = Alignment.Center
            ) {
                Text("YB", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Filter chips
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("All (12)", "Senate Warnings (2)", "Check-In Windows").forEach { chip ->
                val isSelected = selectedFilter == chip
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) DUGreenLight else Color.White)
                        .border(1.dp, if (isSelected) DUGreen else Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                        .clickable { selectedFilter = chip }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = chip,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) DUGreen else Color(0xFF64748B)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // High Risk Card: Senate Breach
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFEE2E2))
                .border(1.dp, Color(0xFFFCA5A5), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(DURed).padding(horizontal = 6.dp, vertical = 2.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = Color.White, modifier = Modifier.size(10.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text("High Risk • 2 hrs ago", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                    Text("Senate Rule 80%", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DURed)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("DU Senate 80% Rule Breached", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF7F1D1D))
                Text("SWE 3112: Database Systems • Sec A", fontSize = 12.sp, color = Color(0xFF991B1B))

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("ATTENDANCE LEVEL", fontSize = 9.sp, color = Color(0xFF64748B))
                            Text("78.4% / 80.0% min", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DURed)
                        }
                        Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = DURed, modifier = Modifier.size(20.dp))
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text("! Action Required: Submit medical excuse or attend departmental review before Friday 5:00 PM EAT.", fontSize = 10.sp, color = Color(0xFF991B1B))

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { viewModel.navigateTo(ScreenDestination.MissingAttendanceWarning) },
                    modifier = Modifier.fillMaxWidth().height(44.dp).testTag("review_warning_appeal_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = DURed),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Review Warning Details & Appeal", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(14.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("TODAY • 2 updates", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))

        Spacer(modifier = Modifier.height(8.dp))

        // Check-In Window Opening Card
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
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(DUGreenLight), contentAlignment = Alignment.Center) {
                            Icon(imageVector = Icons.Default.Sensors, contentDescription = null, tint = DUGreen, modifier = Modifier.size(18.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Check-In Window Opening", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text("15m ago", fontSize = 10.sp, color = Color(0xFF64748B))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text("CoSc 4124 Lab check-in opens in 15 mins at Tech Block B Lab 204. Geofence active.", fontSize = 11.sp, color = Color(0xFF475569))

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("• Radius: 25m • Ready", fontSize = 10.sp, color = DUGreen, fontWeight = FontWeight.Bold)
                    Button(
                        onClick = { viewModel.navigateTo(ScreenDestination.Verification("SESS-4124-MON")) },
                        colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Text("Open Window >", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Bi-Temporal Check-In Confirmed Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(DUGreenLight), contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = DUGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Bi-Temporal Check-In Confirmed", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("1h ago", fontSize = 10.sp, color = Color(0xFF64748B))
                    }
                    Text("Initial arrival verified at 08:34 AM. Don't forget departure checkout at 10:15 AM.", fontSize = 11.sp, color = Color(0xFF475569))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Lecture 11 • Session recorded", fontSize = 10.sp, color = Color(0xFF6366F1), fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("YESTERDAY & EARLIER", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))

        Spacer(modifier = Modifier.height(8.dp))

        // Dispute Approved
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(DUGreenLight), contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Default.Shield, contentDescription = null, tint = DUGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Attendance Dispute Approved", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("Yest, 4:20 PM", fontSize = 10.sp, color = Color(0xFF64748B))
                    }
                    Text("Registrar Directorate approved excuse for Lecture 14 (CoSc 4121). Attendance adjusted +1.", fontSize = 11.sp, color = Color(0xFF475569))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Disp-ID: #8921-DU • Registrar Office", fontSize = 10.sp, color = DUGreen, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Relocation Notice
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(Color(0xFFEEF2FF)), contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Default.NearMe, contentDescription = null, tint = Color(0xFF4F46E5), modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Classroom Relocation Notice", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("2d ago", fontSize = 10.sp, color = Color(0xFF64748B))
                    }
                    Text("Dr. Abrham Desta moved CoSc 4124 tutorial to Block C Hall 2. Geofence anchor updated.", fontSize = 11.sp, color = Color(0xFF475569))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Block C Hall 2 (Lat: 6.4082, Lon: 38.3129)", fontSize = 10.sp, color = Color(0xFF475569))
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Bottom Actions
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier.weight(1f).height(42.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = DUGreen, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Mark all as read", fontSize = 11.sp, color = DUGreen)
            }

            OutlinedButton(
                onClick = { viewModel.navigateToTab(com.example.ui.components.AppNavDestination.PROFILE) },
                modifier = Modifier.weight(1f).height(42.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null, tint = Color(0xFF475569), modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Alert Settings", fontSize = 11.sp, color = Color(0xFF475569))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = Color(0xFF94A3B8), modifier = Modifier.size(12.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Dilla University Cryptographic Attendance Registry", fontSize = 10.sp, color = Color(0xFF94A3B8))
        }
    }
}
