package net.humans.kmm.mvi

typealias ComplexReducer<State, Msg, Eff> = (state: State, msg: Msg) -> Return<State, Eff>
typealias SimpleReducer<State, Msg> = (state: State, msg: Msg) -> State
typealias StateConsumer<State> = (state: State) -> Unit
typealias ErrorHandler = (Throwable) -> Unit
typealias Mutator<T> = (old: T) -> T
