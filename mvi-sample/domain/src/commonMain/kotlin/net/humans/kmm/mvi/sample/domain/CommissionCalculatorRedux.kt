package net.humans.kmm.mvi.sample.domain

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import net.humans.kmm.mvi.sample.domain.model.MoneyAmount
import net.humans.kmm.mvi.sample.domain.model.usd

object CommissionCalculatorRedux {
    data class State(
        val balance: MoneyAmount,
        val inputAmount: MoneyAmount,
        val commission: MoneyAmount,
        val cashback: MoneyAmount,
    ) {
        companion object {
            val DEFAULT = State(
                balance = BigDecimal.ZERO.usd,
                inputAmount = BigDecimal.ZERO.usd,
                commission = BigDecimal.ZERO.usd,
                cashback = BigDecimal.ZERO.usd,
            )
        }
    }

    sealed class Message {
        data class UpdateState(
            val balance: MoneyAmount,
            val inputAmount: MoneyAmount,
            val commission: MoneyAmount,
            val cashback: MoneyAmount,
        ) : Message()

        data class UpdateInput(
            val inputAmount: MoneyAmount,
        ) : Message()

        data class UpdateCommissionAndCashback(
            val commission: MoneyAmount,
            val cashback: MoneyAmount,
        ) : Message()
    }

    sealed class Effect {
        object Initialize : Effect()

        data class CalculateCommissionAndCashback(
            val inputAmount: MoneyAmount,
        ) : Effect()
    }
}