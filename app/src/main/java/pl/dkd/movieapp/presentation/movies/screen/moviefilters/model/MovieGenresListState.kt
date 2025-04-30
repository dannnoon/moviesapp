package pl.dkd.movieapp.presentation.movies.screen.moviefilters.model

sealed class MovieGenresListState {
    data object Loading : MovieGenresListState()

    data class Loaded(val genreList: List<MovieGenreFilterItem>) : MovieGenresListState()
}
