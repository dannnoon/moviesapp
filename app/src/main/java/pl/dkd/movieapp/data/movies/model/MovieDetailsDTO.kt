package pl.dkd.movieapp.data.movies.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDTO(
    val budget: Long,
    val revenue: Long
)
