package net.humans.kmm.mvi.sample

import androidx.annotation.StringRes
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

internal data class CommissionCalculatorViewState(
    val balance: String = "",
    val inputAmount: TextFieldValue = TextFieldValue(text = "", selection = TextRange(0)),
    val commission: String = "",
    val cashback: String = "",
    @StringRes val error: Int? = null,
) {
    fun hasError(): Boolean = error != null
}