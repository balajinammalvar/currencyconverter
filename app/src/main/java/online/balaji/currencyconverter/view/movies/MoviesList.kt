package online.balaji.currencyconverter.view.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import online.balaji.currencyconverter.databinding.ActivityMoviesListBinding
import online.balaji.currencyconverter.model.Movies
import online.balaji.currencyconverter.mvvm.viewmodel.MovieViewModel
import online.balaji.currencyconverter.utilis.TextUtils
import online.balaji.currencyconverter.view.adapter.MoviesAdapter
import online.interview.flendzz.utilis.Status

class MoviesList : AppCompatActivity(),View.OnClickListener {

    private lateinit var activityMoviesListBinding: ActivityMoviesListBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mMoviesList:List<Movies>
    private val TAG="MoviesList"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMoviesListBinding=ActivityMoviesListBinding.inflate(layoutInflater)
        setContentView(activityMoviesListBinding.root)
        movieViewModel=ViewModelProvider(this).get(MovieViewModel::class.java)
        mProgressBar = activityMoviesListBinding.progressBar
//        onClickListener()
        getMovies()

        GlobalScope.launch {
            delay(5000L)
            var i=0
            Log.d(TAG, "onCreate: ${i++}")
        }
    }

        private fun getMovies() {
            movieViewModel.getMoviesList().observe(this,{
                when (it.status){
                    Status.LOADING->{
                        mProgressBar.visibility=View.VISIBLE
                    }
                    Status.ERROR -> {
                        mProgressBar.visibility = View.GONE
                        TextUtils.toast(this, it.message!!)
                    }
                    Status.SUCCESS->{
                        mProgressBar.visibility=View.GONE
                        mMoviesList= it.data!!
                        Log.d(TAG,mMoviesList.size.toString())
                        activityMoviesListBinding.rvMovie.layoutManager=LinearLayoutManager(this)
                        activityMoviesListBinding.rvMovie.adapter=MoviesAdapter(mMoviesList,this)
                    }
                }
            })
        }

    private fun onClickListener() {
        TODO("Not yet implemented")
        activityMoviesListBinding.rvMovie.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}