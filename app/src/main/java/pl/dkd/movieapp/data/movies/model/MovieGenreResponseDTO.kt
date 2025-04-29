package pl.dkd.movieapp.data.movies.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieGenreResponseDTO(val genres: List<MovieGenreDTO>)
