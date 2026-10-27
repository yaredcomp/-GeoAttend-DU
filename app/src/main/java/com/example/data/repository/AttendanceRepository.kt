package com.example.data.repository

import com.example.data.model.AppealRequest
import com.example.data.model.BiTemporalRecord
import com.example.data.model.ExcuseClassification
import com.example.data.model.SenateWarning
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AttendanceRepository {

    private val _records = MutableStateFlow(
        listOf(
            BiTemporalRecord(
                recordId = "18",
                courseCode = "CoSc 4124: Distributed Systems",
                courseTitle = "Distributed Systems & Cloud Computing",
                sessionDate = "May 18, 2025 • Block 45 Hall 02",
                location = "Block 45 Hall 02",
                initialArrival = "08:32 AM",
                initialArrivalTimestamp = "08:32:14 AM EAT",
                finalDeparture = "10:14 AM",
                finalDepartureTimestamp = "10:14:22 AM EAT",
                status = "Verified Complete",
                isLate = false,
                masterBlockHash = "DU-CHAIN-2024-SEM2-COSC4124-7749",
                initialTxnHash = "DU-TXN-90214-4124-IN",
                finalTxnHash = "DU-TXN-90214-4124-OUT",
                observedIntervalMinutes = 102
            ),
            BiTemporalRecord(
                recordId = "16",
                courseCode = "CoSc 4092: Mobile App Dev",
                courseTitle = "Mobile Application Development",
                sessionDate = "May 16, 2025 • Lab Complex C-04",
                location = "Lab Complex C-04",
                initialArrival = "08:44 AM",
                initialArrivalTimestamp = "08:44:03 AM EAT",
                finalDeparture = "10:30 AM",
                finalDepartureTimestamp = "10:30:11 AM EAT",
                status = "Late Arrival",
                isLate = true,
                lateGateTime = "Late Gate (08:44)",
                masterBlockHash = "DU-CHAIN-2024-SEM2-COSC4092-6612",
                initialTxnHash = "DU-TXN-90214-4092-IN",
                finalTxnHash = "DU-TXN-90214-4092-OUT",
                observedIntervalMinutes = 106
            ),
            BiTemporalRecord(
                recordId = "14",
                courseCode = "CoSc 4182: Computer Security",
                courseTitle = "Computer Security & Cryptography",
                sessionDate = "May 14, 2025 • Network Lab 01",
                location = "Network Lab 01",
                initialArrival = "02:02 PM",
                initialArrivalTimestamp = "02:02:40 PM EAT",
                finalDeparture = "03:58 PM",
                finalDepartureTimestamp = "03:58:19 PM EAT",
                status = "Verified Complete",
                isLate = false,
                masterBlockHash = "DU-CHAIN-2024-SEM2-COSC4182-5531",
                initialTxnHash = "DU-TXN-90214-4182-IN",
                finalTxnHash = "DU-TXN-90214-4182-OUT",
                observedIntervalMinutes = 116
            )
        )
    )
    val records: StateFlow<List<BiTemporalRecord>> = _records.asStateFlow()

    private val _senateWarnings = MutableStateFlow(
        listOf(
            SenateWarning(
                alertId = "SENATE-8821B",
                courseCode = "SWE 3112",
                courseName = "Database Systems • Sec A",
                currentRate = 78.4,
                requiredRate = 80.0,
                deficitSessions = 1,
                breachDate = "Tikimt 8, 2016 E.C. (Oct 19, 2024)",
                deadline = "Friday 5:00 PM EAT",
                actionRequired = "Submit medical excuse or attend departmental review before Friday 5:00 PM EAT."
            )
        )
    )
    val senateWarnings: StateFlow<List<SenateWarning>> = _senateWarnings.asStateFlow()

    private val _appeals = MutableStateFlow(
        listOf(
            AppealRequest(
                appealId = "DU-REG-2024-88219",
                courseCode = "SWE 3112: Database Systems",
                sessionDate = "Tikimt 8, 2016 E.C. (08:30 AM - 10:15 AM)",
                excuseType = ExcuseClassification.MEDICAL,
                explanation = "Visited the Odaya'a Student Clinic at 09:45 AM due to acute gastritis. Medical officer issued sick leave slip #MD-88219.",
                attachedFileName = "DU_Clinic_Medical_Slip_88219.pdf",
                status = "Under Review (Registrar)",
                submittedAt = "2024-10-21 14:32 EAT"
            )
        )
    )
    val appeals: StateFlow<List<AppealRequest>> = _appeals.asStateFlow()

    fun addBiTemporalLog(
        courseCode: String,
        courseTitle: String,
        location: String,
        initialTime: String,
        finalTime: String
    ) {
        val nextId = (_records.value.size + 15).toString()
        val randomHash = (1000..9999).random()
        val record = BiTemporalRecord(
            recordId = nextId,
            courseCode = courseCode,
            courseTitle = courseTitle,
            sessionDate = "Today • $location",
            location = location,
            initialArrival = initialTime,
            initialArrivalTimestamp = "$initialTime:12 AM EAT",
            finalDeparture = finalTime,
            finalDepartureTimestamp = "$finalTime:45 AM EAT",
            status = "Verified Complete",
            masterBlockHash = "DU-CHAIN-2024-SEM2-COSC4124-$randomHash",
            initialTxnHash = "DU-TXN-90214-4124-IN",
            finalTxnHash = "DU-TXN-90214-4124-OUT"
        )
        _records.value = listOf(record) + _records.value
    }

    fun submitAppeal(
        courseCode: String,
        sessionDate: String,
        excuseType: ExcuseClassification,
        explanation: String,
        fileName: String?
    ): Boolean {
        val appeal = AppealRequest(
            appealId = "DU-REG-2024-" + (10000..99999).random(),
            courseCode = courseCode,
            sessionDate = sessionDate,
            excuseType = excuseType,
            explanation = explanation,
            attachedFileName = fileName ?: "DU_Medical_Clinic_Receipt.pdf",
            status = "Pending Review",
            submittedAt = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date()) + " EAT"
        )
        _appeals.value = listOf(appeal) + _appeals.value
        return true
    }
}
