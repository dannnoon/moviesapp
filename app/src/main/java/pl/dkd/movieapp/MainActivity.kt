package pl.dkd.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import pl.dkd.movieapp.presentation.common.theme.MovieAppTheme
import pl.dkd.movieapp.presentation.movies.screen.moviefilters.MovieFiltersScreen
import pl.dkd.movieapp.presentation.movies.screen.movies.MoviesScreen

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
                        MoviesScreen(hiltViewModel()) {
                            navController.navigate(Filters(it))
                        }
                    }
                    composable<Filters> {
                        MovieFiltersScreen(hiltViewModel(), onNavigateBack = {
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "genreId",
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
