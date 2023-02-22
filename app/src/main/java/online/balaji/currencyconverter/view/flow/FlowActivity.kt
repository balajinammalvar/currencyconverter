package online.balaji.currencyconverter.view.flow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import online.balaji.currencyconverter.databinding.ActivityFlowBinding
import online.balaji.currencyconverter.mvvm.viewmodel.FlowViewModel

class FlowActivity : AppCompatActivity() {

    private lateinit var activityFlowBinding: ActivityFlowBinding
    private lateinit var flowViewModel:FlowViewModel

    private var number:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFlowBinding=ActivityFlowBinding.inflate(layoutInflater)
        setContentView(activityFlowBinding.root)
        flowViewModel=ViewModelProvider(this).get(FlowViewModel::class.java)

        activityFlowBinding.btnLiveData.setOnClickListener {
            flowViewModel.triggerLiveData()
        }
        activityFlowBinding.btnStateFlow.setOnClickListener {
            flowViewModel.triggerStateFlow()
        }
        activityFlowBinding.btnFlow.setOnClickListener {
            lifecycleScope.launch {
                flowViewModel.triggerFlow().collectLatest {
                    activityFlowBinding.tvFlow.text=it
                }
            }
        }

        activityFlowBinding.btnSharedFlow.setOnClickListener {

        }

        subscribeToObserver()


    }

    private fun subscribeToObserver() {
        flowViewModel.liveData.observe(this){
            activityFlowBinding.tvLiveData.text=it
        }

        lifecycleScope.launchWhenStarted {
            flowViewModel.stateFlow.collectLatest {
                activityFlowBinding.tvStateFlow.text=it
            }
        }

    }

}