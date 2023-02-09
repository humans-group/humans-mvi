package net.humans.kmm.mvi.sample.domain.usecase

import net.humans.kmm.mvi.sample.domain.model.MoneyAmount

interface CalculateCashbackAndCommissionUseCase {
    suspend fun execute(
        input: CalculateCashbackAndCommissionInput
    ): CalculateCashbackAndCommissionResult
}

data class CalculateCashbackAndCommissionInput(
    val balance: MoneyAmount,
    val inputAmount: MoneyAmount,
)

sealed class CalculateCashbackAndCommissionResult {
    data class Success(
        val inputAmount: MoneyAmount,
        val cashback: MoneyAmount,
        val commission: MoneyAmount,
    ) : CalculateCashbackAndCommissionResult()

    sealed class Failed : CalculateCashbackAndCommissionResult() {
        object InsufficientBalance : Failed()
    }
}