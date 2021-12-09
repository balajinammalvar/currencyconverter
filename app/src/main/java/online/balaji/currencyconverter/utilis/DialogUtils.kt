package online.balaji.currencyconverter.utilis

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import online.balaji.currencyconverter.databinding.DialogSearchableSpinnerBinding

class DialogUtils {

    companion object {

        /*dialog for country code selection with searchable list*/
        fun selectCountry(view: Context, mCountryCode: List<String>?, textView: TextView) {
            val binding = DialogSearchableSpinnerBinding.inflate(LayoutInflater.from(view))
            val searchableSpinner = AlertDialog.Builder(view).setView(binding.root)
            val searchableDialog = searchableSpinner.show()
            searchableDialog.setCancelable(true)

            val arrayAdapter =
                ArrayAdapter<String?>(
                    view, R.layout.simple_list_item_1,
                    mCountryCode!! as List<String?>
                )
            binding.lvCountryCode.adapter = arrayAdapter

            binding.etSearch.addTextChangedListener { charsequence ->
                /*on text change filter will be done*/
                arrayAdapter.filter.filter(charsequence)
            }
            binding.lvCountryCode.setOnItemClickListener { adapterView, view, position, id ->
                textView.text = arrayAdapter.getItem(position)
                searchableDialog.cancel()
            }
            searchableDialog.show()
        }
    }
}