package online.balaji.currencyconverter.view.model

data class ConverterResponse(
    val amount: Double,
    val from: String,
    val to: String
)