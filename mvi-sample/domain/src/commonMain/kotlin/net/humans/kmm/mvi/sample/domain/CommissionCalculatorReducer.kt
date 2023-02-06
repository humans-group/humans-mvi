package net.humans.kmm.mvi.sample.domain

import net.humans.kmm.mvi.ComplexReducer
import net.humans.kmm.mvi.Return
import net.humans.kmm.mvi.pure
import net.humans.kmm.mvi.sample.domain.CommissionCalculatorRedux.Effect
import net.humans.kmm.mvi.sample.domain.CommissionCalculatorRedux.Message
import net.humans.kmm.mvi.sample.domain.CommissionCalculatorRedux.State
import net.humans.kmm.mvi.withEffect

class CommissionCalculatorReducer : ComplexReducer<State, Message, Effect> {
    override fun invoke(state: State, msg: Message): Return<State, Effect> = when (msg) {
        is Message.UpdateCommissionAndCashback -> state.copy(
            commission = msg.commission,
            cashback = msg.cashback,
        ).pure()

        is Message.UpdateInput -> state.copy(
            inputAmount = msg.inputAmount
        ) withEffect Effect.CalculateCommissionAndCashback(inputAmount = msg.inputAmount)

        is Message.UpdateState -> State(
            balance = msg.balance,
            inputAmount = msg.inputAmount,
            commission = msg.commission,
            cashback = msg.cashback,
        ).pure()
    }
}