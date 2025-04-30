package pl.dkd.movieapp.presentation.movies.screen.moviefilters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.dkd.movieapp.Filters
import pl.dkd.movieapp.domain.movies.repository.MoviesRepository
import pl.dkd.movieapp.presentation.movies.screen.moviefilters.model.MovieGenresListState

@HiltViewModel
class MovieFiltersScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository
) :
    ViewModel() {
    private val _selectedGenreId: MutableStateFlow<Int?> =
        MutableStateFlow(savedStateHandle.toRoute<Filters>().selectedGenreId)
    val selectedGenreId: StateFlow<Int?> = _selectedGenreId

    private val _genreList: MutableStateFlow<MovieGenresListState> =
        MutableStateFlow(MovieGenresListState.Loading)
    val genreList: StateFlow<MovieGenresListState> = _genreList

    init {
        viewModelScope.launch {
            val genres = moviesRepository.getMovieGenreList()
            val state = MovieGenresListState.Loaded(genres)
            _genreList.emit(state)
        }
    }
}
