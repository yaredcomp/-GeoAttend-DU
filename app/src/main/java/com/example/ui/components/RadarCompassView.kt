package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.theme.DURed

@Composable
fun RadarCompassView(
    userDistanceMeters: Double = 12.4,
    boundaryRadiusMeters: Double = 25.0,
    precisionMeters: Double = 2.8,
    isInsideBoundary: Boolean = true,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "RadarSweep")
    val sweepPulse by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "SweepPulse"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF0F4FA))
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Header Boundary Limit Tag
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFCBD5E1), RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${boundaryRadiusMeters.toInt()}m Boundary Limit",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF475569)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Radar Graphic Box
            Box(
                modifier = Modifier
                    .size(190.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(180.dp)) {
                    val center = Offset(size.width / 2, size.height / 2)
                    val maxRadius = size.width / 2

                    // Outermost boundary circle (dashed)
                    drawCircle(
                        color = Color(0xFF94A3B8),
                        radius = maxRadius,
                        center = center,
                        style = Stroke(
                            width = 2f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 8f))
                        )
                    )

                    // Inner green perimeter
                    val innerColor = if (isInsideBoundary) Color(0x331B7A3E) else Color(0x33E41F1F)
                    drawCircle(
                        color = innerColor,
                        radius = maxRadius * 0.72f,
                        center = center
                    )

                    // Concentric rings
                    drawCircle(
                        color = if (isInsideBoundary) DUGreen.copy(alpha = 0.4f) else DURed.copy(alpha = 0.4f),
                        radius = maxRadius * 0.72f,
                        center = center,
                        style = Stroke(width = 2.5f)
                    )

                    // Animated Pulse Wave
                    drawCircle(
                        color = if (isInsideBoundary) DUGreen.copy(alpha = (1f - sweepPulse) * 0.5f) else DURed.copy(alpha = (1f - sweepPulse) * 0.5f),
                        radius = (maxRadius * 0.72f) * sweepPulse,
                        center = center,
                        style = Stroke(width = 3f)
                    )

                    // Center Beacon Point
                    drawCircle(
                        color = DUGreen,
                        radius = 8f,
                        center = center
                    )
                }

                // Beacon Center Label
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 28.dp)
                ) {
                    Text(
                        text = "Beacon Center",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF475569)
                    )
                }

                // User Pin Badge (at relative offset)
                val userBadgeColor = if (isInsideBoundary) DUGreen else DURed
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 60.dp, end = if (isInsideBoundary) 10.dp else 110.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(userBadgeColor)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(Color.White, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "You: ${userDistanceMeters.toInt()}m",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Metrics Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Offset
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Navigation,
                            contentDescription = "Offset",
                            tint = Color(0xFF64748B),
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("CURRENT OFFSET", fontSize = 9.sp, color = Color(0xFF64748B), fontWeight = FontWeight.SemiBold)
                    }
                    Text(
                        text = "$userDistanceMeters meters",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isInsideBoundary) Color(0xFF1E293B) else DURed
                    )
                }

                Box(modifier = Modifier.width(1.dp).height(24.dp).background(Color(0xFFE2E8F0)))

                // Precision
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.GpsFixed,
                            contentDescription = "Precision",
                            tint = Color(0xFF64748B),
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("PRECISION", fontSize = 9.sp, color = Color(0xFF64748B), fontWeight = FontWeight.SemiBold)
                    }
                    Text(
                        text = "±${precisionMeters}m (14 Sat)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                }

                Box(modifier = Modifier.width(1.dp).height(24.dp).background(Color(0xFFE2E8F0)))

                // Anti-Spoofing
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = "Security",
                            tint = DUGreen,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("ANTI-SPOOF", fontSize = 9.sp, color = Color(0xFF64748B), fontWeight = FontWeight.SemiBold)
                    }
                    Text(
                        text = "SECURE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DUGreen
                    )
                }
            }
        }
    }
}
