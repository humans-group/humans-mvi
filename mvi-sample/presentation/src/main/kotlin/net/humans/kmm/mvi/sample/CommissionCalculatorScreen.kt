package net.humans.kmm.mvi.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
internal fun CommissionCalculatorScreen() {
    val viewModel: CommissionCalculatorViewModel = viewModel()
    val viewState = viewModel.viewState.collectAsState()

    CommissionCalculatorView(
        viewState = viewState.value,
        onValueChange = viewModel::inputAmountChange,
    )
}