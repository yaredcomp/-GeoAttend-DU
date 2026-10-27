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
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Sync
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
import com.example.ui.theme.DUGreenLight
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination

@Composable
fun ClassDetailsScreen(
    courseCode: String,
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val course = viewModel.courses.firstOrNull { it.code == courseCode } ?: viewModel.courses.first()

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
                modifier = Modifier.testTag("class_details_back_button")
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text("GeoAttend DU • Class Details", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            Box(
                modifier = Modifier.size(32.dp).clip(CircleShape).background(DUGreen),
                contentAlignment = Alignment.Center
            ) {
                Text("YB", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Course Header Card
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
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(DUGreen)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(course.code, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(DUGreenLight)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(6.dp).background(DUGreen, CircleShape))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Next in 2h 15m", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(course.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text("Lab Practical & Advanced Architecture • Semester II, 2016 E.C.", fontSize = 11.sp, color = Color(0xFF64748B))

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFF1F5F9))
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.AccessTime, contentDescription = null, tint = Color(0xFF475569), modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text("Session Schedule", fontSize = 9.sp, color = Color(0xFF64748B))
                                Text("Mon & Wed, 10:30", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFF1F5F9))
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Business, contentDescription = null, tint = Color(0xFF475569), modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text("Odaya'a Campus", fontSize = 9.sp, color = Color(0xFF64748B))
                                Text("${course.building}, ${course.room}", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Senate Rule 44.1 Policy Card
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
                        Text("Senate Rule 44.1 Policy", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    }
                    Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(DUGreenLight).padding(horizontal = 6.dp, vertical = 2.dp)) {
                        Text("Good Standing", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Under Academic Regulation 44.1, min 80% biometric dual-session confirmation is required for final examination clearance.",
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = Color(0xFF64748B)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("${course.attendanceRate}% (${course.attendedSessions} of ${course.totalSessions} Verified)", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    Text("+16.0% Safety Margin", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                }

                Spacer(modifier = Modifier.height(6.dp))

                LinearProgressIndicator(
                    progress = { (course.attendanceRate / 100.0).toFloat() },
                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                    color = DUGreen,
                    trackColor = Color(0xFFE2E8F0)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF1F5F9))
                        .padding(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Sync, contentDescription = null, tint = DUGreen, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Bi-Temporal Check: Arrival check-in opens at 08:25 AM. Departure signature is verified at 10:10 AM.", fontSize = 10.sp, color = Color(0xFF334155))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Geofencing & Node Anchor
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
                        Icon(imageVector = Icons.Default.Sensors, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Geofencing & Node Anchor", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    }
                    Text("Beacon #DU-OD-B204", fontSize = 10.sp, color = Color(0xFF64748B))
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("Latitude", fontSize = 10.sp, color = Color(0xFF64748B))
                        Text("${course.beaconNode.latitude}° N", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    }
                    Column {
                        Text("Longitude", fontSize = 10.sp, color = Color(0xFF64748B))
                        Text("${course.beaconNode.longitude}° E", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    }
                    Column {
                        Text("Elevation", fontSize = 10.sp, color = Color(0xFF64748B))
                        Text("${course.beaconNode.elevationMeters} m", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { viewModel.navigateTo(ScreenDestination.CampusMap(course.code)) },
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(imageVector = Icons.Default.NearMe, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Open Lab 204 in Campus Navigation", fontSize = 12.sp, color = DUGreen, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Topic & Materials
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Text("Today's Topic & Materials • Unit IV", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Spacer(modifier = Modifier.height(6.dp))
                Text("Raft Consensus & Byzantine Fault Tolerance", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                Text("Leader election protocols, log replication guarantees, and partition handling in geodistributed etcd clusters.", fontSize = 11.sp, color = Color(0xFF475569))

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFF8FAFC))
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.PictureAsPdf, contentDescription = null, tint = Color(0xFFDC2626), modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("Lecture_09_Raft_Consensus.pdf", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text("4.2 MB • Updated Yesterday", fontSize = 10.sp, color = Color(0xFF64748B))
                        }
                    }
                    Icon(imageVector = Icons.Default.Download, contentDescription = "Download", tint = Color(0xFF64748B), modifier = Modifier.size(18.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Bottom Action Button
        Button(
            onClick = { viewModel.navigateTo(ScreenDestination.Verification(course.code)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("pre_verify_boundary_button"),
            colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(imageVector = Icons.Default.Fingerprint, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Pre-Verify Geofence Boundary", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    }
}
