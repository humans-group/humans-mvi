package net.humans.kmm.mvi

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("TooGenericExceptionCaught", "LongParameterList", "FunctionNaming")
fun <S, M, E> LaunchReduxEngine(
    scope: CoroutineScope,
    tag: String,
    initial: Return<S, E>,
    reducer: ComplexReducer<S, in M, E>,
    effectHandlerFactory: (SendChannel<M>) -> EffectHandler<E>,
    errorHandler: ErrorHandler,
): ReduxEngine<S, M> {
    val logger = Logger.withTag(tag)
    val messages = Channel<M>(Channel.UNLIMITED)
    val states = MutableStateFlow(initial.state)
    val effectHandler = effectHandlerFactory.invoke(messages)
    effectHandler.handle(initial.effects)
    scope.launch {
        for (msg in messages) {
            try {
                val prevState = states.value
                val (state, effects) = reducer.invoke(prevState, msg)
                logger.logReduce(msg, prevState, state, effects)
                states.value = state
                effectHandler.handle(effects)
            } catch (e: Throwable) {
                errorHandler.invoke(e)
            }
        }
    }
    return ReduxEngine(messages, states)
}

@Suppress("TooGenericExceptionCaught", "FunctionNaming")
fun <S, M> LaunchReduxEngine(
    scope: CoroutineScope,
    tag: String,
    initial: S,
    reducer: SimpleReducer<S, in M>,
    errorHandler: ErrorHandler,
): ReduxEngine<S, M> {
    val logger = Logger.withTag(tag)
    val messages = Channel<M>(Channel.UNLIMITED)
    val states = MutableStateFlow(initial)
    scope.launch {
        for (msg in messages) {
            try {
                val prevState = states.value
                val state = reducer.invoke(prevState, msg)
                logger.logReduce(prevState, state, msg)
                states.value = state
            } catch (e: Throwable) {
                errorHandler.invoke(e)
            }
        }
    }
    return ReduxEngine(messages, states)
}
