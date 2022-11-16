package net.humans.kmm.mvi

import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.StateFlow

/**
 * MVI Engine
 */
class ReduxEngine<S, M>(
    val input: SendChannel<M>,
    val output: StateFlow<S>,
) {
    fun send(msg: M) {
        input.trySend(msg).isSuccess
    }
}
