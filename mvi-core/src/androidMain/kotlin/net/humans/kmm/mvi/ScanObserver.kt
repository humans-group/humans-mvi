@file:Suppress("DeprecatedCallableAddReplaceWith")

package net.humans.kmm.mvi

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch

/**
 * Observer that provides also a previous value
 */
@Suppress("FunctionName")
@Deprecated("Use StateFlow instead of LiveData")
inline fun <State> ScanObserver(
    crossinline onChanged: (prev: State?, curr: State) -> Unit
): Observer<State> {
    return object : Observer<State> {
        var prev: State? = null
        override fun onChanged(state: State) {
            onChanged.invoke(prev, state)
            prev = state
        }
    }
}

@Deprecated("Use StateFlow instead of LiveData")
inline fun <State> LiveData<State>.scanObserve(
    lifecycleOwner: LifecycleOwner,
    crossinline onChanged: (prev: State?, curr: State) -> Unit
): Observer<State> {
    return ScanObserver(onChanged).apply {
        observe(lifecycleOwner, this)
    }
}

@Suppress("FunctionName")
@Deprecated("Use StateFlow instead of LiveData")
@OptIn(ObsoleteCoroutinesApi::class)
fun <State> SuspendScanObserver(
    scope: CoroutineScope,
    onChanged: suspend (prev: State?, curr: State) -> Unit
): Observer<State> {
    val rendererChannel = scope.actor<State> {
        var prev: State? = null
        for (state in channel) {
            onChanged.invoke(prev, state)
            prev = state
        }
    }
    return Observer { state ->
        if (!rendererChannel.trySend(state).isSuccess) {
            scope.launch { rendererChannel.send(state) }
        }
    }
}

@Deprecated("Use StateFlow instead of LiveData")
fun <State> LiveData<State>.suspendScanObserve(
    lifecycleOwner: LifecycleOwner,
    onChanged: suspend (prev: State?, curr: State) -> Unit
): Observer<State> {
    return SuspendScanObserver(lifecycleOwner.lifecycle.coroutineScope, onChanged).apply {
        observe(lifecycleOwner, this)
    }
}
