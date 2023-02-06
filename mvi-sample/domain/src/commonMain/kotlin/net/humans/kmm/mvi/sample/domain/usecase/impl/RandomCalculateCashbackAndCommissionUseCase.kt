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
    ): CalculateCashbackAndCommissionResult {
        return CalculateCashbackAndCommissionResult(
            cashback = input.inputAmount * commissionPercentage,
            commission = input.inputAmount * cashbackPercentage,
        )
    }

    companion object {
        private const val DEFAULT_COMMISSION_PERCENTAGE = 0.02f
        private const val DEFAULT_CASHBACK_PERCENTAGE = 0.01f
    }
}