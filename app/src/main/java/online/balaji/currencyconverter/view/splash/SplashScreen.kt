package online.balaji.currencyconverter.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import online.balaji.currencyconverter.databinding.ActivitySplashBinding
import online.balaji.currencyconverter.view.splash.view.home.CurrencyConverter

class SplashScreen : AppCompatActivity() {

    private lateinit var activitySplashBinding: ActivitySplashBinding
    private val activityScope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding.root)

        activityScope.launch {
            delay(3000)
            val intent = Intent(applicationContext, CurrencyConverter::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }
}