package net.humans.kmm.mvi

/**
 * State and List of possible side effects
 */
data class Return<out State, out Eff>(val state: State, val effects: List<Eff> = listOf())

fun <State, Eff> State.pure(): Return<State, Eff> {
    return Return(this)
}

fun <State, Eff> State.withEffects(vararg effects: Eff): Return<State, Eff> {
    return Return(this, listOf(*effects))
}

infix fun <State, Eff> State.withEffects(effects: List<Eff>): Return<State, Eff> {
    return Return(this, effects)
}

infix fun <State, Eff> State.withEffect(effect: Eff): Return<State, Eff> {
    return Return(this, listOf(effect))
}
