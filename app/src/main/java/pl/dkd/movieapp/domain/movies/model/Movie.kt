package pl.dkd.movieapp.domain.movies.model

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val rating: Double
)
