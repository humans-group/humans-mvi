package net.humans.kmm.mvi.sample.domain.usecase

import net.humans.kmm.mvi.sample.domain.model.MoneyAmount

interface CalculateCashbackAndCommissionUseCase {
    suspend fun execute(
        input: CalculateCashbackAndCommissionInput
    ): CalculateCashbackAndCommissionResult
}

data class CalculateCashbackAndCommissionInput(
    val inputAmount: MoneyAmount,
)

data class CalculateCashbackAndCommissionResult(
    val cashback: MoneyAmount,
    val commission: MoneyAmount,
)