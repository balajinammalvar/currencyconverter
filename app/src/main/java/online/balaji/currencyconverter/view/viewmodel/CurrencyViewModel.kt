package online.balaji.currencyconverter.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.emperor.kotlinexample.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import online.interview.flendzz.repository.CurrencyRepo
import kotlin.coroutines.CoroutineContext

class CurrencyViewModel : ViewModel(), CoroutineScope {

    private val job = Job()// Should cancel this on Destroy


    fun getCountryCode() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = CurrencyRepo.getCountryCode()
            emit(Resource.success(data = data))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getConvertedCurrency(from: String, to: String, amount: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = CurrencyRepo.getConvertedCurrency(from, to, amount)
            emit(Resource.success(data = data))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


}