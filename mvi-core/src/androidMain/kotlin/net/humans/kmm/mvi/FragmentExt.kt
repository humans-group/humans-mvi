package net.humans.kmm.mvi

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T> Fragment.observeOnLifecycle(
    flow: StateFlow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    onChanged: suspend (prev: T?, curr: T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            flow.scanCollect(onChanged)
        }
    }
}
