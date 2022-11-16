package net.humans.kmm.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

suspend fun <State> StateFlow<State>.scanCollect(
    onChanged: suspend (prev: State?, curr: State) -> Unit
) {
    var prev: State? = null
    collect { state ->
        onChanged.invoke(prev, state)
        prev = state
    }
}

@Suppress("MagicNumber")
fun <T> Flow<T>.stateInSubscribed(
    scope: CoroutineScope,
    initialValue: T
) = stateIn(
    scope = scope,
    started = WhileSubscribed(5000),
    initialValue = initialValue
)
