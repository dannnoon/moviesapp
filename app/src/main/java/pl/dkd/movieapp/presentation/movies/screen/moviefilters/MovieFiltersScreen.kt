package pl.dkd.movieapp.presentation.movies.screen.moviefilters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.dkd.movieapp.R
import pl.dkd.movieapp.domain.movies.model.MovieGenre
import pl.dkd.movieapp.presentation.movies.screen.moviefilters.model.MovieGenresListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFiltersScreen(
    viewModel: MovieFiltersScreenViewModel,
    onNavigateBack: (selectedGenreId: Int?) -> Unit
) {
    val movieGenresListState by viewModel.genreList.collectAsStateWithLifecycle()
    val selectedGenreId by viewModel.selectedGenreId.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(

            title = {
                Text(stringResource(R.string.movie_filters_title))
            },
            navigationIcon = {
                IconButton(onClick = { onNavigateBack(selectedGenreId) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            }
        )
    }) {
        when (movieGenresListState) {
            MovieGenresListState.Loading -> LoadingView()
            is MovieGenresListState.Loaded -> MovieGenresList(
                movieGenresListState as MovieGenresListState.Loaded,
                selectedGenreId,
                it,
                onNavigateBack
            )
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
fun MovieGenresList(
    state: MovieGenresListState.Loaded,
    selectedGenreId: Int?,
    innerPadding: PaddingValues,
    onNavigateBack: (selectedGenreId: Int?) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(innerPadding),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.genreList.size) {
            val genre = state.genreList[it]
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20))
                    .background(if (genre.id == selectedGenreId) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceContainer)
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .clickable(onClick = { onNavigateBack(genre.id) })
            ) {
                Text(
                    genre.name,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (genre.id == selectedGenreId) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieGenresListPreview() {
    Box(
        Modifier
            .width(200.dp)
            .height(400.dp)
    ) {
        MovieGenresList(
            MovieGenresListState.Loaded(
                listOf(
                    MovieGenre(0, "Horror"), MovieGenre(
                        1,
                        "Action"
                    )
                )
            ),
            0,
            PaddingValues()
        ) {}
    }
}
