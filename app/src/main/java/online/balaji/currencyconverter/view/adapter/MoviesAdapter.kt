package online.balaji.currencyconverter.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import online.balaji.currencyconverter.R
import online.balaji.currencyconverter.databinding.ItemMovieListBinding
import online.balaji.currencyconverter.model.Movies

class MoviesAdapter(var mMoviesList:List<Movies>,val context:Context): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private lateinit var movieListBinding: ItemMovieListBinding

    class ViewHolder(ItemMovieListBinding: ItemMovieListBinding):RecyclerView.ViewHolder(ItemMovieListBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val movieLayoutInflater = LayoutInflater.from(parent.context)
        movieListBinding = ItemMovieListBinding.inflate(movieLayoutInflater)
        return ViewHolder(movieListBinding)
    }

    override fun getItemCount(): Int {
       return mMoviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var movie=mMoviesList[position]
        movieListBinding.tvTitle.text=movie.name
        movieListBinding.tvDesc.text=movie.desc
        Picasso.get()
            .load(movie.imageUrl)
            .placeholder(R.drawable.iv_progress)
            .into(movieListBinding.ivMovie)

    }
}