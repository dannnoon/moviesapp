package pl.dkd.movieapp.data.movies.mapper

import pl.dkd.movieapp.data.movies.model.MovieGenreDTO
import pl.dkd.movieapp.domain.movies.model.MovieGenre

fun MovieGenreDTO.toDomain() = MovieGenre(id, name)
