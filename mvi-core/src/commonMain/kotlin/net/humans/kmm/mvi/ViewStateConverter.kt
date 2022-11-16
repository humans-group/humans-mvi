package net.humans.kmm.mvi

fun interface ViewStateConverter<S, V> {
    fun convert(state: S): V
}
