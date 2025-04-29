package pl.dkd.movieapp.data.movies.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseDTO(val results: List<MovieDTO>)
