package net.humans.kmm.mvi.sample

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
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
        inputAmount = state.inputAmount.amount.toString().let {
            TextFieldValue(text = it, TextRange(it.length))
        },
        commission = state.commission.toPresentationString(),
        cashback = state.cashback.toPresentationString(),
    )

    private fun MoneyAmount.toPresentationString(): String =
        this.amount.toString() + this.currency.toPresentationString()

    private fun Currency.toPresentationString(): String = when (this) {
        Currency.USD -> "$"
    }
}
