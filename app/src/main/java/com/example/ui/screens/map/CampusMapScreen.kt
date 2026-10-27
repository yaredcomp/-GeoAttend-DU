package com.example.ui.screens.map

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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Tune
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
import com.example.ui.components.CampusBuilding3DView
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.viewmodel.AppViewModel
import com.example.ui.viewmodel.ScreenDestination

@Composable
fun CampusMapScreen(
    courseCode: String,
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val simulatedDist by viewModel.simulatedDistance.collectAsState()

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
                modifier = Modifier.testTag("map_back_button")
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("TomTom Classroom Map", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text("Campus Navigation & Geofence", fontSize = 11.sp, color = Color(0xFF64748B))
            }
            Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(DUGreen), contentAlignment = Alignment.Center) {
                Icon(imageVector = Icons.Default.Sensors, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Search Bar Destination
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color(0xFF64748B))
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Destination Classroom", fontSize = 10.sp, color = Color(0xFF64748B))
                    Text("Tech Block B, Lab 204", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                }
                Icon(imageVector = Icons.Default.Tune, contentDescription = "Filters", tint = Color(0xFF64748B))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 3D Isometric Map and Walkway directions
        CampusBuilding3DView(
            currentDistanceMeters = simulatedDist,
            onSimulateArrival = {
                viewModel.setSimulationMode("ARRIVAL_0M")
            },
            onCalibrateBeacon = {
                viewModel.setSimulationMode("INSIDE")
            }
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Bottom Classroom Summary Card
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
                            .background(DUGreenLight)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text("CS-401 • 10:30 AM • Window Open", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Distributed Systems Lab", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text("Tech Block B • Level 2, Room B-204", fontSize = 11.sp, color = Color(0xFF64748B))

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Estimated
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFF1F5F9))
                            .padding(8.dp)
                    ) {
                        Column {
                            Text("Estimated", fontSize = 9.sp, color = Color(0xFF64748B))
                            Text("2 mins", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text("${simulatedDist.toInt()}m distance", fontSize = 9.sp, color = Color(0xFF64748B))
                        }
                    }

                    // Geofence
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFEF3C7))
                            .padding(8.dp)
                    ) {
                        Column {
                            Text("Geofence", fontSize = 9.sp, color = Color(0xFF92400E))
                            Text(if (simulatedDist <= 25.0) "In Radius" else "Approaching", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                            Text("${simulatedDist.toInt()}m to gate", fontSize = 9.sp, color = Color(0xFF92400E))
                        }
                    }

                    // Attendance
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFDCFCE7))
                            .padding(8.dp)
                    ) {
                        Column {
                            Text("Attendance", fontSize = 9.sp, color = DUGreen)
                            Text("Ready", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                            Text("Biometric active", fontSize = 9.sp, color = DUGreen)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = {
                        viewModel.navigateTo(ScreenDestination.Verification(courseCode))
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp).testTag("start_verification_from_map"),
                    colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(imageVector = Icons.Default.Navigation, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Proceed to Geofence Verification", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = DUGreen, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Dilla University Verified Indoor Positioning Node #DU-OD-B204", fontSize = 10.sp, color = Color(0xFF64748B))
        }
    }
}
