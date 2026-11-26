package com.correlation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.serialization.saved
import com.correlation.ui.viewmodel.effect.CalendarEffect
import com.correlation.ui.viewmodel.intent.CalendarIntent
import com.correlation.ui.viewmodel.state.CalendarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class CalendarViewModel
internal constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel(), IViewModelContract<CalendarState, CalendarIntent, CalendarEffect> {

    private var _savedState: CalendarState by savedStateHandle.saved(
        key = "SAVED_CREATION_STATE",
        init = { CalendarState() }
    )

    private val _stateFlow: MutableStateFlow<CalendarState> = MutableStateFlow(_savedState)
    override val stateFlow: StateFlow<CalendarState> = _stateFlow.asStateFlow()
    private val _effectFlow: MutableStateFlow<CalendarEffect?> = MutableStateFlow(null)
    override val effectFlow: SharedFlow<CalendarEffect?> = _effectFlow.asSharedFlow()

    override fun handleIntent(intent: CalendarIntent){
        when(intent){
            is CalendarIntent.SelectDay ->{
                Log.d("Correlation.CalendarVM", "SelectDay Intent handled")
            }
            else -> {}
        }
    }
}