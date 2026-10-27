package com.example.data.repository

import com.example.data.model.BeaconNode
import com.example.data.model.Course
import com.example.data.model.ScheduleSession
import com.example.data.model.SessionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object TimetableRepository {

    val beaconLab204 = BeaconNode(
        id = "#DU-OD-B204",
        name = "Lab 204 Terminal Anchor",
        latitude = 6.4061,
        longitude = 38.3134,
        elevationMeters = 1570,
        radiusMeters = 25.0,
        floorLevel = "2F"
    )

    val beaconHall101 = BeaconNode(
        id = "#DU-OD-H101",
        name = "Block A Hall 101 Beacon",
        latitude = 6.4065,
        longitude = 38.3128,
        elevationMeters = 1568,
        radiusMeters = 30.0,
        floorLevel = "1F"
    )

    val beaconSeminar3 = BeaconNode(
        id = "#DU-OD-S003",
        name = "New Science Bldg LH-1",
        latitude = 6.4070,
        longitude = 38.3140,
        elevationMeters = 1572,
        radiusMeters = 25.0,
        floorLevel = "G"
    )

    val beaconNetLab1 = BeaconNode(
        id = "#DU-OD-N001",
        name = "Network Lab 01 Terminal",
        latitude = 6.4058,
        longitude = 38.3130,
        elevationMeters = 1575,
        radiusMeters = 20.0,
        floorLevel = "3F"
    )

    val enrolledCourses = listOf(
        Course(
            code = "CoSc 4124",
            title = "Distributed Systems & Cloud Computing",
            lecturer = "Dr. Abrham Desta (PhD)",
            ects = 4,
            creditHours = 3,
            attendanceRate = 96.0,
            attendedSessions = 24,
            totalSessions = 25,
            lateArrivals = 0,
            incompleteCheckouts = 0,
            department = "Computer Science",
            building = "Tech Block B",
            room = "Lab 204",
            beaconNode = beaconLab204
        ),
        Course(
            code = "CoSc 4092",
            title = "Mobile Application Development",
            lecturer = "Dr. Mahlet A. / Mr. Henok Girma",
            ects = 5,
            creditHours = 3,
            attendanceRate = 92.5,
            attendedSessions = 22,
            totalSessions = 24,
            lateArrivals = 1,
            incompleteCheckouts = 0,
            department = "Software Engineering",
            building = "Block A",
            room = "Hall 101",
            beaconNode = beaconHall101
        ),
        Course(
            code = "CoSc 4152",
            title = "Compiler Design",
            lecturer = "Dr. Yohannes G. / Dr. Worku Alemayehu",
            ects = 4,
            creditHours = 3,
            attendanceRate = 88.0,
            attendedSessions = 20,
            totalSessions = 23,
            lateArrivals = 2,
            incompleteCheckouts = 1,
            department = "Computer Science",
            building = "New Science Bldg",
            room = "LH-1",
            beaconNode = beaconSeminar3
        ),
        Course(
            code = "CoSc 4182",
            title = "Computer Security & Cryptography",
            lecturer = "Ato Daniel K. / Ms. Bethlehem Tsegaye",
            ects = 4,
            creditHours = 3,
            attendanceRate = 100.0,
            attendedSessions = 20,
            totalSessions = 20,
            lateArrivals = 0,
            incompleteCheckouts = 0,
            department = "Computer Science",
            building = "Tech Block C",
            room = "Room 302",
            beaconNode = beaconNetLab1
        )
    )

    private val _sessions = MutableStateFlow(
        listOf(
            ScheduleSession(
                sessionId = "SESS-4092-MON",
                courseCode = "CoSc 4092",
                courseTitle = "Mobile Application Development",
                lecturer = "Mr. Henok Girma",
                dayOfWeek = "Monday",
                ethiopianDate = "Tikimt 12, 2016 E.C.",
                gregorianTime = "08:30 – 10:15 EAT",
                startHour = 8,
                startMinute = 30,
                endHour = 10,
                endMinute = 15,
                building = "Block A",
                room = "Hall 101",
                beaconNode = beaconHall101,
                status = SessionStatus.VERIFIED,
                attendanceWindowOpen = false,
                countdownMinutes = 0,
                countdownSeconds = 0,
                initialCheckInDone = true,
                finalCheckInDone = true
            ),
            ScheduleSession(
                sessionId = "SESS-4124-MON",
                courseCode = "CoSc 4124",
                courseTitle = "Distributed Systems & Cloud Computing",
                lecturer = "Dr. Abrham Desta",
                dayOfWeek = "Monday",
                ethiopianDate = "Tikimt 12, 2016 E.C.",
                gregorianTime = "10:30 AM – 12:30 PM (2 hrs)",
                startHour = 10,
                startMinute = 30,
                endHour = 12,
                endMinute = 30,
                building = "Tech Block B",
                room = "Lab 204",
                beaconNode = beaconLab204,
                status = SessionStatus.LIVE_NOW,
                attendanceWindowOpen = true,
                countdownMinutes = 6,
                countdownSeconds = 21,
                initialCheckInDone = false,
                finalCheckInDone = false
            ),
            ScheduleSession(
                sessionId = "SESS-4152-MON",
                courseCode = "CoSc 4152",
                courseTitle = "Compiler Design",
                lecturer = "Dr. Worku Alemayehu",
                dayOfWeek = "Monday",
                ethiopianDate = "Tikimt 12, 2016 E.C.",
                gregorianTime = "02:00 – 04:00 EAT",
                startHour = 14,
                startMinute = 0,
                endHour = 16,
                endMinute = 0,
                building = "New Science Bldg",
                room = "Seminar Hall 3",
                beaconNode = beaconSeminar3,
                status = SessionStatus.UPCOMING,
                attendanceWindowOpen = false,
                countdownMinutes = 180,
                countdownSeconds = 0
            ),
            ScheduleSession(
                sessionId = "SESS-4182-MON",
                courseCode = "CoSc 4182",
                courseTitle = "Computer Security & Cryptography",
                lecturer = "Ms. Bethlehem Tsegaye",
                dayOfWeek = "Monday",
                ethiopianDate = "Tikimt 12, 2016 E.C.",
                gregorianTime = "04:15 – 05:45 EAT",
                startHour = 16,
                startMinute = 15,
                endHour = 17,
                endMinute = 45,
                building = "Tech Block C",
                room = "Room 302",
                beaconNode = beaconNetLab1,
                status = SessionStatus.UPCOMING,
                attendanceWindowOpen = false,
                countdownMinutes = 320,
                countdownSeconds = 0
            )
        )
    )

    val sessions: StateFlow<List<ScheduleSession>> = _sessions.asStateFlow()

    fun markSessionInitialCheckIn(sessionId: String) {
        _sessions.value = _sessions.value.map {
            if (it.sessionId == sessionId) {
                it.copy(initialCheckInDone = true)
            } else it
        }
    }

    fun markSessionFinalCheckIn(sessionId: String) {
        _sessions.value = _sessions.value.map {
            if (it.sessionId == sessionId) {
                it.copy(finalCheckInDone = true, status = SessionStatus.COMPLETED)
            } else it
        }
    }
}
