---
name: GeoAttend DU Student
colors:
  surface: '#f9f9ff'
  surface-dim: '#cfdaf2'
  surface-bright: '#f9f9ff'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f0f3ff'
  surface-container: '#e7eeff'
  surface-container-high: '#dee8ff'
  surface-container-highest: '#d8e3fb'
  on-surface: '#111c2d'
  on-surface-variant: '#3f493f'
  inverse-surface: '#263143'
  inverse-on-surface: '#ecf1ff'
  outline: '#6f7a6e'
  outline-variant: '#becabc'
  surface-tint: '#026d33'
  primary: '#00602b'
  on-primary: '#ffffff'
  primary-container: '#1b7a3e'
  on-primary-container: '#abffb9'
  inverse-primary: '#80da93'
  secondary: '#4b6453'
  on-secondary: '#ffffff'
  secondary-container: '#cae7d1'
  on-secondary-container: '#4f6857'
  tertiary: '#754900'
  on-tertiary: '#ffffff'
  tertiary-container: '#965e00'
  on-tertiary-container: '#ffe8d1'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#9bf7ad'
  primary-fixed-dim: '#80da93'
  on-primary-fixed: '#00210b'
  on-primary-fixed-variant: '#005225'
  secondary-fixed: '#cde9d4'
  secondary-fixed-dim: '#b2cdb8'
  on-secondary-fixed: '#082013'
  on-secondary-fixed-variant: '#344c3c'
  tertiary-fixed: '#ffddb8'
  tertiary-fixed-dim: '#ffb95f'
  on-tertiary-fixed: '#2a1700'
  on-tertiary-fixed-variant: '#653e00'
  background: '#f9f9ff'
  on-background: '#111c2d'
  surface-variant: '#d8e3fb'
typography:
  display-lg:
    fontFamily: Roboto Flex
    fontSize: 57px
    fontWeight: '400'
    lineHeight: 64px
    letterSpacing: -0.25px
  headline-lg:
    fontFamily: Roboto Flex
    fontSize: 32px
    fontWeight: '600'
    lineHeight: 40px
    letterSpacing: 0px
  headline-lg-mobile:
    fontFamily: Roboto Flex
    fontSize: 28px
    fontWeight: '600'
    lineHeight: 36px
    letterSpacing: 0px
  headline-md:
    fontFamily: Roboto Flex
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
    letterSpacing: 0px
  headline-sm:
    fontFamily: Roboto Flex
    fontSize: 20px
    fontWeight: '500'
    lineHeight: 28px
    letterSpacing: 0.15px
  title-lg:
    fontFamily: Roboto Flex
    fontSize: 22px
    fontWeight: '500'
    lineHeight: 28px
    letterSpacing: 0px
  title-md:
    fontFamily: Roboto Flex
    fontSize: 16px
    fontWeight: '600'
    lineHeight: 24px
    letterSpacing: 0.15px
  title-sm:
    fontFamily: Roboto Flex
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 20px
    letterSpacing: 0.1px
  body-lg:
    fontFamily: Roboto Flex
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
    letterSpacing: 0.5px
  body-md:
    fontFamily: Roboto Flex
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
    letterSpacing: 0.25px
  body-sm:
    fontFamily: Roboto Flex
    fontSize: 12px
    fontWeight: '400'
    lineHeight: 16px
    letterSpacing: 0.4px
  label-lg:
    fontFamily: Roboto Flex
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 20px
    letterSpacing: 0.1px
  label-md:
    fontFamily: Roboto Flex
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.5px
  label-sm:
    fontFamily: Roboto Flex
    fontSize: 11px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.5px
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  gutter: 1rem
  gutter-sm: 0.5rem
  margin: 1rem
  margin-tablet: 1.5rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 1rem
  space-lg: 1.5rem
  space-xl: 2rem
---

## Brand & Style

This design system serves Dilla University's institutional mobile experience, establishing an operational, high-integrity interface for location-verified attendance. The brand balances governmental academic authority with effortless field utility for students moving through campus environments under fluctuating network and sunlight conditions.

