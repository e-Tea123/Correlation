package com.correlation.ui.viewmodel.state

import kotlinx.serialization.Serializable

@Serializable
data class CalendarState(
    val temp: Int = 0
) : CorrelationState
