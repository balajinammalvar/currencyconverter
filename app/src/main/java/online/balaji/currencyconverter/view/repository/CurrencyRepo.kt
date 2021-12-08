package online.interview.flendzz.repository

import com.emperor.kotlinexample.api.RetroClient
import online.balaji.currencyconverter.view.model.ConverterResponse

class CurrencyRepo {

    companion object {
        suspend fun getCountryCode(): List<String>? {
            val response = RetroClient.apiService.getCountryCode()

            return response
        }

        suspend fun getConvertedCurrency(
            from: String,
            to: String,
            amount: String
        ): ConverterResponse? {
            val response = RetroClient.apiService.getConvertedCurrency(from, to, amount)

            return response
        }
    }
}