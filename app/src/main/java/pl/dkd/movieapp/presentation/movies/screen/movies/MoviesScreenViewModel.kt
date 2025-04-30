package pl.dkd.movieapp.presentation.movies.screen.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import pl.dkd.movieapp.domain.movies.repository.MoviesRepository
import pl.dkd.movieapp.presentation.movies.screen.movies.model.MovieItemData
import pl.dkd.movieapp.presentation.movies.screen.movies.model.MovieListState

@HiltViewModel
class MoviesScreenViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val _selectedGenre: MutableStateFlow<Int?> = MutableStateFlow(null)
    val selectedGenre: StateFlow<Int?> = _selectedGenre
    private val _movieListState: MutableStateFlow<MovieListState> =
        MutableStateFlow(MovieListState.Loading)
    val movieListState: StateFlow<MovieListState> = _movieListState

    init {
        viewModelScope.launch {
            try {
                _selectedGenre.collectLatest { selectedGenreId ->
                    _movieListState.emit(MovieListState.Loading)
                    val movies = moviesRepository.getMovieList(selectedGenreId)

                    val deferredMovieItemDataList = movies.asFlow().map {
                        async {
                            val details = moviesRepository.getMovieDetails(it.id)
                            MovieItemData(it, details)
                        }
                    }.toList()

                    val movieItemDataList = deferredMovieItemDataList.awaitAll()
                    _movieListState.emit(MovieListState.Loaded(movieItemDataList))
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    fun changeSelectedGenreId(selectedGenreId: Int?) {
        viewModelScope.launch {
            _selectedGenre.emit(selectedGenreId)
        }
    }
}
