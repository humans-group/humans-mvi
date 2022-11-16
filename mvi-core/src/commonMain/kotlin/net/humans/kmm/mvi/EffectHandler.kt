package net.humans.kmm.mvi

/**
 * MVI Effect Handler
 */
@Suppress("MatchingDeclarationName")
fun interface EffectHandler<in Eff> {
    fun handle(eff: Eff)
}

fun <Eff> EffectHandler<Eff>.handle(effects: List<Eff>) {
    effects.forEach { effect ->
        handle(effect)
    }
}
