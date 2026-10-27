package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("GeoAttend DU", appName)
  }

  @Test
  fun `verify student profile and timetable`() {
    val profile = com.example.data.repository.StudentRepository.studentProfile.value
    assertEquals("DU/UG/14298/14", profile.studentId)
    assertEquals("Yonas Bekele Tadesse", profile.fullName)
    assertEquals(94.2, profile.overallAttendanceRate, 0.01)

    val courses = com.example.data.repository.TimetableRepository.enrolledCourses
    assertEquals(4, courses.size)
  }
}
