package adeel.moviedb.ui.main.viewholders

import adeel.moviedb.R
import adeel.moviedb.data.database.entities.SearchEntry
import adeel.moviedb.data.models.Movie
import adeel.moviedb.ui.base.interfaces.OnMovieClickListener
import adeel.moviedb.utils.Constants
import adeel.moviedb.utils.Helpers
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class SearchViewHolder(itemView: View?,
                       val context: Context,
                       private val listener: OnMovieClickListener
) : RecyclerView.ViewHolder(itemView!!), View.OnClickListener {



    var moreTitle: TextView
    var moreSubtitle: TextView
    var morePoster: ImageView
    private var movie: SearchEntry? = null

    init {
        moreTitle = itemView!!.findViewById(R.id.item_more_name)
        moreSubtitle = itemView.findViewById(R.id.item_more_subtitle)
        morePoster = itemView.findViewById(R.id.item_more_image)

        itemView.setOnClickListener(this)
    }

    fun bindSearchData(movie: SearchEntry?, mSharedPreferences:SharedPreferences) {
        if (movie == null) {
            return
        } else {
            this.movie = movie
            moreTitle.setText(movie.title)
            moreSubtitle.setText(movie.genreString)

            if (mSharedPreferences.getBoolean(context.getString(R.string.pref_cache_data_key),true)){
                Glide.with(context).load(Helpers.buildImageUrl(movie.posterPath!!)).thumbnail(0.05f)
                        .transition(DrawableTransitionOptions.withCrossFade()).into(morePoster)
            } else{
                Glide.with(context).load(Helpers.buildImageUrl(movie.posterPath!!)).thumbnail(0.05f)
                        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true))
                        .transition(DrawableTransitionOptions.withCrossFade()).into(morePoster)
            }
        }
    }

    private fun convertEntryToMovieList(movie: SearchEntry): Movie {
            val passMovie = Movie()
            passMovie.id = movie.movieId
            passMovie.voteCount = movie.voteCount
            passMovie.video = movie.video
            passMovie.voteAverage = movie.voteAverage
            passMovie.title = movie.title
            passMovie.popularity = movie.popularity
            passMovie.posterPath = movie.posterPath!!
            passMovie.originalLanguage = movie.originalLanguage
            passMovie.originalTitle = movie.originalTitle
            passMovie.backdropPath = movie.backdropPath!!
            passMovie.adult = movie.adult
            passMovie.overview = movie.overview
            passMovie.releaseDate = movie.releaseDate
            passMovie.genreString = movie.genreString!!
            passMovie.contentType = Constants.CONTENT_MOVIE
            passMovie.tableName = Constants.SEARCHES
        return passMovie
    }

    override fun onClick(p0: View?) {
        val position: Int = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onMovieClickListener(convertEntryToMovieList(movie!!))
        }
    }
}