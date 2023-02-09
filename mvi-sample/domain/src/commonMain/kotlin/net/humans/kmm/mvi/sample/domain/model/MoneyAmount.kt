package net.humans.kmm.mvi.sample.domain.model

import com.ionspin.kotlin.bignum.decimal.BigDecimal

data class MoneyAmount(
    val amount: BigDecimal,
    val currency: Currency
) {
    operator fun times(value: Float): MoneyAmount =
        this.copy(amount = amount * BigDecimal.fromFloat(value))

    operator fun compareTo(moneyAmount: MoneyAmount): Int {
        check(this.currency == moneyAmount.currency) {
            "Impossible to compare money amount. Non consistent currencies."
        }
        return this.amount.compareTo(moneyAmount.amount)
    }
}

val BigDecimal.usd: MoneyAmount
    get() = MoneyAmount(amount = this, currency = Currency.USD)