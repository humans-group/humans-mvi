package net.humans.kmm.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch

/**
 * MVI suspend Effect Handler
 */
interface CoroutineEffectHandler<in E, out M> {
    suspend fun handle(eff: E): M?
}

/** Coroutine Effect Handlers */
@Suppress("FunctionNaming")
fun <E, M> CoroutineEffectHandler(
    scope: CoroutineScope,
    channel: SendChannel<M>,
    block: CoroutineEffectHandler<E, M>,
) = EffectHandler { eff: E ->
    scope.launch {
        block.handle(eff)?.let { msg ->
            channel.send(msg)
        }
    }
}

@Suppress("FunctionNaming")
fun <E, M> CoroutineEffectHandler(
    scope: CoroutineScope,
    channel: SendChannel<M>,
    block: suspend (E) -> M?
) = EffectHandler { eff: E ->
    scope.launch {
        block.invoke(eff)?.let { msg ->
            channel.send(msg)
        }
    }
}

@Suppress("FunctionNaming")
fun <E, M> CoroutineEffectHandler(
    scope: CoroutineScope,
    channel: SendChannel<M>,
    block: suspend (SendChannel<M>, E) -> Unit,
) = EffectHandler { eff: E ->
    scope.launch {
        block.invoke(channel, eff)
    }
}
