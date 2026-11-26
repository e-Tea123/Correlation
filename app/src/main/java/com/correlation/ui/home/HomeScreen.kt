package com.correlation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.alejo.compose_calendar.SimpleComposeCalendar
import java.time.LocalDate

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               onDayClick:(LocalDate, List<LocalDate>) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleComposeCalendar(
            eventIndicator = null,
            isContentClickable = true,
            onDayClick = onDayClick
        )
    }
}


@Preview
@Composable
fun PreviewHomeScreen(){
    val onDayClick = {day: LocalDate, dayList: List<LocalDate>->}
    HomeScreen(onDayClick = onDayClick)
}