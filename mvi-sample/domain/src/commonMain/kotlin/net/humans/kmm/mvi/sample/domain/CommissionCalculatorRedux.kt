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
        val error: Error? = null,
    ) {

        sealed class Error {
            object InsufficientBalance : Error()
        }

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
            val inputAmount: MoneyAmount,
            val commission: MoneyAmount,
            val cashback: MoneyAmount,
        ) : Message()

        data class SetError(
            val error: State.Error,
        ) : Message()

        object ErrorHandled : Message()
    }

    sealed class Effect {
        object Initialize : Effect()

        data class CalculateCommissionAndCashback(
            val balance: MoneyAmount,
            val inputAmount: MoneyAmount,
        ) : Effect()
    }
}