package pl.dkd.movieapp.presentation.movies.screen.movies.model

import pl.dkd.movieapp.domain.movies.model.Movie
import pl.dkd.movieapp.domain.movies.model.MovieDetails

data class MovieItemData(val movie: Movie, val details: MovieDetails)
