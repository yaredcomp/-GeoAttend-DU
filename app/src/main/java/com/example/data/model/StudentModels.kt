package com.example.data.model

data class StudentProfile(
    val studentId: String = "DU/UG/14298/14",
    val fullName: String = "Yonas Bekele Tadesse",
    val email: String = "yonas.bekele@du.edu.et",
    val college: String = "College of Engineering & Technology (Odaya'a)",
    val department: String = "Department of Computer Science & Software Engineering",
    val program: String = "B.Sc. Software Engineering",
    val yearOfStudy: Int = 4,
    val semester: String = "Semester II",
    val academicYear: String = "2024/2025 (2016/2017 E.C.)",
    val campus: String = "Odaya'a Main Campus",
    val overallAttendanceRate: Double = 94.2,
    val totalSessionsAttended: Int = 38,
    val totalSessionsScheduled: Int = 40,
    val senateMinRequirement: Double = 80.0,
    val hardwareKnoxId: String = "DU-SEC-7F91-NODE",
    val boundDeviceModel: String = "Samsung Galaxy S21 5G (Knox HSM)",
    val nodeSignature: String = "DU-KEY-8199-ECDSA",
    val isBiometricEnabled: Boolean = true,
    val backgroundGeofenceEnabled: Boolean = true,
    val checkoutAlarmsEnabled: Boolean = true
)

data class CollegeInfo(
    val name: String,
    val departments: List<String>
)
