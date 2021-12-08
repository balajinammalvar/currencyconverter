package online.balaji.currencyconverter.view.model

data class CurrencyResponse(
    val amount: String,
    val base_currency_code: String,
    val base_currency_name: String,
    val rates: Rates,
    val status: String,
    val updated_date: String
)

data class Rates(
    val USD: USD
)

data class USD(
    val currency_name: String,
    val rate: String,
    val rate_for_amount: String
)