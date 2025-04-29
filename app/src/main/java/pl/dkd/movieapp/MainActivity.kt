package pl.dkd.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import pl.dkd.movieapp.presentation.movies.screen.moviefilters.MovieFiltersScreen
import pl.dkd.movieapp.presentation.movies.screen.movies.MoviesScreen
import pl.dkd.movieapp.presentation.common.theme.MovieAppTheme

@Serializable
object Movies

@Serializable
object Filters

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MovieAppTheme {
                NavHost(navController, startDestination = Movies) {
                    composable<Movies> { MoviesScreen() }
                    composable<Filters> { MovieFiltersScreen() }
                }
            }
        }
    }
}

