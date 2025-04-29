package pl.dkd.movieapp.data.movies.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDTO(
    val budget: Int,
    val revenue: Int
)
