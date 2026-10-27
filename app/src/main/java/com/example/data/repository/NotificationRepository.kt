package com.example.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class NotificationItem(
    val id: String,
    val title: String,
    val body: String,
    val timestamp: String,
    val category: String, // "Senate Warning", "Check-In Window", "Sync", "General"
    val isHighRisk: Boolean = false,
    val isRead: Boolean = false,
    val actionText: String? = null,
    val actionPayload: String? = null
)

object NotificationRepository {
    private val _notifications = MutableStateFlow(
        listOf(
            NotificationItem(
                id = "NOTIF-1",
                title = "DU Senate 80% Rule Breached",
                body = "SWE 3112: Database Systems • Sec A. Attendance level at 78.4% (80.0% min required). Action required before Friday 5:00 PM EAT.",
                timestamp = "2 hrs ago",
                category = "Senate Warning",
                isHighRisk = true,
                actionText = "Review Warning Details & Appeal",
                actionPayload = "SENATE_WARNING"
            ),
            NotificationItem(
                id = "NOTIF-2",
                title = "Check-In Window Opening",
                body = "CoSc 4124 Lab check-in opens in 15 mins at Tech Block B Lab 204. Geofence active (Radius: 25m).",
                timestamp = "15m ago",
                category = "Check-In Window",
                actionText = "Open Window",
                actionPayload = "OPEN_WINDOW"
            ),
            NotificationItem(
                id = "NOTIF-3",
                title = "Bi-Temporal Check-In Confirmed",
                body = "Initial arrival verified at 08:34 AM. Don't forget departure checkout at 10:15 AM (Lecture 11).",
                timestamp = "1h ago",
                category = "Check-In Window"
            ),
            NotificationItem(
                id = "NOTIF-4",
                title = "Attendance Dispute Approved",
                body = "Registrar Directorate approved excuse for Lecture 14 (CoSc 4121). Attendance adjusted +1. Disp-ID: #8921-DU.",
                timestamp = "Yest, 4:20 PM",
                category = "General"
            ),
            NotificationItem(
                id = "NOTIF-5",
                title = "Classroom Relocation Notice",
                body = "Dr. Abrham Desta moved CoSc 4124 tutorial to Block C Hall 2. Geofence anchor updated (Lat: 6.4082, Lon: 38.3129).",
                timestamp = "2d ago",
                category = "General"
            ),
            NotificationItem(
                id = "NOTIF-6",
                title = "Weekly Punctuality Summary",
                body = "96% overall attendance rate across 5 registered modules this week. All 5 sessions validated on-site.",
                timestamp = "3d ago",
                category = "General"
            )
        )
    )
    val notifications: StateFlow<List<NotificationItem>> = _notifications.asStateFlow()

    fun markAllAsRead() {
        _notifications.value = _notifications.value.map { it.copy(isRead = true) }
    }
}
