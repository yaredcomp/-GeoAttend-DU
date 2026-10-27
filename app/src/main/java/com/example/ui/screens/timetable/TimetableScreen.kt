package com.example.ui.screens.timetable

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.ViewDay
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
import com.example.data.model.ScheduleSession
import com.example.data.model.SessionStatus
import com.example.ui.theme.DUAmber
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination

data class CalendarDayChip(
    val dayName: String,
    val dayNum: String,
    val ethiopianDate: String
)

@Composable
fun TimetableScreen(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val sessions by viewModel.sessions.collectAsState()
    var isDailyView by remember { mutableStateOf(true) }
    var selectedDayIndex by remember { mutableStateOf(0) }

    val days = listOf(
        CalendarDayChip("Mon", "23", "Tikimt 12"),
        CalendarDayChip("Tue", "24", "Tikimt 13"),
        CalendarDayChip("Wed", "25", "Tikimt 14"),
        CalendarDayChip("Thu", "26", "Tikimt 15"),
        CalendarDayChip("Fri", "27", "Tikimt 16"),
        CalendarDayChip("Sat", "28", "Tikimt 17")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Academic Term Pill Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFFEEF2FF))
                .border(1.dp, Color(0xFFC7D2FE), RoundedCornerShape(14.dp))
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.School, contentDescription = null, tint = Color(0xFF4F46E5), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text("ACADEMIC TERM", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6366F1))
                        Text("Sem II • 2016 E.C. (2023/24)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    }
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFDCFCE7))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(6.dp).background(DUGreen, CircleShape))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Odaya'a CS-Yr 4", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Daily View / Weekly View Selector
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFE2E8F0))
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (isDailyView) Color.White else Color.Transparent)
                    .clickable { isDailyView = true }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.ViewDay, contentDescription = null, tint = if (isDailyView) DUGreen else Color(0xFF64748B), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Daily View", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = if (isDailyView) Color(0xFF1E293B) else Color(0xFF64748B))
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (!isDailyView) Color.White else Color.Transparent)
                    .clickable { isDailyView = false }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.GridView, contentDescription = null, tint = if (!isDailyView) DUGreen else Color(0xFF64748B), modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Weekly View", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = if (!isDailyView) Color(0xFF1E293B) else Color(0xFF64748B))
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Date Chips
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(days.indices.toList()) { index ->
                val day = days[index]
                val isSelected = selectedDayIndex == index
                Box(
                    modifier = Modifier
                        .width(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) DUGreen else Color.White)
                        .border(1.dp, if (isSelected) DUGreen else Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                        .clickable { selectedDayIndex = index }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(day.dayName, fontSize = 10.sp, color = if (isSelected) Color.White.copy(alpha = 0.8f) else Color(0xFF64748B), fontWeight = FontWeight.SemiBold)
                        Text(day.dayNum, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if (isSelected) Color.White else Color(0xFF1E293B))
                        Text(day.ethiopianDate, fontSize = 8.sp, color = if (isSelected) Color.White.copy(alpha = 0.9f) else Color(0xFF94A3B8))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Monday's Schedule", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF1F5F9))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text("${sessions.size} Sessions", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF475569))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Classes List
        sessions.forEach { session ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(
                        1.dp,
                        if (session.status == SessionStatus.LIVE_NOW) DUGreen else Color(0xFFE2E8F0),
                        RoundedCornerShape(16.dp)
                    )
                    .clickable { viewModel.navigateTo(ScreenDestination.ClassDetails(session.courseCode)) }
                    .padding(16.dp)
            ) {
                Column {
                    // Top Session Status Badge
                    if (session.status == SessionStatus.LIVE_NOW) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFFEF3C7))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(modifier = Modifier.size(6.dp).background(DUAmber, CircleShape))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("ATTENDANCE WINDOW ACTIVE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                                }
                                Text("08:42 left", fontSize = 11.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF92400E))
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = session.gregorianTime + if (session.status == SessionStatus.LIVE_NOW) " • LIVE NOW" else "",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (session.status == SessionStatus.LIVE_NOW) DUGreen else Color(0xFF64748B)
                        )

                        if (session.status == SessionStatus.VERIFIED) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(DUGreenLight)
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = DUGreen, modifier = Modifier.size(12.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Verified", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                                }
                            }
                        } else if (session.status == SessionStatus.UPCOMING) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFF1F5F9))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text("Upcoming", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF64748B))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${session.courseCode}: ${session.courseTitle}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "${session.building}, ${session.room}   •   Lecturer: ${session.lecturer}",
                        fontSize = 12.sp,
                        color = Color(0xFF475569)
                    )

                    if (session.status == SessionStatus.LIVE_NOW) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Sensors, contentDescription = null, tint = DUGreen, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("In Range (14m to Beacon)", fontSize = 11.sp, color = DUGreen, fontWeight = FontWeight.SemiBold)
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                viewModel.navigateTo(ScreenDestination.Verification(session.sessionId))
                            },
                            modifier = Modifier.fillMaxWidth().height(46.dp).testTag("open_geofence_checkin_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Fingerprint, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Open Geofence & Check In", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedButton(
                            onClick = {
                                viewModel.navigateTo(ScreenDestination.CampusMap(session.courseCode))
                            },
                            modifier = Modifier.fillMaxWidth().height(42.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.NearMe, contentDescription = null, tint = Color(0xFF334155), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Directions to ${session.room}", fontSize = 12.sp, color = Color(0xFF334155), fontWeight = FontWeight.Bold)
                        }
                    } else if (session.status == SessionStatus.VERIFIED) {
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedButton(
                            onClick = {
                                viewModel.navigateTo(ScreenDestination.CampusMap(session.courseCode))
                            },
                            modifier = Modifier.fillMaxWidth().height(42.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Map, contentDescription = null, tint = Color(0xFF334155), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("View Classroom on Map", fontSize = 12.sp, color = Color(0xFF334155))
                        }
                    } else {
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFEEF2F6))
                                .clickable { viewModel.navigateTo(ScreenDestination.CampusMap(session.courseCode)) }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFF64748B), modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Locate ${session.room}", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF475569))
                            }
                        }
                    }
                }
            }
        }
    }
}