The visual style is rooted in canonical Material 3 (Material You) with an institutional, utility-first expression. It eschews decorative gradients, simulated skeuomorphic scanner graphics, or trendy translucent blurs in favor of strict tonal surface hierarchy, precise status signaling, and immediate spatial awareness. The interface conveys structural reliability, cryptographic certainty, and rapid task completion: students open the app, verify location boundary parameters, submit attendance with native biometric confirmation, and proceed to their lectures without friction.

## Colors

The palette establishes an authoritative institutional hierarchy anchored by Dilla University's official academic green. Contrast ratios meet strict WCAG AAA guidelines across core text and interactive states to maintain legibility under harsh equatorial midday sunlight.

### Color Roles & Semantic Application
- **Primary (`#1B7A3E`):** Represents institutional authentication, verified spatial presence, primary action buttons, active navigation markers, and verified system badges.
- **Primary Container (`#E8F5E9`):** Low-strain, high-legibility container for verified status badges, within-geofence radar banners, and highlighted class cards.
- **Secondary (`#4A6352`):** Muted slate-green reserved for contextual filters, secondary chip outlines, and supporting navigational metadata.
- **Tertiary / Warning (`#F59E0B`):** Reserved for temporal urgency: countdown timers, attendance windows opening in under 5 minutes, and provisional or pending verification states.
- **Error / Boundary Breach (`#E41F1F`):** Used explicitly for out-of-bounds geofence warnings, failed cryptographic/biometric attempts, and missed lecture alerts.
- **Surface Background (`#F8F9FA`):** Soft, neutral foundational ground that minimizes glare on OLED and IPS mobile panels.
- **Surface Card (`#FFFFFF`):** High-clarity elevated containers framing actionable course blocks and timetable entries.
- **Text Primary (`#1E293B`):** Deep slate providing high visual acuity for course titles, student identifiers, and room numbers.
- **Text Secondary (`#64748B`):** Balanced neutral for timestamps, lecture hall capacities, and academic instructor credentials.
- **Outline / Border (`#E2E8F0`):** Precise 1px structural boundaries defining inactive cards, dividers, and resting form fields.

## Typography

Typography relies completely on the variable axes of `Roboto Flex`, aligning with native Android performance standards. The typographic system delivers immediate scanning speed, high distinction between similar characters (such as uppercase `I`, lowercase `l`, and digit `1`), and rigid numeric alignment.

Tabular figures (`font-variant-numeric: tabular-nums`) must be enabled universally for countdown clocks, GPS coordinate readouts, room identifiers (e.g., `TECH-204`), and Student ID fields (`DU/R/10492/14`). Large titles scale down systematically on mobile viewports under 380dp width to eliminate awkward word breaks in multi-word course names.

## Layout & Spacing

The layout is built upon an 8dp baseline grid (with a 4dp micro-step for compact badges, icons, and chip padding). Mobile layouts adhere to a single-column fluid card stack between 320dp and 599dp, protected by `margin` (16dp). Tablet landscape layouts (600dp+) split into a master-detail split screen with 24dp margins and gutters.

### Layout Rules
- **Touch Target Integrity:** Every interactive trigger (attendance button, chip filter, navigation item) enforces a strict minimum bounding box of 48dp x 48dp, even when visual badge bounds are smaller.
- **Vertical Rhythm:** Screen-level content flows with `space-md` (16dp) gaps between structural cards. Section groups (e.g., "Active Attendance Windows" vs. "Upcoming Classes") use `space-xl` (32dp) separation.
- **Canvas Boundaries:** Global viewports enforce bottom content inset handling that dynamically clears the Material 3 Navigation Bar (80dp height) plus system gesture insets.

## Elevation & Depth

Depth is articulated through Material 3 tonal elevation and crisp structural boundaries rather than dramatic, blurry drop shadows. This keeps the user interface performant on mid-range and budget Android hardware standard across the student body.

