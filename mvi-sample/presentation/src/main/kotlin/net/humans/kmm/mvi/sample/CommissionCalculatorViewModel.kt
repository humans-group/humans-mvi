package net.humans.kmm.mvi.sample

import androidx.lifecycle.ViewModel
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import net.humans.kmm.mvi.ComplexReducer
import net.humans.kmm.mvi.CoroutineEffectHandler
import net.humans.kmm.mvi.LaunchReduxEngine
import net.humans.kmm.mvi.ViewStateConverter
import net.humans.kmm.mvi.consume
import net.humans.kmm.mvi.sample.domain.CommissionCalculatorEffectHandler
import net.humans.kmm.mvi.sample.domain.CommissionCalculatorReducer
import net.humans.kmm.mvi.sample.domain.CommissionCalculatorRedux.Effect
import net.humans.kmm.mvi.sample.domain.CommissionCalculatorRedux.Message
import net.humans.kmm.mvi.sample.domain.CommissionCalculatorRedux.State
import net.humans.kmm.mvi.sample.domain.model.usd
import net.humans.kmm.mvi.withEffect

internal class CommissionCalculatorViewModel(
    reducer: ComplexReducer<State, Message, Effect> = CommissionCalculatorReducer(),
    effectHandler: CoroutineEffectHandler<Effect, Message> = CommissionCalculatorEffectHandler(),
    viewStateConverter: ViewStateConverter<State, CommissionCalculatorViewState> =
        CommissionCalculatorViewStateConverter(),
) : ViewModel() {

    private val _viewState = MutableStateFlow(CommissionCalculatorViewState())
    val viewState: StateFlow<CommissionCalculatorViewState> = _viewState

    private val engine = LaunchReduxEngine(
        tag = TAG,
        initial = State.DEFAULT withEffect Effect.Initialize,
        reducer = reducer,
        effectHandler = effectHandler,
    ).also { consume(it, _viewState, viewStateConverter) }

    fun inputAmountChange(input: String) {
        val amount = input.toFloatOrNull()?.let { it / INPUT_AMOUNT_DIVIDER } ?: DEFAULT_AMOUNT
        val moneyAmount = BigDecimal.fromFloat(amount).usd
        engine send Message.UpdateInput(moneyAmount)
    }

    companion object {
        private const val TAG = "CommissionCalculatorViewModel"
        private const val DEFAULT_AMOUNT = 0f
        private const val INPUT_AMOUNT_DIVIDER = 100
    }
}