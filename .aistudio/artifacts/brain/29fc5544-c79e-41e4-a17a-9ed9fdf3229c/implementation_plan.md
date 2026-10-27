# GeoAttend DU — Student Mobile Application

GeoAttend DU Student is an institutional mobile application designed specifically for Dilla University (DU) in Ethiopia. It enforces biometric and cryptographic bi-temporal attendance (initial arrival and final departure check-ins) inside strict classroom geofences under DU Senate Legislation Rule 44.1 (80% minimum attendance threshold for final exam eligibility).

### User Review & Critical Decisions

> [!IMPORTANT]
> The following user preferences were confirmed during clarification and govern the implementation:
> - **Backend & Testing Strategy**: Pre-seeded Dilla University demo data (student Yonas Bekele Tadesse, Software Engineering Year 4, courses CoSc 4124, CoSc 4092, CoSc 4152, CoSc 4182, Odaya'a Campus beacons & classrooms) with seamless Firestore repository integration and test diagnostics.
> - **Map & Spatial Verification**: Interactive stylized campus radar and indoor walkway map with floor-level navigation (Level 3F / 2F / 1F / G) alongside TomTom Maps SDK integration.
> - **Language Localization**: Instant bilingual English and Amharic (EN / አማርኛ) toggle across all core labels, status pills, and academic terms.
> - **Biometric & Cryptographic Security**: Hardware-backed Android BiometricPrompt emulation and ECDSA transaction hashing adhering to Senate integrity protocols without storing raw biometric data.

---

## 1. Overview & Core Concept

- **What It Does**: Enables Dilla University students to verify their presence in assigned lecture halls during tight, university-controlled check-in windows. The system demands two separate verified events per lecture—Initial Arrival (Event A) and Final Departure Checkout (Event B)—to guarantee authentic lecture attendance.
- **Target Audience / Persona**: Dilla University undergraduate and postgraduate students attending lectures and labs across Odaya'a Main Campus and satellite campuses.
- **Key Value**: Eliminates proxy attendance, enforces the 80% Senate examination attendance threshold transparently, protects student rights with audit-trailed correction requests, and guides students to classrooms with indoor navigation.

---

## 2. User Experience & Visual Design

### Key User Flows

```
┌────────────────────────────────────────────────────────────────────────┐
│                        STUDENT ONBOARDING & AUTH                       │
│  Welcome & Policy ─► Registrar Registration ─► SSO Login ─► Reset PWD   │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│                           STUDENT DASHBOARD                            │
│  - Academic Standing (94.2% Rate vs 80% Senate Rule)                   │
│  - Next Scheduled Class with Live Geofence Countdown Clock             │
│  - Today's Timetable (Daily & Weekly Views)                            │
└───────────────────┬───────────────────────────────┬────────────────────┘
                    │                               │
                    ▼                               ▼
┌──────────────────────────────────────┐  ┌──────────────────────────────┐
│       CLASSROOM MAP & NAVIGATION     │  │   SPATIAL VERIFICATION FLOW  │
│  - TomTom 3D Campus Building Render  │  │  Step 1: Enrolled Course     │
│  - Floor Level Switcher (3F/2F/1F/G) │  │  Step 2: Node Coordinates    │
│  - Distance & Indoor Path Guidance   │  │  Step 3: Geofence Assessment │
│  - Arrival Simulator Diagnostics     │  │  Step 4: Hardware Policy     │
└──────────────────────────────────────┘  │  Step 5: Biometric Auth      │
                                          │  Step 6: Ledger Submission   │
                                          └──────────────┬───────────────┘
                                                         │
                                                         ▼
                                          ┌──────────────────────────────┐
                                          │     BI-TEMPORAL WORKFLOW     │
                                          │  - Event A: Initial Arrival  │
                                          │  - Active Class Sentinel     │
                                          │  - Event B: Final Checkout   │
                                          │  - Cryptographic Receipt PDF │
                                          └──────────────────────────────┘
```

1. **Authentication & Identity Binding**:
   - Welcome Screen featuring official Dilla University crest emblem ("University of the Green Land", "ዲላ ዩኒቨርሲቲ").
   - Student Verification & Registration (Step 1 Identity, Step 2 Device Bound, Step 3 Biometrics). Auto-fills valid DU student formats (`DU/UG/14298/14`).
   - Single Sign-On (SSO) login with password toggle, biometric quick-sign-in option, and campus registrar contact links.
   - Portal Credential Reset with SMS / Campus Mail dispatch and 6-digit OTP verification token.
2. **Student Dashboard & Schedule**:
   - Top banner with DU Crest, "GeoAttend DU", Live Sentinel indicator, and student profile picture.
   - Student identity header with dual calendar date (Gregorian & Ethiopian Calendar: *Tikimt 12, 2016 E.C.*).
   - High-contrast stat cards: Cumulative Attendance Rate (94.2%), Sessions (38/40), Senate Exam Limit (80.0%, +14.2% Safe Margin).
   - Next Class Hero Card with countdown badge (`06m:21s`), room coordinates, radar distance indicator, and large primary action button ("ENTER ATTENDANCE VERIFICATION").
3. **Classroom Navigation & TomTom Maps**:
   - Stylized 3D campus layout with architectural isometric projection of Tech Block B, Odaya'a Library, and Engineering Hall.
   - Floor switcher (Level 3F, 2F, 1F, Ground) for vertical indoor positioning.
   - Turn-by-turn indoor walkway directions with simulation diagnostics (Simulate Arrival at 0m, Calibrate Beacon).
4. **Spatial & Biometric Verification**:
   - 6-step verification pipeline:
     1. Enrolled Course check.
     2. Node Coordinates anchor (e.g., Beacon `#DU-OD-B204`).
     3. Geofence Boundary assessment with circular radar (within 25m boundary limit, GPS precision ±2.8m, anti-spoofing check, Wi-Fi RTT and Mock location check).
     4. Hardware-backed security policy disclosure.
     5. Biometric prompt trigger (Android BiometricPrompt modal with fingerprint sensor glyph and Knox Keystore ECDSA signature).
     6. Ledger submission and cryptographic sealing.
5. **Bi-Temporal Dual Attendance**:
   - **Phase 1 (Initial Arrival)**: Verified arrival confirmation card with timestamp (`08:34:12 AM EAT`), Ethiopian date, and ledger hash `DU-TXN-90214-4124-IN`. Displays countdown until Departure Checkout window opens.
   - **Phase 2 (Final Departure Checkout)**: Urgency warning dialog when class end approaches (`08:42 left`), calculating dwell duration (1 hr 41 mins) and warning of unverified early exit.
   - **Final Receipt**: Complete audit trail showing initial check-in + final check-out, master block hash `DU-CHAIN-2024-SEM2-COSC4124-7749`, and option to view/download verified attendance receipt.
6. **Attendance & Punctuality Reports**:
   - Course-by-course breakdown with progress bars and status tags (e.g., *CoSc 4124 Distributed Systems 96.0%*, *SWE 3112 Database Systems 78.4% Below Quorum*).
   - Senate Academic Integrity Disclosure with photo of Odaya'a Main Campus.
   - Recent bi-temporal log cards with initial arrival and final departure timestamps.
7. **Senate Breaches & Attendance Corrections**:
   - Senate 80% Rule Breached notification with immediate disqualification warning.
   - Formal Attendance Correction Request petition with multi-step checklist, excuse classifications (Official Medical Illness, Hardware/GNSS Timeout, Approved University Representation, Lecturer Early Dismissal), file attachment picker, and Senate Code of Conduct Art. 19.3 integrity checkbox.
8. **Student Profile & Settings**:
   - Knox HSM Hardware Binding status, Spatial sensor toggles, Registrar & SIS sync status.
   - Instant language switcher: **EN** (English) and **አም** (Amharic).

### Visual Identity & Theme Tokens

- **Primary**: `#1B7A3E` (DU Forest Green — Authority & Verified Presence)
- **Primary Container**: `#E8F5E9` (Light Institutional Mint Container)
- **Secondary**: `#4A6352` (Muted Slate Green)
- **Warning / Urgency**: `#F59E0B` (Amber Countdown & Deficit Warning)
- **Error / Breach**: `#E41F1F` (Senate Boundary Breach & Disqualification Alert)
- **Background**: `#F8F9FA` (Clean Off-White Surface)
- **Typography**: Roboto Flex / Android M3 with tabular numerals for countdowns and coordinates.

---

## 3. Key Product Decisions & Trade-Offs

- **Decision 1: Standalone Demo Mode with Hybrid Firestore Data Engine**
  - *Chosen Approach*: Implement an in-memory & local-first Firestore synchronization repository with rich Dilla University demo datasets, while wiring the production Firestore data models, security rules, and collections (`users`, `students`, `courses`, `enrollments`, `schedules`, `classSessions`, `attendanceEvents`, `studentAttendance`, `exceptionRequests`).
  - *Why*: Allows immediate, seamless evaluation on the Android Streaming Emulator without requiring the user to authenticate live Google accounts or manually provision cloud projects during first launch.
- **Decision 2: Dual-Event Bi-Temporal Attendance Sentinel**
  - *Chosen Approach*: Track both `InitialArrival` and `FinalDeparture` as distinct immutable cryptographically signed events. Calculate dwell duration and observed presence without claiming uninterrupted continuous tracking.
  - *Why*: Directly satisfies the prompt requirement and Dilla University Senate Regulation 44.1.
- **Decision 3: Integrated Interactive Campus Radar & Indoor Navigation View**
  - *Chosen Approach*: Build a custom Jetpack Compose Canvas radar and 3D campus floor plan navigation widget alongside TomTom Maps integration.
  - *Why*: Delivers the exact architectural visual mockup provided by the user (`screen__9__213c7998.png`) with interactive simulation controls (0m arrive, beacon calibrate) that work out-of-the-box inside the emulator.

---

## 4. Technical Architecture & Data Strategy

### System & Component Hierarchy

```
┌────────────────────────────────────────────────────────────────────────┐
│                        MAIN ACTIVITY & ROOT UI                         │
│  - EnableEdgeToEdge & MaterialTheme (DU Color Palette)                 │
│  - GeoAttendDUApp Scaffold & Bottom Navigation Bar (5 Tabs)            │
│  - Instant English / Amharic Localization Provider                     │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
    ┌───────────────────────────────┼───────────────────────────────┐
    ▼                               ▼                               ▼
┌──────────────────────┐  ┌──────────────────────┐  ┌──────────────────────┐
│  AUTHENTICATION UI   │  │   CORE STUDENT TABS  │  │ ATTENDANCE & VERIFY  │
│ - WelcomeScreen      │  │ - DashboardScreen    │  │ - SpatialVerifyScreen│
│ - RegistrationScreen │  │ - TimetableScreen    │  │ - BiometricDialog    │
│ - LoginScreen        │  │ - AttendanceHistory  │  │ - CheckInConfirm     │
│ - ResetPasswordScreen│  │ - NotificationsScreen│  │ - CheckoutPrompt     │
│                      │  │ - ProfileScreen      │  │ - CorrectionRequest  │
│                      │  │ - CampusMapView      │  │ - ClassDetailsScreen │
└──────────────────────┘  └──────────────────────┘  └──────────────────────┘
                                    │
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│                      VIEWMODEL & STATE LAYER                           │
│  - StudentViewModel (Session, Punctuality, Next Class Countdown)       │
│  - VerificationViewModel (6-Step Pipeline, GPS, Biometrics, Simulator) │
│  - TimetableViewModel (Daily/Weekly Schedules, Room Filtering)         │
│  - AttendanceViewModel (Bi-Temporal History, Senate Breaches, Appeals) │
│  - Language & Preferences State (EN / Amharic toggle)                  │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│                      DATA & REPOSITORY LAYER                           │
│  - AttendanceRepository (Bi-Temporal validation, hash generation)      │
│  - TimetableRepository (Courses, schedules, lecture halls, beacons)   │
│  - StudentRepository (Profile, Knox bindings, registrar records)       │
│  - Mock / Production Firestore Bridge & Security Rules                 │
└────────────────────────────────────────────────────────────────────────┘
```

### Key Data Entities

- **StudentProfile**: ID, full name, email, department, college, year, semester, device Knox ID, cumulative rate.
- **Course**: Course code (`CoSc 4124`), title, instructor name, ECTS, credit hours, classroom node.
- **ScheduleSession**: Schedule ID, course code, day, start time, end time, building, room, geofence center (lat, lon), radius (25m), beacon ID (`#DU-OD-B204`).
- **BiTemporalAttendanceRecord**: Session ID, student ID, initial arrival timestamp, final departure timestamp, initial status, final status, ECDSA ledger hash, dwell time, verification method.
- **AttendanceAppeal**: Petition ID, course ID, session date, excuse category, explanation text, document name, status (`Under Review`, `Approved`, `Rejected`), timestamp.
- **SenateAlert**: Alert ID, title, course, breach percentage, severity (`High Risk`, `Warning`, `Notice`), action deadline.

---

## 5. Implementation Phases & Steps

### Step 1: Branding, Resources & App Icon
- Set application ID and app name ("GeoAttend DU") in `strings.xml` and `metadata.json`.
- Configure custom adaptive icon using the official Dilla University emblem vector / artwork.
- Establish theme colors (`Color.kt`, `Theme.kt`, `Type.kt`) matching Dilla University brand tokens from `DESIGN_34f9bc0e.md`.

### Step 2: Data Models, Repositories & Bilingual Localization
- Create data models for students, courses, schedules, attendance logs, and appeals.
- Create string resources and bilingual Amharic mappings for instant UI switching.
- Implement `AttendanceRepository`, `TimetableRepository`, and `StudentRepository` with pre-seeded Dilla University academic datasets.

### Step 3: Auth & Onboarding Screens
- Implement `WelcomeScreen` with Dilla University crest and institutional credentials.
- Implement `RegistrationScreen` with student identity verification and hardware binding consent.
- Implement `LoginScreen` (SSO) and `ForgotPasswordScreen` with OTP verification.

### Step 4: Core Navigation & Dashboard
- Implement M3 Navigation Bar with 5 destinations: Home, Timetable, Verify, Alerts, Profile.
- Implement `DashboardScreen` displaying academic standing, next class countdown timer, and today's schedule.
- Implement `TimetableScreen` with Daily and Weekly tabs, Ethiopian calendar chips, and room navigation links.

### Step 5: Campus Map & Indoor Walkway Navigation
- Implement `CampusMapView` with 3D isometric building renderings of Tech Block B, Library, Engineering Hall.
- Add floor selector (Level 3F / 2F / 1F / G), distance counter (85m), walkway directions, and simulation controls (0m Arrival, Calibrate Beacon).

### Step 6: 6-Step Verification & Biometric Sentinel
- Implement `SpatialVerificationScreen` with simulation diagnostics (Inside Geofence, Outside Boundary, Weak GPS).
- Implement interactive circular radar showing user distance vs 25m boundary limit, GPS precision ±2.8m, and anti-spoofing checks.
- Implement native `BiometricPromptDialog` bottom sheet with Knox Keystore ECDSA signature.
- Implement `InitialCheckInConfirmedScreen` (Event A) and `UrgentCheckoutScreen` (Event B).
- Implement `FinalAttendanceReceiptScreen` with cryptographic master hash and receipt PDF action.

### Step 7: Attendance Reports, Appeals & Student Profile
- Implement `AttendanceHistoryScreen` with course breakdown, Senate rule 80% indicator, and recent bi-temporal logs.
- Implement `MissingAttendanceWarningScreen` and `AttendanceCorrectionRequestScreen` with document attachment and declaration checkbox.
- Implement `ProfileScreen` with Knox HSM status, sensor toggles, registrar sync, and instant English/Amharic switcher.

### Step 8: Verification & Compilation
- Run `compile_applet` to verify compilation, type safety, and clean build.
