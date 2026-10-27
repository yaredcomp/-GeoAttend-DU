package com.example.ui.screens.history

import android.widget.Toast
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
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ExcuseClassification
import com.example.ui.theme.DUAmber
import com.example.ui.theme.DUGreen
import com.example.ui.theme.DUGreenLight
import com.example.ui.theme.DURed
import com.example.ui.viewmodel.AppViewModel

@Composable
fun AttendanceCorrectionRequestScreen(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedExcuse by remember { mutableStateOf(ExcuseClassification.MEDICAL) }
    var explanationText by remember {
        mutableStateOf("I visited the Odaya'a Student Clinic at 09:45 AM due to acute gastritis. Medical officer issued sick leave slip #MD-88219 verifying urgent treatment during the lecture window.")
    }
    var attachedFile by remember { mutableStateOf<String?>("DU_Clinic_Medical_Slip_88219.pdf") }
    var integrityConfirmed by remember { mutableStateOf(true) }

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
                modifier = Modifier.testTag("correction_back_button")
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Attendance Correction Request", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text("Registrar & Senate Verification", fontSize = 10.sp, color = Color(0xFF64748B))
            }
            Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(DUGreen), contentAlignment = Alignment.Center) {
                Text("YB", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Senate Petition Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFEEF7F0))
                .border(1.dp, Color(0xFFCCE8D2), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(DUGreenLight).padding(horizontal = 6.dp, vertical = 2.dp)) {
                        Text("SENATE LEGISLATION 44.1", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                    }
                    Box(modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(Color(0xFFFEF3C7)).padding(horizontal = 6.dp, vertical = 2.dp)) {
                        Text("Window: 3 Days Left", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text("Academic Attendance Appeal", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text(
                    text = "Official statutory grievance petition for lecture presence reinstatement & clinic reconciliation.",
                    fontSize = 11.sp,
                    color = Color(0xFF475569)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Session #19 Grievance Registry", fontSize = 10.sp, color = Color(0xFF64748B))
                    Text("DU-REG-2024", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DUGreen)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 1. Affected Course & Session
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
                    Text("1. AFFECTED COURSE & SESSION", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
                    Text("Auto-Synced", fontSize = 10.sp, color = DUGreen, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text("SWE 3112: Database Systems", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text("College of Engineering & Technology • Lab 04", fontSize = 11.sp, color = Color(0xFF64748B))
                Text("Tikimt 8, 2016 E.C. (Oct 19, 2024) • 08:30 AM - 10:15 AM", fontSize = 11.sp, color = Color(0xFF475569))
                Text("Instructor: Dr. Tsegaye Kebede", fontSize = 11.sp, color = Color(0xFF64748B))

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Marked Absent (Unverified Departure)", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DURed)
                    Text("Terminal #B-04", fontSize = 10.sp, color = Color(0xFF64748B))
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. Excuse Classification
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
                    Text("2. EXCUSE CLASSIFICATION", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
                    Text("Required", fontSize = 10.sp, color = DURed, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(10.dp))

                ExcuseClassification.entries.forEach { excuse ->
                    val isSelected = selectedExcuse == excuse
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) DUGreenLight else Color(0xFFF8FAFC))
                            .clickable { selectedExcuse = excuse }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { selectedExcuse = excuse },
                            colors = RadioButtonDefaults.colors(selectedColor = DUGreen)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text(excuse.labelEn, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Text(excuse.labelAm, fontSize = 10.sp, color = Color(0xFF64748B))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 3. Explanation & Circumstances
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
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("3. EXPLANATION & CIRCUMSTANCES", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
                    Text("${explanationText.length} / 600", fontSize = 10.sp, color = Color(0xFF64748B))
                }

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = explanationText,
                    onValueChange = { explanationText = it },
                    modifier = Modifier.fillMaxWidth().height(100.dp).testTag("appeal_explanation_input"),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text("Include timestamps & reference ID from clinic or faculty letter.", fontSize = 10.sp, color = Color(0xFF94A3B8))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 4. Supporting Document
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
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("4. SUPPORTING DOCUMENT", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF64748B))
                    Text("Max 5MB", fontSize = 10.sp, color = Color(0xFF64748B))
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (attachedFile != null) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFEEF7F0))
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.PictureAsPdf, contentDescription = null, tint = DUGreen, modifier = Modifier.size(22.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(attachedFile!!, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                                Text("1.4 MB • Clinic Verified Seal", fontSize = 10.sp, color = DUGreen, fontWeight = FontWeight.SemiBold)
                            }
                        }
                        IconButton(onClick = { attachedFile = null }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove", tint = Color(0xFF94A3B8), modifier = Modifier.size(18.dp))
                        }
                    }
                } else {
                    OutlinedButton(
                        onClick = { attachedFile = "DU_Clinic_Medical_Slip_88219.pdf" },
                        modifier = Modifier.fillMaxWidth().height(44.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(imageVector = Icons.Default.UploadFile, contentDescription = null, tint = DUGreen, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Attach Medical Slip or Official Letter", fontSize = 12.sp, color = DUGreen)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Senate Code of Conduct Checkbox
        Row(verticalAlignment = Alignment.Top) {
            Checkbox(
                checked = integrityConfirmed,
                onCheckedChange = { integrityConfirmed = it },
                colors = CheckboxDefaults.colors(checkedColor = DUGreen)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text("Senate Code of Conduct (Art. 19.3)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                Text(
                    text = "I solemnly declare under Dilla University Academic Integrity Charter that this petition and its attached medical certificate are genuine and authentic. I acknowledge that submitting forged medical excuses will result in automatic semester suspension and referral to the University Disciplinary Committee.",
                    fontSize = 10.sp,
                    lineHeight = 14.sp,
                    color = Color(0xFF475569)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Submit Button
        Button(
            onClick = {
                viewModel.submitCorrectionAppeal(
                    courseCode = "SWE 3112: Database Systems",
                    sessionDate = "Tikimt 8, 2016 E.C.",
                    excuseType = selectedExcuse,
                    explanation = explanationText,
                    fileName = attachedFile
                )
                Toast.makeText(context, "Appeal submitted to Academic Commission", Toast.LENGTH_SHORT).show()
            },
            enabled = integrityConfirmed,
            modifier = Modifier.fillMaxWidth().height(50.dp).testTag("submit_appeal_button"),
            colors = ButtonDefaults.buttonColors(containerColor = DUGreen),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Submit Appeal to Academic Commission", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = { viewModel.navigateBack() },
            modifier = Modifier.fillMaxWidth().height(42.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.BookmarkBorder, contentDescription = null, tint = Color(0xFF475569), modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Save Administrative Draft", fontSize = 12.sp, color = Color(0xFF475569))
        }
    }
}
