package pl.dkd.movieapp.presentation.movies.screen.moviefilters.model

import pl.dkd.movieapp.domain.movies.model.MovieGenre

sealed class MovieGenresListState {
    data object Loading : MovieGenresListState()

    data class Loaded(val genreList: List<MovieGenre>) : MovieGenresListState()
}
