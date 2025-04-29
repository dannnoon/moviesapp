package pl.dkd.movieapp.presentation.movies.screen.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.dkd.movieapp.presentation.movies.screen.movies.model.MovieListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(viewModel: MoviesScreenViewModel) {
    val movieItemList by viewModel.movieListState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Movies",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) {
        when (movieItemList) {
            MovieListState.Loading -> Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

            is MovieListState.Loaded -> MoviesGridView(movieItemList as MovieListState.Loaded, it)
        }
    }
}

@Composable
private fun MoviesGridView(loadedState: MovieListState.Loaded, innerPadding: PaddingValues) {
    LazyVerticalGrid(
        modifier = Modifier.padding(innerPadding),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
    ) {
        items(
            loadedState.movieItemDataList.size,
            itemContent = {
                val movie = loadedState.movieItemDataList[it]
                Column {
                    Text(movie.movie.title, style = MaterialTheme.typography.titleMedium)
                }
            }
        )
    }
}
