package net.humans.kmm.mvi.sample.domain.usecase

import net.humans.kmm.mvi.sample.domain.model.MoneyAmount

interface GetBalanceUseCase {
    suspend fun execute(): GetBalanceResult
}

data class GetBalanceResult(
    val balance: MoneyAmount,
)