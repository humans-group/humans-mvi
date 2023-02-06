package net.humans.kmm.mvi.sample.domain.usecase.impl

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import net.humans.kmm.mvi.sample.domain.model.Currency
import net.humans.kmm.mvi.sample.domain.model.MoneyAmount
import net.humans.kmm.mvi.sample.domain.usecase.GetBalanceResult
import net.humans.kmm.mvi.sample.domain.usecase.GetBalanceUseCase
import kotlin.random.Random
import kotlin.random.nextUInt

internal class RandomGetBalanceUseCase(
    private val balanceRange: UIntRange = BALANCE_RANGE,
) : GetBalanceUseCase {
    override suspend fun execute(): GetBalanceResult = GetBalanceResult(
        balance = MoneyAmount(
            amount = BigDecimal.fromUInt(Random.nextUInt(balanceRange)),
            currency = Currency.USD
        )
    )

    companion object {
        private val BALANCE_RANGE = 100u..10_000u
    }
}