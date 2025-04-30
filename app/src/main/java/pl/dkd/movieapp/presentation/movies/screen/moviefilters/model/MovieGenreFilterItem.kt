package pl.dkd.movieapp.presentation.movies.screen.moviefilters.model

import pl.dkd.movieapp.domain.movies.model.MovieGenre

sealed class MovieGenreFilterItem(open val id: Int?) {
    data object AllFilters : MovieGenreFilterItem(null)

    data class SingleFilter(override val id: Int, val name: String) : MovieGenreFilterItem(id) {
        companion object {
            fun fromMovieGenre(genre: MovieGenre): SingleFilter {
                return SingleFilter(genre.id, genre.name)
            }
        }
    }
}
