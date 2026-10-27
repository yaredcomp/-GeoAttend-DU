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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight

@Composable
fun RegistrationScreen(
    onNavigateBack: () -> Unit,
    onContinueToLogin: () -> Unit,
    onSuccessRegister: () -> Unit,
    modifier: Modifier = Modifier
) {
    var studentId by remember { mutableStateOf("DU/UG/14298/14") }
    var fullName by remember { mutableStateOf("Yonas Bekele Tadesse") }
    var email by remember { mutableStateOf("yonas.bekele@du.edu.et") }
    var selectedYear by remember { mutableStateOf(4) }
    var selectedSemester by remember { mutableStateOf("Semester II") }
    var hardwareConsent by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier.testTag("registration_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF1E293B)
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(
                    text = "DILLA UNIVERSITY",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = DUGreen
                )
                Text(
                    text = "Student Verification",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Node banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFEEF7F0))
                .border(1.dp, Color(0xFFC7E4CD), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(DUGreen)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("Registrar Verified Node", fontSize = 10.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("• SIS Portal Connected", fontSize = 11.sp, color = DUGreen, fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Student Device & Account Binding",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF1E293B)
                )
                Text(
                    text = "Link your academic identity and enroll this device with the Dilla University Registrar.",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Step Tracker
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Step 1 of 3", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = DUGreen)
            Text("Identity Confirmation", fontSize = 11.sp, color = Color(0xFF64748B))
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Box(modifier = Modifier.weight(1f).height(4.dp).background(DUGreen, RoundedCornerShape(2.dp)))
            Box(modifier = Modifier.weight(1f).height(4.dp).background(Color(0xFFCBD5E1), RoundedCornerShape(2.dp)))
            Box(modifier = Modifier.weight(1f).height(4.dp).background(Color(0xFFCBD5E1), RoundedCornerShape(2.dp)))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Student ID Input
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("University Student ID Number", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF334155))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(DUGreenLight)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text("Valid Format", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = studentId,
                onValueChange = { studentId = it },
                modifier = Modifier.fillMaxWidth().testTag("student_id_input"),
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    Text(
                        text = "Auto-fill",
                        fontSize = 11.sp,
                        color = DUGreen,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable { studentId = "DU/UG/14298/14" }
                            .padding(end = 12.dp)
                    )
                }
            )
            Text("Accepted patterns: DU/R/[No]/[YY] or DU/UG/[No]/[YY]", fontSize = 10.sp, color = Color(0xFF94A3B8))
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Full Name
        Column {
            Text("Full Name (as on Registrar ID)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF334155))
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                modifier = Modifier.fillMaxWidth().testTag("full_name_input"),
                shape = RoundedCornerShape(10.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Email
        Column {
            Text("Official University Email", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF334155))
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth().testTag("email_input"),
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    Text("@du.edu.et", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0369A1), modifier = Modifier.padding(end = 12.dp))
                }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // College & Department
        Column {
            Text("Academic College / Faculty", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF334155))
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF1F5F9))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Text("College of Engineering & Technology (Odaya'a)", fontSize = 12.sp, color = Color(0xFF1E293B))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column {
            Text("Department & Program", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF334155))
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF1F5F9))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Text("Department of Computer Science & Software Engineering", fontSize = 12.sp, color = Color(0xFF1E293B))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Year of study chips
        Text("Year of Study", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF334155))
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            (1..5).forEach { yr ->
                val isSelected = selectedYear == yr
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) DUGreen else Color(0xFFF1F5F9))
                        .clickable { selectedYear = yr }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Year $yr",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else Color(0xFF475569)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Semester Chips
        Text("Active Semester", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF334155))
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            listOf("Semester I", "Semester II").forEach { sem ->
                val isSelected = selectedSemester == sem
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) DUGreen else Color(0xFFF1F5F9))
                        .clickable { selectedSemester = sem }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = sem,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else Color(0xFF475569)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hardware Binding Consent Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF8FAFC))
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.Top) {
                    Checkbox(
                        checked = hardwareConsent,
                        onCheckedChange = { hardwareConsent = it },
                        colors = CheckboxDefaults.colors(checkedColor = DUGreen)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text(
                            text = "Device Hardware Binding Consent",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color(0xFF1E293B)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "I acknowledge that GeoAttend binds to this physical device hardware ID to prevent proxy attendance per Senate Academic Council rules.",
                            fontSize = 11.sp,
                            lineHeight = 15.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFEEF2F6))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("ID: DU-SEC-7F91-NODE", fontSize = 10.sp, color = Color(0xFF475569), fontWeight = FontWeight.SemiBold)
                        Text("Hardware Secured", fontSize = 10.sp, color = DUGreen, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Verify & Continue Button
        Button(
            onClick = onSuccessRegister,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("verify_register_button"),
            colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Verify with Registrar & Continue", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Already registered on this device? ", fontSize = 12.sp, color = Color(0xFF64748B))
            Text(
                text = "Log In",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DUGreen,
                modifier = Modifier.clickable { onContinueToLogin() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campus geofencing ready
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF0FDF4))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier.size(32.dp).clip(CircleShape).background(Color(0xFFDCFCE7)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = DUGreen, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text("Campus Geofencing Ready", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                Text("Attendance windows automatically unlock within lecture halls at Odaya'a, Main, and Amedero Campuses.", fontSize = 11.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center, color = Color(0xFF475569))
            }
        }
    }
}
