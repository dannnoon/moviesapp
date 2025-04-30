package pl.dkd.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import pl.dkd.movieapp.presentation.common.theme.MovieAppTheme
import pl.dkd.movieapp.presentation.movies.screen.moviefilters.MovieFiltersScreen
import pl.dkd.movieapp.presentation.movies.screen.movies.MoviesScreen
import pl.dkd.movieapp.presentation.movies.screen.movies.MoviesScreenViewModel

private const val SELECTED_GENRE_ID_KEY = "selectedGenreId"

@Serializable
object Movies

@Serializable
data class Filters(val selectedGenreId: Int?)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MovieAppTheme {
                NavHost(navController, startDestination = Movies) {
                    composable<Movies> {
                        val viewModel = hiltViewModel<MoviesScreenViewModel>()
                        viewModel.viewModelScope.launch {
                            it.savedStateHandle.getStateFlow<Int?>(SELECTED_GENRE_ID_KEY, null)
                                .collectLatest {
                                    viewModel.changeSelectedGenreId(it)
                                }
                        }
                        MoviesScreen(viewModel) {
                            navController.navigate(Filters(it))
                        }
                    }
                    composable<Filters> {
                        MovieFiltersScreen(hiltViewModel(), onNavigateBack = {
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                SELECTED_GENRE_ID_KEY,
                                it
                            )
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }
}
