package com.example.ui.screens.auth

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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight

@Composable
fun ForgotPasswordScreen(
    onNavigateBack: () -> Unit,
    onPasswordResetSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var studentId by remember { mutableStateOf("DU/R/14298/14") }
    var selectedChannel by remember { mutableStateOf("SMS") }
    var codeSent by remember { mutableStateOf(true) }
    var otpDigits by remember { mutableStateOf(listOf("4", "8", "2", "9", "1", "0")) }

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
        // Top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier.testTag("reset_password_back_button")
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text("DILLA UNIVERSITY", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                Text("Student Verification", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier.size(56.dp).clip(CircleShape).background(DUGreenLight),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Default.LockReset, contentDescription = null, tint = DUGreen, modifier = Modifier.size(32.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text("Reset Portal Credentials", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Verify your student record with the Dilla University Registrar directory to receive a secure OTP reset code.",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF64748B)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Step 1: Student Identification
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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("1. Student Identification", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFF1E293B))
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(DUGreenLight).padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("DU SIS Connected", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = studentId,
                    onValueChange = { studentId = it },
                    label = { Text("DU Student ID Number") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    trailingIcon = {
                        Box(
                            modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(DUGreenLight).padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text("Record Found", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text("Format: DU/College/Roll/Year • Yohannes Kebede A.", fontSize = 11.sp, color = DUGreen, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Step 2: Delivery Channel
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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("2. Delivery Channel", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFF1E293B))
                    Text("Encrypted Dispatch", fontSize = 10.sp, color = Color(0xFF64748B))
                }

                Spacer(modifier = Modifier.height(10.dp))

                // SMS
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (selectedChannel == "SMS") DUGreenLight else Color(0xFFF8FAFC))
                        .border(1.dp, if (selectedChannel == "SMS") DUGreen else Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                        .clickable { selectedChannel = "SMS" }
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedChannel == "SMS",
                        onClick = { selectedChannel = "SMS" },
                        colors = RadioButtonDefaults.colors(selectedColor = DUGreen)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Send OTP via SMS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(Color(0xFFE0F2FE)).padding(horizontal = 4.dp, vertical = 2.dp)) {
                                Text("Recommended", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0369A1))
                            }
                        }
                        Text("Registered Phone: +251 91 •••• 84 (Ethio Telecom)", fontSize = 11.sp, color = Color(0xFF64748B))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Email
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (selectedChannel == "EMAIL") DUGreenLight else Color(0xFFF8FAFC))
                        .border(1.dp, if (selectedChannel == "EMAIL") DUGreen else Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                        .clickable { selectedChannel = "EMAIL" }
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedChannel == "EMAIL",
                        onClick = { selectedChannel = "EMAIL" },
                        colors = RadioButtonDefaults.colors(selectedColor = DUGreen)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Send reset link to mail", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("Institutional Email: y••••@du.edu.et", fontSize = 11.sp, color = Color(0xFF64748B))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Step 3: Enter Auth Token (OTP)
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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("3. Enter Auth Token", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFF1E293B))
                    Text("05:00 left", fontSize = 11.sp, color = DUGreen, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text("Enter the 6-digit one-time token dispatched to your student contact record:", fontSize = 11.sp, color = Color(0xFF64748B))

                Spacer(modifier = Modifier.height(12.dp))

                // 6 Boxes
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    otpDigits.forEachIndexed { index, digit ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFEEF2F6))
                                .border(1.dp, Color(0xFFCBD5E1), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = digit,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E293B)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Resend Code", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen, modifier = Modifier.clickable { })
                    Text("Ref: DU-AUTH-9022", fontSize = 10.sp, color = Color(0xFF94A3B8))
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Validate Button
        Button(
            onClick = onPasswordResetSuccess,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("validate_password_reset_button"),
            colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Validate & Set New Password", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Back to Sign In",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = DUGreen,
            modifier = Modifier.clickable { onNavigateBack() }
        )
    }
}
