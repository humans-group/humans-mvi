package net.humans.kmm.mvi

import co.touchlab.kermit.Logger

internal fun <E, M, S> Logger.logReduce(msg: M, prevState: S, state: S, effects: List<E>) {
    d { "Begin reduce:" }
    d {
        prevState.logReduxClassName() +
            ", ${msg.logReduxClassName()} -> " +
            state.logReduxClassName() +
            if (effects.isNotEmpty()) ", ${effects.joinToString { it.logReduxClassName() }}" else ""
    }
    d { "End reduce" }
}

internal fun <M, S> Logger.logReduce(prevState: S, state: S, msg: M) {
    d { "Begin reduce:" }
    d { "${prevState.logReduxClassName()}, ${msg.logReduxClassName()} -> ${state.logReduxClassName()}" }
    d { "End reduce" }
}

private fun Any?.logReduxClassName(): String {
    val className = this?.run { this::class.qualifiedName } ?: ""
    return className.substringAfter("Redux.")
}

@Suppress("FunctionName")
fun Any.LoggingErrorHandler(msg: String = "Error occurred"): ErrorHandler {
    return { throwable ->
        Logger.withTag(msg).e(this::class.simpleName.orEmpty(), throwable)
    }
}
