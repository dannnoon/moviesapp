package pl.dkd.movieapp.data.movies.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDTO(
    val title: String,
    @SerialName("poster_path") val posterUrl: String,
    @SerialName("vote_average") val rating: Double
)
