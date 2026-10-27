package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.theme.DUGreenDark

@Composable
fun CampusBuilding3DView(
    currentDistanceMeters: Double,
    onSimulateArrival: () -> Unit,
    onCalibrateBeacon: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedFloor by remember { mutableStateOf("2F") }

    Column(modifier = modifier.fillMaxWidth()) {
        // Map Container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(310.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFEAF5EB))
                .border(1.dp, Color(0xFFD1E7D5), RoundedCornerShape(20.dp))
        ) {
            // Stylized 3D Isometric Canvas
            Canvas(modifier = Modifier.fillMaxWidth().height(310.dp)) {
                // Background grid lines (campus lawn & pathways)
                val strokeColor = Color(0xFFD4EBD9)
                for (i in 0..12) {
                    val y = i * 28f
                    drawLine(
                        color = strokeColor,
                        start = Offset(0f, y),
                        end = Offset(size.width, y + 100f),
                        strokeWidth = 1.5f
                    )
                }

                // Pathway Line from Library to Lab 204
                val path = Path().apply {
                    moveTo(size.width * 0.28f, size.height * 0.72f)
                    cubicTo(
                        size.width * 0.35f, size.height * 0.60f,
                        size.width * 0.50f, size.height * 0.55f,
                        size.width * 0.68f, size.height * 0.35f
                    )
                }
                drawPath(
                    path = path,
                    color = DUGreen,
                    style = Stroke(
                        width = 4f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f))
                    )
                )

                // 3D Isometric Block: Odaya'a Library (User start)
                val libX = size.width * 0.22f
                val libY = size.height * 0.70f
                drawRoundRect(
                    color = Color(0xFF93C5FD),
                    topLeft = Offset(libX, libY),
                    size = androidx.compose.ui.geometry.Size(100f, 60f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(6f, 6f)
                )

                // 3D Isometric Block: Tech Block B (Lab 204 Destination)
                val destX = size.width * 0.60f
                val destY = size.height * 0.22f
                // Geofence circle around Tech Block B
                drawCircle(
                    color = Color(0x331B7A3E),
                    radius = 65f,
                    center = Offset(destX + 50f, destY + 30f)
                )
                drawCircle(
                    color = DUGreen,
                    radius = 65f,
                    center = Offset(destX + 50f, destY + 30f),
                    style = Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f)))
                )
                drawRoundRect(
                    color = Color(0xFF86EFAC),
                    topLeft = Offset(destX, destY),
                    size = androidx.compose.ui.geometry.Size(105f, 65f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(8f, 8f)
                )
            }

            // Top Status Badges inside Map
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // TomTom HD GNSS Chip
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White.copy(alpha = 0.95f))
                        .border(1.dp, Color(0xFFCBD5E1), RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(7.dp).background(DUGreen, CircleShape))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "TomTom HD GNSS • ±2.1m",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        )
                    }
                }

                // Campus Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White.copy(alpha = 0.95f))
                        .border(1.dp, Color(0xFFCBD5E1), RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "Odaya'a Campus",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DUGreen
                    )
                }
            }

            // Geofence target badge over Tech Block B
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 70.dp, end = 40.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(DUGreen)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Sensors,
                        contentDescription = "Geofence",
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Geofence (25m)",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // User Location Marker
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 75.dp, start = 40.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                    .border(1.dp, DUGreen, RoundedCornerShape(14.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(7.dp).background(DUGreen, CircleShape))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "You • ${currentDistanceMeters.toInt()}m away",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                }
            }

            // Vertical Floor Switcher on right edge
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White.copy(alpha = 0.95f))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(20.dp))
                    .padding(vertical = 6.dp, horizontal = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("LEVEL", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
                listOf("3F", "2F", "1F", "G").forEach { floor ->
                    val isFloorActive = selectedFloor == floor
                    Box(
                        modifier = Modifier
                            .padding(vertical = 3.dp)
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(if (isFloorActive) DUGreen else Color.Transparent)
                            .clickable { selectedFloor = floor },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = floor,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isFloorActive) Color.White else Color(0xFF475569)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Step-by-step walkway directions card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFFF8FAFC))
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
                        Icon(
                            imageVector = Icons.Default.DirectionsWalk,
                            contentDescription = "Walk",
                            tint = DUGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Step 1: North Walkway Approach",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Color(0xFF1E293B)
                        )
                    }
                    Text(
                        text = "Next: 35m",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF64748B)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "1. Head northeast past Central Library lawn toward Tech Block B main glass portal.\n2. Ascend East Wing central stairway to Level 2 (Computer Science wing).\n3. Turn right past Server Rack corridor; Lab 204 terminal reader is at doorway.",
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    color = Color(0xFF475569)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Simulation Diagnostics Controls (for immediate evaluation)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = onSimulateArrival,
                modifier = Modifier.weight(1f).testTag("simulate_arrival_button"),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00602B)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.TrackChanges,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Simulate Arrival (0m)", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }

            OutlinedButton(
                onClick = onCalibrateBeacon,
                modifier = Modifier.weight(1f).testTag("calibrate_beacon_button"),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Sensors,
                    contentDescription = null,
                    tint = DUGreen,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Calibrate Beacon", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
            }
        }
    }
}
