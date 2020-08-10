package adeel.moviedb.data.database.entities

import adeel.moviedb.utils.Constants.Companion.SEARCHES
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "search")
class SearchEntry {

    //Creating all fields to assist in the future development of this app
    @PrimaryKey
    var movieId: Int? = null
    var voteCount: Int? = null
    var video: Boolean? = null
    var voteAverage: Float? = null
    var title: String? = null
    var popularity: Float? = null
    var posterPath: String? = null
    var originalLanguage: String? = null
    var originalTitle: String? = null
    var genreIds: String? = null
    var backdropPath: String? = null
    var adult: Boolean? = null
    var overview: String? = null
    var releaseDate: String? = null
    var contentType: Int? = null
    var totalPages: Int? = null
    var genreString: String? = ""
    var timeAdded: Long? = null
    var tableName: Int = SEARCHES

    override fun equals(other: Any?): Boolean {
        return movieId == other
    }

    override fun hashCode(): Int {
        return movieId!!
    }

    override fun toString(): String {
        return "Movie(id=$movieId, timeAdded=$timeAdded)"
    }

}