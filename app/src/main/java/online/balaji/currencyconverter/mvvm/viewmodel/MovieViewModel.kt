package online.balaji.currencyconverter.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.emperor.kotlinexample.utils.Resource
import kotlinx.coroutines.Dispatchers
import online.interview.flendzz.repository.CurrencyRepo

class MovieViewModel :ViewModel() {

    fun getMoviesList()= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val movieData= CurrencyRepo().getMoviesList()
            emit(Resource.success(data = movieData))
        }catch (e:Exception){

        }
    }
}