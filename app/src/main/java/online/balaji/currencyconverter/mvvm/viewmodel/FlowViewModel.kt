package online.balaji.currencyconverter.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class FlowViewModel :ViewModel() {

    private val _liveData=MutableLiveData("Live Data")
    val liveData:LiveData<String> =_liveData

    private val _stateFlow= MutableStateFlow("State Flow")
    val stateFlow=_stateFlow.asStateFlow()

    private val _sharedStateFlow= MutableSharedFlow<String>()
    val sharedStateFlow=_sharedStateFlow.asSharedFlow()


    fun triggerLiveData(){
        _liveData.value="Trigger Live Data"
    }

    fun triggerStateFlow(){
        _stateFlow.value="Trigger State Data"
    }

    fun triggerFlow():Flow<String>{
        return flow {
            repeat(5) {
                emit("Balaji $it")
                delay(1000L)
            }
        }
    }
}