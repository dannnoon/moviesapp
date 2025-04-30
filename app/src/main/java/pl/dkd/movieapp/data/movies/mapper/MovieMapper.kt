package pl.dkd.movieapp.data.movies.mapper

import pl.dkd.movieapp.data.movies.model.MovieDTO
import pl.dkd.movieapp.domain.movies.model.Movie

fun MovieDTO.toDomain(): Movie =
    Movie(
        id,
        title,
        "https://image.tmdb.org/t/p/original/$posterUrl",
        rating
    )
