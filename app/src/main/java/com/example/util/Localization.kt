package com.example.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

enum class AppLanguage {
    EN, AM
}

object Strings {
    fun get(key: String, lang: AppLanguage): String {
        val dict = when (lang) {
            AppLanguage.EN -> enStrings
            AppLanguage.AM -> amStrings
        }
        return dict[key] ?: enStrings[key] ?: key
    }

    private val enStrings = mapOf(
        "app_title" to "GeoAttend DU",
        "dilla_university" to "Dilla University",
        "odaya_campus" to "Odaya'a Main Campus",
        "live_sentinel" to "Live Sentinel",
        "nav_home" to "Home",
        "nav_timetable" to "Timetable",
        "nav_verify" to "Verify",
        "nav_alerts" to "Alerts",
        "nav_profile" to "Profile",
        "academic_year" to "Academic Year 2024/2025",
        "overall_rate" to "Overall Rate",
        "sessions" to "Sessions",
        "senate_limit" to "Senate Limit",
        "attendance_window_opens" to "Attendance window opens in",
        "enter_attendance" to "ENTER ATTENDANCE VERIFICATION",
        "today_schedule" to "Today's Schedule",
        "daily_view" to "Daily View",
        "weekly_view" to "Weekly View",
        "classroom_radar" to "Classroom Radar",
        "inside_boundary" to "Inside Boundary",
        "outside_boundary" to "Outside Boundary",
        "authenticate_biometrics" to "Authenticate via Device Biometrics",
        "arrival_verified" to "Arrival Verified Successfully!",
        "final_checkout_required" to "Final Attendance Checkout Required!",
        "attendance_fully_recorded" to "Attendance Fully Recorded!",
        "senate_breach" to "DU Senate 80% Rule Breached",
        "request_correction" to "Request Attendance Correction",
        "sign_out" to "Sign Out from Device",
        "hardware_bound" to "Knox HSM Hardware Bound",
        "attendance_standing" to "Exemplary Standing",
        "exam_eligible" to "Full Exam Eligibility Confirmed",
        "download_pdf" to "Download Verified Receipt PDF"
    )

    private val amStrings = mapOf(
        "app_title" to "ጂኦአቴንድ ዲዩ",
        "dilla_university" to "ዲላ ዩኒቨርሲቲ",
        "odaya_campus" to "ኦዳያ ዋና ግቢ",
        "live_sentinel" to "ቀጥታ ሴንቲነል",
        "nav_home" to "መነሻ",
        "nav_timetable" to "መርሐ-ግብር",
        "nav_verify" to "ማረጋገጫ",
        "nav_alerts" to "ማሳወቂያ",
        "nav_profile" to "መገለጫ",
        "academic_year" to "የትምህርት ዘመን 2016/2017 ዓ.ም",
        "overall_rate" to "ጠቅላላ መገኘት",
        "sessions" to "ክፍለ-ጊዜያት",
        "senate_limit" to "የሴኔት ገደብ",
        "attendance_window_opens" to "የመገኘት መመዝገቢያ የሚከፈተው በ",
        "enter_attendance" to "የመገኘት ማረጋገጫ ጀምር",
        "today_schedule" to "የዛሬ የትምህርት መርሐ-ግብር",
        "daily_view" to "የዕለት እይታ",
        "weekly_view" to "የሳምንት እይታ",
        "classroom_radar" to "የክፍል ራዳር",
        "inside_boundary" to "በክፍሉ ወሰን ውስጥ",
        "outside_boundary" to "ከክፍሉ ወሰን ውጪ",
        "authenticate_biometrics" to "በባዮሜትሪክስ አረጋግጥ",
        "arrival_verified" to "የመጀመሪያ መገኘት ተረጋግጧል!",
        "final_checkout_required" to "የመጨረሻ መገኘት ማረጋገጫ ያስፈልጋል!",
        "attendance_fully_recorded" to "መገኘት ሙሉ በሙሉ ተመዝግቧል!",
        "senate_breach" to "የዲዩ ሴኔት 80% ደንብ ተጥሷል",
        "request_correction" to "የመገኘት ማስተካከያ ጥያቄ አቅርብ",
        "sign_out" to "ከመሳሪያው ውጣ",
        "hardware_bound" to "ኖክስ ኤች.ኤስ.ኤም ደህንነት የተረጋገጠ",
        "attendance_standing" to "ምርጥ የመገኘት ደረጃ",
        "exam_eligible" to "ለፈተና ሙሉ ብቁነት ተረጋግጧል",
        "download_pdf" to "የተረጋገጠ ማረጋገጫ ሰነድ (PDF) አውርድ"
    )
}

val LocalAppLanguage = compositionLocalOf { AppLanguage.EN }
