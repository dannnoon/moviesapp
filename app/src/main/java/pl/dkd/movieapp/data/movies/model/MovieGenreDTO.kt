package pl.dkd.movieapp.data.movies.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieGenreDTO(
    val id: Int,
    val name: String
)
