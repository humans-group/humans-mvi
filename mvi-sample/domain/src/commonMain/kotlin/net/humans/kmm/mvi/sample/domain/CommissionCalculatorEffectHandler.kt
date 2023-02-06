package net.humans.kmm.mvi.sample.domain

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import net.humans.kmm.mvi.CoroutineEffectHandler
import net.humans.kmm.mvi.sample.domain.CommissionCalculatorRedux.Effect
import net.humans.kmm.mvi.sample.domain.CommissionCalculatorRedux.Message
import net.humans.kmm.mvi.sample.domain.model.usd
import net.humans.kmm.mvi.sample.domain.usecase.CalculateCashbackAndCommissionInput
import net.humans.kmm.mvi.sample.domain.usecase.CalculateCashbackAndCommissionUseCase
import net.humans.kmm.mvi.sample.domain.usecase.GetBalanceUseCase
import net.humans.kmm.mvi.sample.domain.usecase.impl.RandomCalculateCashbackAndCommissionUseCase
import net.humans.kmm.mvi.sample.domain.usecase.impl.RandomGetBalanceUseCase

class CommissionCalculatorEffectHandler(
    private val getBalanceUseCase: GetBalanceUseCase = RandomGetBalanceUseCase(),
    private val calculateCashbackAndCommissionUseCase: CalculateCashbackAndCommissionUseCase =
        RandomCalculateCashbackAndCommissionUseCase()
) : CoroutineEffectHandler<Effect, Message> {
    override suspend fun handle(eff: Effect): Message = when (eff) {
        is Effect.CalculateCommissionAndCashback ->
            calculateCashbackAndCommissionUseCase.execute(
                input = CalculateCashbackAndCommissionInput(inputAmount = eff.inputAmount)
            ).let { result ->
                Message.UpdateCommissionAndCashback(
                    commission = result.commission,
                    cashback = result.cashback,
                )
            }

        Effect.Initialize -> Message.UpdateState(
            balance = getBalanceUseCase.execute().balance,
            inputAmount = BigDecimal.ZERO.usd,
            commission = BigDecimal.ZERO.usd,
            cashback = BigDecimal.ZERO.usd,
        )
    }
}