- **Level 0 (Flat):** Surface background (`#F8F9FA`). No shadow, no outline.
- **Level 1 (Resting Cards & Containers):** Pure white background (`#FFFFFF`) with a 1px solid stroke in Border/Outline (`#E2E8F0`). Under high ambient lighting or low-end displays, this outline prevents edge bleeding. A minimal ambient shadow (`0px 1px 3px rgba(30, 41, 59, 0.06)`) gives separation.
- **Level 2 (Active/Hovered Cards & Interactive Bottom Sheets):** Elevated surface overlay featuring subtle tinting and soft elevation (`0px 4px 12px rgba(30, 41, 59, 0.08)`). Used for the native biometric verification modal and contextual schedule pickers.
- **Level 3 (Floating Overlays):** Applied to persistent geofence verification pills and snackbar status messages (`0px 8px 24px rgba(30, 41, 59, 0.12)`).
- **Navigation Bar:** Fixed at Level 2 elevation with a 1px top boundary rule (`#E2E8F0`) to ensure contrast against underlying scrolling lists.

## Shapes

The design system applies a roundedness index of `2`, providing a modern, disciplined aesthetic that feels native to modern Android versions without appearing overly playful or toy-like.

- **Standard Cards:** 12dp to 16dp corner radius (`rounded-lg`), matching Material 3 card container specifications.
- **Interactive Buttons:** 12dp corner radius for filled, tonal, and outlined buttons, providing a unified tactile target.
- **Chips & Status Pills:** Full pill-radius (9999px) to communicate real-time transient state, distinguishing them immediately from interactive rectangular cards.
- **Bottom Sheets & Modal Surfaces:** 24dp top-left and top-right radii, conforming to canonical Android bottom sheet behavior.
- **Text Inputs:** 8dp to 12dp corner radius for consistent form structure.

## Components

### Buttons
- **Primary Attendance Action:** Full-width filled container (`#1B7A3E`) with high-contrast text (`#FFFFFF`) in `label-lg`. Enforces a height of 52dp with 12dp corner radius. When biometric verification is required, it houses the standard Android biometric icon prefix without fake skeuomorphic prints.
- **Secondary / Outlined Action:** 1px border (`#E2E8F0`), background transparent, text `#1B7A3E`. Used for "View Syllabus", "Report GPS Issue", and auxiliary actions.
- **Disabled State:** Solid fill (`#E2E8F0`) with muted slate text (`#94A3B8`). Applied when the student is detected outside the geofence perimeter or the attendance window has closed.

### Status Chips & Badges
- **Within Geofence:** Container `#E8F5E9`, text `#1B7A3E`, 1px border `#A3D9B1`. Includes a localized pulsing dot icon.
- **Outside Boundary:** Container `#FEE2E2`, text `#E41F1F`, 1px border `#FCA5A5`. Warns of GPS radial mismatch.
- **Window Active:** Container `#FEF3C7`, text `#B45309`, 1px border `#FCD34D`. Shows remaining time using tabular numerals (`04:59 left`).
- **Verified:** Container `#E8F5E9`, text `#1B7A3E`, with a solid checkmark icon prefix.

### Academic Information Cards
- **Structure:** Surface `#FFFFFF`, 16dp rounded corners, 1px `#E2E8F0` stroke, 16dp internal padding.
- **Hierarchy:** Top row houses the Course Code (`CS-401`) and Status Chip. Center area features the Course Title (`Distributed Systems`) in `title-md`, followed by Instructor Name, Hall Number (`Block 45, Hall 02`), and scheduled timeslot.
- **Action Strip:** Sits at the bottom of the card with an integrated distance indicator (e.g., `12m from lecture hall centerpoint`).

### Navigation Bar
- Standard 5-destination Material 3 bar: **Home**, **Timetable**, **Attendance**, **Notifications**, **Profile**.
- Active destination is framed in an elongated oval pill (`#E8F5E9`) with primary icon and label in `#1B7A3E`. Inactive destinations use `#64748B`.
- Container height is 80dp, anchored to the display bottom edge, supporting Android edge-to-edge transparent navigation.

### Form Inputs & Selectors
- Outlined style with resting border `#E2E8F0`, transitioning to a 2px `#1B7A3E` stroke on focus.
- Labels float to the top perimeter in `body-sm`. Error messages appear below in `label-sm` using `#E41F1F`.

### Native Biometric Confirmation Trigger
- Bottom sheet layout anchored to the system BiometricPrompt API guidelines.
- Features real-time GPS coordinate confirmation, clear cryptographic verification hash indicator, and instantaneous response feedback without decorative UI delay.