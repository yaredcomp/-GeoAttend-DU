package com.example.ui.screens.auth

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var studentIdOrEmail by remember { mutableStateOf("DU/UG/14298/14") }
    var password by remember { mutableStateOf("••••••••••••") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberDevice by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Dilla University Crest Logo
        Image(
            painter = painterResource(id = R.drawable.du_logo),
            contentDescription = "DU Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "GeoAttend DU",
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF1E293B)
        )

        Text(
            text = "INSTITUTIONAL STUDENT SINGLE SIGN-ON (SSO)",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF475569)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFE8F5E9))
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(6.dp).background(DUGreen, CircleShape))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Odaya'a Main Campus • Academic Year 2024/2025",
                    fontSize = 11.sp,
                    color = DUGreen,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Card Container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(18.dp))
                .padding(20.dp)
        ) {
            Column {
                Text(
                    text = "Student ID or Institutional Email",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF334155)
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = studentIdOrEmail,
                    onValueChange = { studentIdOrEmail = it },
                    placeholder = { Text("e.g. DU/R/14298/14 or name@du.edu.et", fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth().testTag("login_id_input"),
                    shape = RoundedCornerShape(10.dp),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = Color(0xFF64748B))
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "University Password",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF334155)
                    )
                    Text(
                        text = "Forgot Password?",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = DUGreen,
                        modifier = Modifier
                            .clickable { onForgotPasswordClick() }
                            .testTag("forgot_password_link")
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth().testTag("login_password_input"),
                    shape = RoundedCornerShape(10.dp),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = Color(0xFF64748B))
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle password"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Remember device toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Remember this device", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        Text("Hardware-bound cryptographic token", fontSize = 11.sp, color = Color(0xFF64748B))
                    }
                    Switch(
                        checked = rememberDevice,
                        onCheckedChange = { rememberDevice = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = DUGreen)
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Submit button
                Button(
                    onClick = onLoginSuccess,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("sign_in_submit_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Sign In to GeoAttend", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Divider
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFCBD5E1))
            Text("   OR AUTHENTICATE WITH   ", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF94A3B8))
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFCBD5E1))
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Biometric Quick Login Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                .clickable { onLoginSuccess() }
                .padding(14.dp)
                .testTag("biometric_quick_login")
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(DUGreenLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Fingerprint, contentDescription = null, tint = DUGreen, modifier = Modifier.size(26.dp))
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text("Use Android Fingerprint / Face Unlock", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                    Text("• Device enrolled: SM-G991B (Knox Secured)", fontSize = 11.sp, color = DUGreen)
                }

                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = Color(0xFF94A3B8))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("New student to Dilla University? ", fontSize = 12.sp, color = Color(0xFF64748B))
            Text(
                text = "Register account",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DUGreen,
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Support Contacts Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFFF1F5F9))
                .padding(14.dp)
        ) {
            Column {
                Text("DU ICT & ACADEMIC SUPPORT", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .padding(10.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Phone, contentDescription = null, tint = DUGreen, modifier = Modifier.size(13.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("ICT Helpdesk", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF334155))
                            }
                            Text("Ext. 6088", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .padding(10.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = DUGreen, modifier = Modifier.size(13.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Registrar Office", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF334155))
                            }
                            Text("registrar@du.edu.et", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                        }
                    }
                }
            }
        }
    }
}
