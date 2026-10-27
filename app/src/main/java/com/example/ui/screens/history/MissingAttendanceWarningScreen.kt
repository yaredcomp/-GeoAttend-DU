package com.example.ui.screens.history

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
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ContactSupport
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DURed
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination

@Composable
fun MissingAttendanceWarningScreen(
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
            .padding(16.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.navigateBack() },
                modifier = Modifier.testTag("missing_warning_back_button")
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Missing Attendance Warning", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text("Registrar & Senate Verification", fontSize = 10.sp, color = Color(0xFF64748B))
            }
            Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(DURed), contentAlignment = Alignment.Center) {
                Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // High Risk Disqualification Card
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
                    Box(modifier = Modifier.size(28.dp).clip(CircleShape).background(DURed), contentAlignment = Alignment.Center) {
                        Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("IMMEDIATE COMPLIANCE ACTION REQUIRED", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DURed)
                        Text("Final Exam Disqualification Risk!", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF7F1D1D))
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text("Academic Regulation 44.1: Minimum 80% In-Person Attendance Policy.", fontSize = 11.sp, color = Color(0xFF991B1B))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Student & Module Profile Card
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
                    Text("STUDENT & MODULE PROFILE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
                    Text("Semester I, 2016 E.C.", fontSize = 10.sp, color = Color(0xFF4F46E5), fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Yonas Bekele Tadesse • DU/UG/14298/14", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text("Department of Software Engineering • Odaya'a Main Campus", fontSize = 11.sp, color = Color(0xFF64748B))

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFF8FAFC))
                        .padding(10.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("SWE 3112: Database Systems", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Box(modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(Color(0xFFFEF3C7)).padding(horizontal = 6.dp, vertical = 2.dp)) {
                                Text("Core Course", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                            }
                        }
                        Text("Instructor: Ins. Mihretu G. • Tech Block A, Room 108", fontSize = 11.sp, color = Color(0xFF475569))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Attendance Deficit Metrics
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
                    Text("Attendance Deficit Metrics", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(Color(0xFFFEE2E2)).padding(horizontal = 8.dp, vertical = 3.dp)) {
                        Text("• Below Quorum", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DURed)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(68.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFEE2E2))
                            .border(3.dp, DURed, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("78.3%", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DURed)
                            Text("Actual", fontSize = 8.sp, color = Color(0xFF64748B))
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Current Verified", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text("18 / 23", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = { 0.783f },
                            modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                            color = DURed,
                            trackColor = Color(0xFFE2E8F0)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("• Senate Threshold: 80.00% (-1 Session needed)", fontSize = 10.sp, color = DURed, fontWeight = FontWeight.SemiBold)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "According to Senate Legislation Article 44.1.2, any candidate falling below 80% without an officially registered excuse filed within 5 days of absence is automatically barred from sitting for Final Semester Examinations.",
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = Color(0xFF475569)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Remediation Button
        Button(
            onClick = { viewModel.navigateTo(ScreenDestination.AttendanceCorrectionRequest) },
            modifier = Modifier.fillMaxWidth().height(50.dp).testTag("file_correction_request_button"),
            colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.Description, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("File Attendance Correction Request", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = { viewModel.navigateBack() },
            modifier = Modifier.fillMaxWidth().height(44.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.ContactSupport, contentDescription = null, tint = Color(0xFF475569), modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Request Instructor Endorsement", fontSize = 12.sp, color = Color(0xFF475569))
        }
    }
}
