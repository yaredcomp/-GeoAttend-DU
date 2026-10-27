package com.example.ui.screens.dashboard

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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.example.data.model.ScheduleSession
import com.example.data.model.SessionStatus
import com.example.ui.theme.DUAmber
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenDark
import com.example.ui.theme.DUGreenLight
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination
import com.example.util.Strings

@Composable
fun DashboardScreen(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val profile by viewModel.studentProfile.collectAsState()
    val sessions by viewModel.sessions.collectAsState()
    val currentLang by viewModel.currentLanguage.collectAsState()
    val countdownMinutes by viewModel.countdownMinutes.collectAsState()
    val countdownSeconds by viewModel.countdownSeconds.collectAsState()
    val simulatedDist by viewModel.simulatedDistance.collectAsState()
    val isInside by viewModel.isInsideGeofence.collectAsState()

    val nextSession = sessions.firstOrNull { it.courseCode == "CoSc 4124" } ?: sessions.first()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Student Profile Header Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFFEEF7F0))
                .border(1.dp, Color(0xFFCCE8D2), RoundedCornerShape(18.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = profile.fullName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFFDCFCE7))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "ID: ${profile.studentId}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = DUGreen
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "${profile.program} • Year ${profile.yearOfStudy}, ${profile.semester}",
                        fontSize = 12.sp,
                        color = Color(0xFF475569),
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = Color(0xFF64748B),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Monday, Oct 23, 2023 • Tikimt 12, 2016 E.C.",
                            fontSize = 11.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFDCFCE7)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = "Academic Standing",
                        tint = DUGreen,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 3 Metric Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Overall Rate
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Sync, contentDescription = null, tint = DUGreen, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Overall Rate", fontSize = 10.sp, color = Color(0xFF64748B), fontWeight = FontWeight.SemiBold)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("${profile.overallAttendanceRate}%", fontSize = 17.sp, fontWeight = FontWeight.ExtraBold, color = DUGreen)
                    Text("Excellent", fontSize = 10.sp, color = Color(0xFF475569))
                }
            }

            // Sessions
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = Color(0xFF0369A1), modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Sessions", fontSize = 10.sp, color = Color(0xFF64748B), fontWeight = FontWeight.SemiBold)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("${profile.totalSessionsAttended} / ${profile.totalSessionsScheduled}", fontSize = 17.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1E293B))
                    Text("Present", fontSize = 10.sp, color = Color(0xFF475569))
                }
            }

            // Senate Limit
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = DUGreen, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Senate Limit", fontSize = 10.sp, color = Color(0xFF64748B), fontWeight = FontWeight.SemiBold)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("${profile.senateMinRequirement}%", fontSize = 17.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1E293B))
                    Text("+14.2% Safe", fontSize = 10.sp, color = DUGreen, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Next Scheduled Class Hero Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(Color.White)
                .border(2.dp, DUGreen, RoundedCornerShape(18.dp))
                .padding(16.dp)
        ) {
            Column {
                // Countdown Badge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFFEF3C7))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(6.dp).background(DUAmber, CircleShape))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "ATTENDANCE WINDOW OPENS IN",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF92400E)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF0F5227))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        val secStr = if (countdownSeconds < 10) "0$countdownSeconds" else "$countdownSeconds"
                        Text(
                            text = "0${countdownMinutes}m : ${secStr}s",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFDCFCE7))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("LECTURE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Course Code: ${nextSession.courseCode}", fontSize = 12.sp, color = Color(0xFF64748B), fontWeight = FontWeight.SemiBold)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = nextSession.courseTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = Color(0xFF64748B), modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(nextSession.lecturer, fontSize = 12.sp, color = Color(0xFF475569))
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Time & Location Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF1F5F9))
                        .padding(10.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.AccessTime, contentDescription = null, tint = Color(0xFF334155), modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(nextSession.gregorianTime, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Business, contentDescription = null, tint = Color(0xFF334155), modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("${nextSession.building}, ${nextSession.room} (${profile.campus})", fontSize = 11.sp, color = Color(0xFF475569))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Classroom Radar Banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE8F5E9))
                        .clickable { viewModel.navigateTo(ScreenDestination.CampusMap(nextSession.courseCode)) }
                        .padding(10.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Sensors, contentDescription = null, tint = DUGreen, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Classroom Radar: ${simulatedDist.toInt()}m away", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFFC7E4CD))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(if (isInside) "INSIDE BOUNDARY (30m)" else "OUTSIDE BOUNDARY", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DUGreenDark)
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "GPS Precision: ±3m (High Integrity) • BLE Beacon DU-OD-B204 Active",
                            fontSize = 10.sp,
                            color = Color(0xFF475569)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Primary Attendance Verification Button
                Button(
                    onClick = {
                        viewModel.navigateTo(ScreenDestination.Verification(nextSession.sessionId))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("enter_attendance_verification_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00602B)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                        Text(
                            text = Strings.get("enter_attendance", currentLang),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Icon(imageVector = Icons.Default.Fingerprint, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Today's Schedule Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = Strings.get("today_schedule", currentLang),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B)
            )
            Text(
                text = "${sessions.size} Sessions",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF64748B)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        sessions.forEach { session ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                    .clickable { viewModel.navigateTo(ScreenDestination.ClassDetails(session.courseCode)) }
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(session.gregorianTime, fontSize = 11.sp, color = Color(0xFF64748B), fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.width(6.dp))
                            if (session.status == SessionStatus.VERIFIED) {
                                Box(modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(DUGreenLight).padding(horizontal = 6.dp, vertical = 2.dp)) {
                                    Text("Verified", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                                }
                            } else if (session.status == SessionStatus.LIVE_NOW) {
                                Box(modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(Color(0xFFFEF3C7)).padding(horizontal = 6.dp, vertical = 2.dp)) {
                                    Text("Live Now", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "${session.courseCode}: ${session.courseTitle}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        )
                        Text(
                            text = "${session.building}, ${session.room} • ${session.lecturer}",
                            fontSize = 11.sp,
                            color = Color(0xFF475569)
                        )
                    }

                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF94A3B8))
                }
            }
        }
    }
}
