package com.example.ui.screens.verification

import android.widget.Toast
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
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination

@Composable
fun FinalAttendanceReceiptScreen(
    sessionId: String,
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

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
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.navigateTo(ScreenDestination.MainApp) },
                modifier = Modifier.testTag("receipt_back_button")
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text("GeoAttend DU • Successful Final Check In", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(DUGreen), contentAlignment = Alignment.Center) {
                Text("YB", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Ledger status banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFDCFCE7))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = DUGreen, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("LEDGER STATUS: Session 100% Completed & Sealed", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                }
                Box(modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(Color.White).padding(horizontal = 6.dp, vertical = 2.dp)) {
                    Text("Immutable", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Large Verified Shield
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(DUGreenLight),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(DUGreen),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Shield, contentDescription = "Verified", tint = Color.White, modifier = Modifier.size(30.dp))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(Color(0xFFE2E8F0)).padding(horizontal = 8.dp, vertical = 3.dp)) {
            Text("OFFICIAL CAMPUS RECORD", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF475569))
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text("Attendance Fully Recorded!", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1E293B))
        Text(
            text = "Bi-temporal location verification successfully sealed for Dilla University Academic Records.",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF64748B)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Dual Verification Audit Trail Card
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
                    Text("DUAL VERIFICATION AUDIT TRAIL", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
                    Text("100% Lecture Verified", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Event 1: Initial Arrival
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("1. Initial Arrival Recorded", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text("08:34:12 AM EAT", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                        Text("Geofence Node #B204 • Biometric Signed ✓", fontSize = 10.sp, color = DUGreen)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Event 2: Final Departure
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("2. Final Departure Recorded", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text("10:22:45 AM EAT", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                        Text("Geofence Node #B204 • Biometric Signed ✓", fontSize = 10.sp, color = DUGreen)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFEEF2FF))
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Session Total Active Time:", fontSize = 11.sp, color = Color(0xFF4F46E5), fontWeight = FontWeight.SemiBold)
                        Text("1 hr 48 mins", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4F46E5))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Cryptographic Ledger Proof
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                .padding(12.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("CRYPTOGRAPHIC LEDGER PROOF", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
                    Icon(imageVector = Icons.Default.Shield, contentDescription = null, tint = DUGreen, modifier = Modifier.size(14.dp))
                }

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF8FAFC))
                        .padding(8.dp)
                ) {
                    Column {
                        Text("Master Block Hash", fontSize = 9.sp, color = Color(0xFF64748B))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("DU-CHAIN-2024-SEM2-COSC4124-7749", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy", tint = Color(0xFF64748B), modifier = Modifier.size(14.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text("✓ Synced with Dilla University Registrar Portal", fontSize = 10.sp, color = DUGreen, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Primary Buttons
        Button(
            onClick = { viewModel.navigateTo(ScreenDestination.MainApp) },
            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("receipt_return_dashboard_button"),
            colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.Dashboard, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Return to Student Dashboard", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = { viewModel.navigateTo(ScreenDestination.AttendanceHistory) },
            modifier = Modifier.fillMaxWidth().height(44.dp).testTag("view_attendance_history_button"),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.History, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("View Updated Attendance History", fontSize = 12.sp, color = DUGreen, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .clickable {
                    Toast.makeText(context, "Verified Receipt PDF exported to Downloads", Toast.LENGTH_SHORT).show()
                }
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Download, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Download Verified Receipt PDF", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DUGreen)
        }
    }
}
