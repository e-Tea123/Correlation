package com.correlation.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.correlation.ui.viewmodel.effect.CorrelationEffect
import com.correlation.ui.viewmodel.intent.CorrelationIntent
import com.correlation.ui.viewmodel.state.CorrelationState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

sealed interface IViewModelContract<STATE : CorrelationState, INTENT : CorrelationIntent, EFFECT : CorrelationEffect> {
    val stateFlow: StateFlow<STATE>

    val dispatcher: (INTENT) -> Unit
        get() = { intent -> handleIntent(intent) }

    val effectFlow: SharedFlow<EFFECT?>

    fun handleIntent(intent: INTENT)

    data class StateDispatchEvent<STATE, INTENT, EFFECT>(
        val state: STATE,
        val dispatcher: (INTENT) -> Unit,
        val effectFlow: SharedFlow<EFFECT?>
    )

    @Composable
    fun use(lifecycleOwner: LifecycleOwner) = StateDispatchEvent(
        state = stateFlow.collectAsStateWithLifecycle(lifecycleOwner).value,
        dispatcher = dispatcher,
        effectFlow = effectFlow
    )
}

@Suppress("ComposableNaming")
@Composable
fun <T> SharedFlow<T>.collectInLaunchedEffect(function: suspend (value: T) -> Unit) {
    val sharedFlow = this
    LaunchedEffect(key1 = sharedFlow) {
        sharedFlow.collectLatest( function )
    }
}