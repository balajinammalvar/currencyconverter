package online.balaji.currencyconverter.view.constraintlayout

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import online.balaji.currencyconverter.R
import online.balaji.currencyconverter.databinding.ActivityConstraintLayoutBinding

class ConstraintLayout : AppCompatActivity() {

    private lateinit var activityConstraintLayoutBinding: ActivityConstraintLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityConstraintLayoutBinding=ActivityConstraintLayoutBinding.inflate(layoutInflater)
        setContentView(activityConstraintLayoutBinding.root)

        getSpinner()
    }

    private fun getSpinner() {
        val model: MutableList<String> = ArrayList()
        model.add("Ford")
        model.add("BMW")
        model.add("Audi")
        model.add("Hyundai")
        model.add("Suzuki")
        model.add("Porsche")

        activityConstraintLayoutBinding.spCarMake.let {
            val arrayAdapter=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,model)
            it.adapter = arrayAdapter
        }
    }
}