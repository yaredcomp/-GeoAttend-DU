package com.example.ui.screens.history

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
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import com.example.ui.theme.DUAmber
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.theme.DURed
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination

@Composable
fun AttendanceHistoryScreen(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val records by viewModel.records.collectAsState()
    val courses = viewModel.courses
    var selectedFilter by remember { mutableStateOf("All Courses") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.navigateBack() },
                modifier = Modifier.testTag("attendance_history_back_button")
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("ACADEMIC YEAR 2024/2025", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
                Text("Attendance & Punctuality", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            }
            Icon(imageVector = Icons.Default.Info, contentDescription = "Info", tint = Color(0xFF64748B), modifier = Modifier.size(20.dp))
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Exemplary Standing Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(18.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Circular Gauge
                    Box(
                        modifier = Modifier
                            .size(76.dp)
                            .clip(CircleShape)
                            .background(DUGreenLight)
                            .border(4.dp, DUGreen, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("94.2%", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = DUGreen)
                            Text("Rate", fontSize = 9.sp, color = Color(0xFF64748B))
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Exemplary Standing", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(DUGreenLight)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text("+14.2% DU Senate Req.", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Exceeds the institutional 80.0% minimum threshold for final examinations.",
                            fontSize = 11.sp,
                            lineHeight = 15.sp,
                            color = Color(0xFF475569)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Full Exam Eligibility Confirmed
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFDCFCE7))
                        .padding(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Status: Full Exam Eligibility Confirmed (DU Senate)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 4 Breakdown Tiles
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF1F5F9)).padding(8.dp)) {
                        Column {
                            Text("Attended", fontSize = 9.sp, color = Color(0xFF64748B))
                            Text("38 Sessions", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                    }
                    Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF1F5F9)).padding(8.dp)) {
                        Column {
                            Text("Late Recorded", fontSize = 9.sp, color = Color(0xFF64748B))
                            Text("02 Sessions", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF1F5F9)).padding(8.dp)) {
                        Column {
                            Text("Excused Leave", fontSize = 9.sp, color = Color(0xFF64748B))
                            Text("01 Medical", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                    }
                    Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF1F5F9)).padding(8.dp)) {
                        Column {
                            Text("Unexcused Miss", fontSize = 9.sp, color = Color(0xFF64748B))
                            Text("00 Zero deficit", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Filter Chips
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("All Courses", "Semester II", "Deficits").forEach { chip ->
                val isSelected = selectedFilter == chip
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) Color(0xFFEEF2FF) else Color.White)
                        .border(1.dp, if (isSelected) Color(0xFF6366F1) else Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                        .clickable { selectedFilter = chip }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chip,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) Color(0xFF4F46E5) else Color(0xFF64748B)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Course Breakdown (4 Enrolled Units)
        Text("Course breakdown • 4 Enrolled Units", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))

        Spacer(modifier = Modifier.height(8.dp))

        courses.forEach { course ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
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
                        Text(course.code, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                        Text("${course.attendanceRate}%", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1E293B))
                    }
                    Text(course.title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    Text("${course.lecturer} • ${course.building}, ${course.room}", fontSize = 11.sp, color = Color(0xFF64748B))

                    Spacer(modifier = Modifier.height(6.dp))

                    LinearProgressIndicator(
                        progress = { (course.attendanceRate / 100.0).toFloat() },
                        modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                        color = if (course.attendanceRate >= 90.0) DUGreen else DUAmber,
                        trackColor = Color(0xFFE2E8F0)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("✓ ${course.attendedSessions} / ${course.totalSessions} Sessions", fontSize = 10.sp, color = DUGreen, fontWeight = FontWeight.SemiBold)
                        Text("Incomplete Checkouts: ${course.incompleteCheckouts}", fontSize = 10.sp, color = Color(0xFF64748B))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Senate Academic Integrity Disclosure Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFEEF7F0))
                .border(1.dp, Color(0xFFCCE8D2), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Shield, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Senate Academic Integrity Disclosure", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "In accordance with Dilla University Registrar guidelines, all records represent authenticated bi-temporal check-ins (Initial arrival & Final departure verification) inside verified geofence coordinates.",
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = Color(0xFF475569)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = DUGreen, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Odaya'a Main Campus • Geofence Radius 45m Active", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Recent Bi-temporal Logs
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Recent Bi-temporal Logs", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            Text("Auto-Synced Live", fontSize = 10.sp, color = DUGreen, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        records.forEach { record ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
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
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFFEEF2FF)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(record.recordId, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4F46E5))
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(record.courseCode, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                                Text(record.sessionDate, fontSize = 10.sp, color = Color(0xFF64748B))
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (record.isLate) Color(0xFFFEF3C7) else DUGreenLight)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = record.status,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (record.isLate) Color(0xFF92400E) else DUGreen
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(6.dp)).background(Color(0xFFF1F5F9)).padding(6.dp)) {
                            Column {
                                Text("INITIAL ARRIVAL", fontSize = 8.sp, color = Color(0xFF64748B), fontWeight = FontWeight.Bold)
                                Text(record.initialArrival, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            }
                        }

                        Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(6.dp)).background(Color(0xFFF1F5F9)).padding(6.dp)) {
                            Column {
                                Text("FINAL DEPARTURE", fontSize = 8.sp, color = Color(0xFF64748B), fontWeight = FontWeight.Bold)
                                Text(record.finalDeparture, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Discrepancy Card & Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFEEF2FF))
                .padding(14.dp)
        ) {
            Column {
                Text("Discrepancy or Hospital Visit?", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Submit Dilla University Health Center medical certificates or hardware sync dispute forms to the department council within 5 working days.",
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = Color(0xFF475569)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { viewModel.navigateTo(ScreenDestination.AttendanceCorrectionRequest) },
                    modifier = Modifier.fillMaxWidth().height(48.dp).testTag("request_correction_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(imageVector = Icons.Default.AddComment, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Request Attendance Correction", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
