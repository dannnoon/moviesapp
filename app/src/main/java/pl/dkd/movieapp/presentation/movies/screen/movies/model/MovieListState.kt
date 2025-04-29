package pl.dkd.movieapp.presentation.movies.screen.movies.model

sealed class MovieListState {
    data object Loading : MovieListState()

    data class Loaded(
        val movieItemDataList: List<MovieItemData>
    ) : MovieListState()
}
