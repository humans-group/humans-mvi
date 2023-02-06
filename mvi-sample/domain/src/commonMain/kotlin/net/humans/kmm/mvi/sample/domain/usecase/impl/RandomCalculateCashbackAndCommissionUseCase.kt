package net.humans.kmm.mvi.sample.domain.usecase.impl

import net.humans.kmm.mvi.sample.domain.usecase.CalculateCashbackAndCommissionInput
import net.humans.kmm.mvi.sample.domain.usecase.CalculateCashbackAndCommissionResult
import net.humans.kmm.mvi.sample.domain.usecase.CalculateCashbackAndCommissionUseCase

internal class RandomCalculateCashbackAndCommissionUseCase(
    private val commissionPercentage: Float = DEFAULT_COMMISSION_PERCENTAGE,
    private val cashbackPercentage: Float = DEFAULT_CASHBACK_PERCENTAGE,
) : CalculateCashbackAndCommissionUseCase {
    override suspend fun execute(
        input: CalculateCashbackAndCommissionInput
    ): CalculateCashbackAndCommissionResult = if (input.inputAmount > input.balance) {
        CalculateCashbackAndCommissionResult.Failed.InsufficientBalance
    } else {
        CalculateCashbackAndCommissionResult.Success(
            inputAmount = input.inputAmount,
            cashback = input.inputAmount * cashbackPercentage,
            commission = input.inputAmount * commissionPercentage,
        )
    }

    companion object {
        private const val DEFAULT_COMMISSION_PERCENTAGE = 0.02f
        private const val DEFAULT_CASHBACK_PERCENTAGE = 0.01f
    }
}