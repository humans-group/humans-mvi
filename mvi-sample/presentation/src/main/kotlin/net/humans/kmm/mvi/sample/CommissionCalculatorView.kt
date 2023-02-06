package net.humans.kmm.mvi.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import net.humans.kmm.mvi.sample.ui.theme.HumansMVITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CommissionCalculatorView(
    viewState: CommissionCalculatorViewState,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Balance:",
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = viewState.balance,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "Commission:",
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = viewState.commission,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "Cashback:",
            style = MaterialTheme.typography.headlineSmall,
        )
        Text(
            text = viewState.cashback,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "Exchange amount:",
            style = MaterialTheme.typography.headlineSmall,
        )
        OutlinedTextField(
            value = viewState.inputAmount,
            onValueChange = {
                onValueChange(it.text)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = MaterialTheme.typography.displaySmall.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Preview
@Composable
private fun PreviewCommissionCalculatorView() {
    HumansMVITheme {
        CommissionCalculatorView(
            viewState = CommissionCalculatorViewState(
                balance = "100",
                inputAmount = TextFieldValue(text="50.00",selection = TextRange(5)),
                commission = "1",
                cashback = "0.5",
            ),
            onValueChange = {}
        )
    }
}