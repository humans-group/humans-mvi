@file:Suppress("TooManyFunctions")

package net.humans.kmm.mvi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/** Complex Engines */
@Suppress("FunctionNaming")
fun <S, M, E> ViewModel.LaunchReduxEngine(
    tag: String,
    initial: Return<S, E>,
    reducer: ComplexReducer<S, in M, E>,
    effectHandler: CoroutineEffectHandler<E, M>,
    errorHandler: ErrorHandler = LoggingErrorHandler(tag),
) = LaunchReduxEngine(
    scope = viewModelScope,
    tag = tag,
    initial = initial,
    reducer = reducer,
    effectHandlerFactory = { channel ->
        CoroutineEffectHandler(viewModelScope, channel, effectHandler)
    },
    errorHandler = errorHandler
)

@Suppress("FunctionNaming")
fun <S, M, E> ViewModel.LaunchReduxEngine(
    tag: String,
    initial: Return<S, E>,
    reducer: ComplexReducer<S, in M, E>,
    effectHandler: suspend (E) -> M?,
    errorHandler: ErrorHandler = LoggingErrorHandler(tag),
) = LaunchReduxEngine(
    scope = viewModelScope,
    tag = tag,
    initial = initial,
    reducer = reducer,
    effectHandlerFactory = { channel ->
        CoroutineEffectHandler(viewModelScope, channel, effectHandler)
    },
    errorHandler = errorHandler
)

@Suppress("FunctionNaming")
fun <S, M, E> ViewModel.LaunchReduxEngine(
    tag: String,
    initial: Return<S, E>,
    reducer: ComplexReducer<S, in M, E>,
    effectHandler: suspend (SendChannel<M>, E) -> Unit,
    errorHandler: ErrorHandler = LoggingErrorHandler(tag),
) = LaunchReduxEngine(
    scope = viewModelScope,
    tag = tag,
    initial = initial,
    reducer = reducer,
    effectHandlerFactory = { channel ->
        CoroutineEffectHandler(viewModelScope, channel, effectHandler)
    },
    errorHandler = errorHandler
)

/** Simple Engines */
@Suppress("FunctionNaming")
fun <S, M> ViewModel.LaunchReduxEngine(
    tag: String,
    initial: S,
    reducer: SimpleReducer<S, in M>,
    errorHandler: ErrorHandler = LoggingErrorHandler(tag),
) = LaunchReduxEngine(
    scope = viewModelScope,
    tag = tag,
    initial = initial,
    reducer = reducer,
    errorHandler = errorHandler
)

/** Consumption */
@Deprecated("Use StateFlow instead of LiveData")
fun <S, V> ViewModel.consumeNow(
    engine: ReduxEngine<S, *>,
    liveData: MutableLiveData<V>,
    transform: (S) -> V
) {
    val consumed = engine.output.value
    liveData.value = transform.invoke(consumed)
    consume(engine, liveData, transform)
}

@Deprecated("Use StateFlow instead of LiveData")
fun <S, V> ViewModel.consume(
    engine: ReduxEngine<S, *>,
    liveData: MutableLiveData<V>,
    transform: (S) -> V
) {
    consume(engine.output, liveData) { _, state -> transform.invoke(state) }
}

@Deprecated("Use StateFlow instead of LiveData")
fun <S, V> ViewModel.consume(
    engine: ReduxEngine<S, *>,
    liveData: MutableLiveData<V>,
    converter: ViewStateConverter<S, V>
) {
    consume(engine.output, liveData) { _, state -> converter.convert(state) }
}

@Deprecated("Use StateFlow instead of LiveData")
fun <S, V> ViewModel.consume(
    output: Flow<S>,
    liveData: MutableLiveData<V>,
    transform: (V?, S) -> V
) {
    viewModelScope.launch {
        output.collect { state ->
            liveData.mutate(state, transform)
        }
    }
}

fun <S> ViewModel.consume(
    engine: ReduxEngine<S, *>,
    consumer: suspend (S) -> Unit,
) {
    viewModelScope.launch {
        engine.output.collect { state ->
            consumer.invoke(state)
        }
    }
}

fun <S, V> ViewModel.consume(
    engine: ReduxEngine<S, *>,
    stateFlow: MutableStateFlow<V>,
    transform: (S) -> V
) {
    consume(engine.output, stateFlow) { _, state: S -> transform.invoke(state) }
}

fun <S, V> ViewModel.consume(
    engine: ReduxEngine<S, *>,
    stateFlow: MutableStateFlow<V>,
    converter: ViewStateConverter<S, V>
) {
    consume(engine.output, stateFlow) { _, state: S -> converter.convert(state) }
}

fun <S, V> ViewModel.consume(
    output: Flow<S>,
    stateFlow: MutableStateFlow<V>,
    transform: (V?, S) -> V
) {
    viewModelScope.launch {
        output.collect { state ->
            stateFlow.value = transform.invoke(stateFlow.value, state)
        }
    }
}

fun <S, V> ViewModel.consume(
    output: Flow<S>,
    stateFlow: MutableStateFlow<V>,
    converter: ViewStateConverter<S, V>
) {
    viewModelScope.launch {
        output.collect { state ->
            stateFlow.value = converter.convert(state)
        }
    }
}
