package com.correlation.ui.viewmodel.intent

import java.time.LocalDate

sealed class CalendarIntent : CorrelationIntent() {
    data class SelectDay(val day: LocalDate) : CalendarIntent()
}