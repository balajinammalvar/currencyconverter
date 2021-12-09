package online.balaji.currencyconverter.model

import com.google.gson.annotations.SerializedName

data class ConverterResponse(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String
)
