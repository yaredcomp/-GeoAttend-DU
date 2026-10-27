package com.example.data.model

enum class AttendanceEventType {
    INITIAL_ARRIVAL,
    FINAL_DEPARTURE,
    FULL_SESSION_SEALED
}

data class BiTemporalRecord(
    val recordId: String,
    val courseCode: String,
    val courseTitle: String,
    val sessionDate: String, // "May 18, 2025 • Tikimt 12, 2016 E.C."
    val location: String, // "Block 45 Hall 02"
    val initialArrival: String, // "08:32 AM"
    val initialArrivalTimestamp: String = "08:32:15 AM EAT",
    val finalDeparture: String, // "10:14 AM"
    val finalDepartureTimestamp: String = "10:14:45 AM EAT",
    val status: String, // "Verified Complete", "Late Arrival", "Unverified Early Exit"
    val isLate: Boolean = false,
    val lateGateTime: String? = null,
    val masterBlockHash: String = "DU-CHAIN-2024-SEM2-COSC4124-7749",
    val initialTxnHash: String = "DU-TXN-90214-4124-IN",
    val finalTxnHash: String = "DU-TXN-90214-4124-OUT",
    val observedIntervalMinutes: Int = 108,
    val beaconId: String = "#DU-OD-B204"
)

enum class ExcuseClassification(val labelEn: String, val labelAm: String) {
    MEDICAL("Official Medical Illness", "ህጋዊ የህክምና ህመም ማስረጃ"),
    GNSS_TIMEOUT("Hardware / GNSS Geofence Timeout", "የመሳሪያ / ጂፒኤስ የኔትወርክ መቆራረጥ"),
    UNIVERSITY_REP("Approved University Representation", "የተፈቀደ የዩኒቨርሲቲ ውክልና"),
    EARLY_DISMISSAL("Lecturer Early Dismissal", "በመምህር የተደረገ ቀደምት ስንብት")
}

data class AppealRequest(
    val appealId: String,
    val courseCode: String,
    val sessionDate: String,
    val excuseType: ExcuseClassification,
    val explanation: String,
    val attachedFileName: String?,
    val status: String = "Under Review (Registrar)",
    val submittedAt: String
)

data class SenateWarning(
    val alertId: String,
    val courseCode: String,
    val courseName: String,
    val currentRate: Double,
    val requiredRate: Double = 80.0,
    val deficitSessions: Int,
    val breachDate: String,
    val deadline: String,
    val actionRequired: String
)
