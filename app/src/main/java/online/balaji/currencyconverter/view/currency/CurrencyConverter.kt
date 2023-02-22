package online.balaji.currencyconverter.view.splash.view.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import online.balaji.currencyconverter.R
import online.balaji.currencyconverter.databinding.ActivityCurrencyConverterBinding
import online.balaji.currencyconverter.model.Movies
import online.balaji.currencyconverter.mvvm.viewmodel.CurrencyViewModel
import online.balaji.currencyconverter.utilis.DialogUtils
import online.balaji.currencyconverter.utilis.TextUtils
import online.balaji.currencyconverter.view.constraintlayout.ConstraintLayout
import online.balaji.currencyconverter.view.movies.MoviesList
import online.interview.flendzz.utilis.InternetCheck
import online.interview.flendzz.utilis.Status


class CurrencyConverter : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityCurrencyConverterBinding: ActivityCurrencyConverterBinding
    private lateinit var currencyViewModel: CurrencyViewModel
    private lateinit var mCountryCode: List<String>
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mMoviesList: List<Movies>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCurrencyConverterBinding = ActivityCurrencyConverterBinding.inflate(layoutInflater)
        setContentView(activityCurrencyConverterBinding.root)

        currencyViewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)
        mProgressBar = activityCurrencyConverterBinding.progressBar
        onClickListener()
    }



    private fun onClickListener() {
        activityCurrencyConverterBinding.tvFrom.setOnClickListener(this)
        activityCurrencyConverterBinding.tvTo.setOnClickListener(this)
        activityCurrencyConverterBinding.btnSubmit.setOnClickListener(this)
        activityCurrencyConverterBinding.btnNext.setOnClickListener(this)
        activityCurrencyConverterBinding.btnFlow.setOnClickListener(this)
    }

    /*API getting currency country code*/
    private fun getCountryCode() {
        currencyViewModel.getCountryCode().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    mProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    mProgressBar.visibility = View.GONE
                    TextUtils.toast(this, it.message!!)
                }
                Status.SUCCESS -> {
                    mProgressBar.visibility = View.GONE
                    mCountryCode = ArrayList<String>(it.data)
                }
            }
        })
    }

    private fun getMovies() {
        val movieIntent = Intent(applicationContext, MoviesList::class.java)
        startActivity(movieIntent)
    }

    /*API getting converted currency value*/
    private fun getCurrency(from: String, to: String, currency: String) {
        currencyViewModel.getConvertedCurrency(from, to, currency).observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    mProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    mProgressBar.visibility = View.GONE
                    TextUtils.toast(this, it.message!!)
                }
                Status.SUCCESS -> {
                    mProgressBar.visibility = View.GONE
                    /*for user experience concat the strings for display */
                    val convertedCurrency =
                        "${activityCurrencyConverterBinding.etCurrency.text} ${it.data?.from} = ${it.data?.amount.toString()} ${ it.data?.to}"
                    activityCurrencyConverterBinding.tvConver.text = convertedCurrency
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
                activityCurrencyConverterBinding.tvFrom
            )
            R.id.tvTo -> DialogUtils.selectCountry(
                this,
                mCountryCode,
                activityCurrencyConverterBinding.tvTo
            )
            R.id.btnSubmit -> validateCurrency()

            R.id.btnNext -> getMovies()

            R.id.btnFlow->getFlow()
        }
    }

    private fun getFlow() {
        val flowIntent = Intent(applicationContext, ConstraintLayout::class.java)
        startActivity(flowIntent)
    }


    /*validate input fields*/
    private fun validateCurrency() {
        if (TextUtils.isValidString(activityCurrencyConverterBinding.tvFrom.text.toString()) &&
            TextUtils.isValidString(activityCurrencyConverterBinding.tvTo.text.toString()) &&
            TextUtils.isValidString(activityCurrencyConverterBinding.etCurrency.text.toString())
        ) {
            if (activityCurrencyConverterBinding.tvFrom.text.toString() === activityCurrencyConverterBinding.tvTo.text.toString()) {
                TextUtils.toast(this, getString(R.string.toast_cannot_same))
            } else
                getCurrency(
                    activityCurrencyConverterBinding.tvFrom.text.toString(),
                    activityCurrencyConverterBinding.tvTo.text.toString(),
                    activityCurrencyConverterBinding.etCurrency.text.toString()
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