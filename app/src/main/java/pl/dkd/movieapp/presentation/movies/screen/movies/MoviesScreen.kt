package pl.dkd.movieapp.presentation.movies.screen.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.dkd.movieapp.R
import pl.dkd.movieapp.presentation.movies.screen.movies.model.MovieListState
import pl.dkd.movieapp.presentation.movies.screen.movies.view.MovieGridItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    viewModel: MoviesScreenViewModel,
    onNavigateToFilters: (selectedGenreId: Int?) -> Unit
) {
    val movieItemList by viewModel.movieListState.collectAsStateWithLifecycle()
    val selectedGenre by viewModel.selectedGenre.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.movies_title),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToFilters(selectedGenre) }
            ) {
                Icon(Icons.Default.Search, null)
            }
        }
    ) {
        when (movieItemList) {
            is MovieListState.Loading -> LoadingView()

            is MovieListState.Loaded -> MoviesGridView(
                movieItemList as MovieListState.Loaded,
                it
            )

            is MovieListState.ConnectionError -> ConnectionErrorView {
                viewModel.reloadData()
            }
        }
    }
}

@Composable
private fun LoadingView() {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun MoviesGridView(loadedState: MovieListState.Loaded, innerPadding: PaddingValues) {
    LazyVerticalGrid(
        modifier = Modifier.padding(innerPadding),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            loadedState.movieItemDataList.size,
            itemContent = {
                val movie = loadedState.movieItemDataList[it]
                MovieGridItem(movie)
            }
        )
    }
}

@Composable
private fun ConnectionErrorView(onTryAgain: () -> Unit) {
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.movies_connection_error),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onTryAgain() }) {
            Text(stringResource(R.string.movies_connection_error_action))
        }
    }
}
