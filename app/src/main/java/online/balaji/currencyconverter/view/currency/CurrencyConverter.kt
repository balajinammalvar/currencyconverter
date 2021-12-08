package online.balaji.currencyconverter.view.splash.view.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import online.balaji.currencyconverter.R
import online.balaji.currencyconverter.databinding.ActivityCurrencyConverterBinding
import online.balaji.currencyconverter.view.utilis.DialogUtils
import online.balaji.currencyconverter.view.utilis.TextUtils
import online.balaji.currencyconverter.view.viewmodel.CurrencyViewModel
import online.interview.flendzz.utilis.InternetCheck
import online.interview.flendzz.utilis.Status

class CurrencyConverter : AppCompatActivity(), View.OnClickListener {

    var activityCurrencyConverterBinding: ActivityCurrencyConverterBinding? = null
    private lateinit var currencyViewModel: CurrencyViewModel
    private var mCountryCode: List<String>? = null
    var mProgressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCurrencyConverterBinding = ActivityCurrencyConverterBinding.inflate(layoutInflater)
        setContentView(activityCurrencyConverterBinding!!.root)

        currencyViewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)
        mProgressBar = activityCurrencyConverterBinding?.progressBar
        activityCurrencyConverterBinding!!.tvFrom.setOnClickListener(this)
        activityCurrencyConverterBinding!!.tvTo.setOnClickListener(this)
        activityCurrencyConverterBinding!!.btnSubmit.setOnClickListener(this)
    }

    /*API getting currency country code*/
    private fun getCountryCode() {
        currencyViewModel.getCountryCode().observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    mProgressBar?.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    mProgressBar?.visibility = View.GONE
                    TextUtils.toast(this, it.message!!)
                }
                Status.SUCCESS -> {
                    mProgressBar?.visibility = View.GONE
                    mCountryCode = ArrayList<String>(it.data)
                }
            }
        })
    }

    /*API getting converted currency value*/
    private fun getCurrency(from: String, to: String, currency: String) {
        currencyViewModel.getConvertedCurrency(from, to, currency)?.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    mProgressBar?.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    mProgressBar?.visibility = View.GONE
                    TextUtils.toast(this, it.message!!)
                }
                Status.SUCCESS -> {
                    mProgressBar?.visibility = View.GONE
                    /*for user experience concat the strings for display */
                    var convertedCurrency =
                        "${activityCurrencyConverterBinding?.etCurrency?.text}${" "}${it.data?.from}${" "}${"="}${" "}${it.data?.amount?.toString()!!}${" "}${it.data.to}"
                    activityCurrencyConverterBinding?.tvConver?.text = convertedCurrency
                }
            }
        })
    }

    /*on click implementation for this activity*/
    override fun onClick(click: View?) {
        when (click!!.id) {
            R.id.tvFrom -> DialogUtils.selectCountry(
                this,
                mCountryCode,
                activityCurrencyConverterBinding!!.tvFrom
            )
            R.id.tvTo -> DialogUtils.selectCountry(
                this,
                mCountryCode,
                activityCurrencyConverterBinding!!.tvTo
            )
            R.id.btnSubmit -> validateCurrency()
        }
    }

    /*validate input fields*/
    private fun validateCurrency() {
        if (TextUtils.isValidString(activityCurrencyConverterBinding?.tvFrom?.text.toString()) &&
            TextUtils.isValidString(activityCurrencyConverterBinding?.tvTo?.text.toString()) &&
            TextUtils.isValidString(activityCurrencyConverterBinding?.etCurrency?.text.toString())
        ) {
            if (activityCurrencyConverterBinding?.tvFrom?.text.toString() === activityCurrencyConverterBinding?.tvTo?.text.toString()) {
                TextUtils.toast(this, getString(R.string.toast_cannot_same))
            } else
                getCurrency(
                    activityCurrencyConverterBinding?.tvFrom?.text.toString(),
                    activityCurrencyConverterBinding?.tvTo?.text.toString(),
                    activityCurrencyConverterBinding?.etCurrency?.text.toString()
                )
        } else TextUtils.toast(this, getString(R.string.toast_valid))
    }

    /*API call will done for getting country code in activity resume state*/
    override fun onResume() {
        super.onResume()
        if (InternetCheck.isOnline(applicationContext)) {
            getCountryCode()
        } else TextUtils.toast(this, getString(R.string.toast_internet))
    }
}