package com.example.data.model

enum class SessionStatus(val label: String) {
    UPCOMING("Upcoming"),
    CHECK_IN_AVAILABLE("Check-in Available"),
    LIVE_NOW("Live Now"),
    VERIFIED("Verified"),
    CHECKOUT_REQUIRED("Checkout Required"),
    COMPLETED("Completed"),
    INCOMPLETE("Incomplete"),
    MISSED("Missed Attendance")
}

data class BeaconNode(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val elevationMeters: Int,
    val radiusMeters: Double,
    val floorLevel: String
)

data class Course(
    val code: String,
    val title: String,
    val lecturer: String,
    val ects: Int,
    val creditHours: Int,
    val attendanceRate: Double,
    val attendedSessions: Int,
    val totalSessions: Int,
    val lateArrivals: Int,
    val incompleteCheckouts: Int,
    val department: String,
    val building: String,
    val room: String,
    val beaconNode: BeaconNode
)

data class ScheduleSession(
    val sessionId: String,
    val courseCode: String,
    val courseTitle: String,
    val lecturer: String,
    val dayOfWeek: String, // "Monday", "Tuesday", etc.
    val ethiopianDate: String, // "Tikimt 12, 2016 E.C."
    val gregorianTime: String, // "10:30 AM – 12:30 PM"
    val startHour: Int,
    val startMinute: Int,
    val endHour: Int,
    val endMinute: Int,
    val building: String,
    val room: String,
    val beaconNode: BeaconNode,
    val status: SessionStatus,
    val attendanceWindowOpen: Boolean,
    val countdownMinutes: Int,
    val countdownSeconds: Int,
    val initialCheckInDone: Boolean = false,
    val finalCheckInDone: Boolean = false
)
