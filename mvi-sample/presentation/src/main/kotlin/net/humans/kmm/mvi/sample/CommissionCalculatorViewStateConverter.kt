package net.humans.kmm.mvi.sample

import androidx.annotation.StringRes
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import net.humans.kmm.mvi.ViewStateConverter
import net.humans.kmm.mvi.sample.domain.CommissionCalculatorRedux
import net.humans.kmm.mvi.sample.domain.model.Currency
import net.humans.kmm.mvi.sample.domain.model.MoneyAmount

internal class CommissionCalculatorViewStateConverter :
    ViewStateConverter<CommissionCalculatorRedux.State, CommissionCalculatorViewState> {
    override fun convert(
        state: CommissionCalculatorRedux.State
    ): CommissionCalculatorViewState = CommissionCalculatorViewState(
        balance = state.balance.toPresentationString(),
        inputAmount = state.inputAmount.amount
            .times(100).intValue(false).toString()
            .let {
                TextFieldValue(text = it, TextRange(it.length))
            },
        commission = state.commission.toPresentationString(),
        cashback = state.cashback.toPresentationString(),
        error = state.error?.toPresentationError(),
    )

    private fun MoneyAmount.toPresentationString(): String =
        this.amount.roundToDigitPosition(3, RoundingMode.FLOOR)
            .toStringExpanded() +
            this.currency.toPresentationString()

    private fun Currency.toPresentationString(): String = when (this) {
        Currency.USD -> "$"
    }

    @StringRes
    private fun CommissionCalculatorRedux.State.Error.toPresentationError(): Int = when(this) {
        CommissionCalculatorRedux.State.Error.InsufficientBalance ->
            R.string.commission_calculator__error_insufficient_balance
    }
}
