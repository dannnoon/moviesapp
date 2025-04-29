package pl.dkd.movieapp.data.movies.mapper

import pl.dkd.movieapp.data.movies.model.MovieDetailsDTO
import pl.dkd.movieapp.domain.movies.model.MovieDetails

fun MovieDetailsDTO.toDomain() = MovieDetails(budget, revenue)
