package pl.dkd.movieapp.presentation.movies.screen.movies.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import pl.dkd.movieapp.R
import pl.dkd.movieapp.domain.movies.model.Movie
import pl.dkd.movieapp.domain.movies.model.MovieDetails
import pl.dkd.movieapp.presentation.movies.screen.movies.model.MovieItemData

@Composable
fun MovieGridItem(movieItemData: MovieItemData) {
    Box(
        Modifier
            .aspectRatio(0.65f)
            .clip(RoundedCornerShape(10))
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                ImageRequest.Builder(LocalContext.current)
                    .data(movieItemData.movie.posterUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(60.dp)
            )
            Column(
                Modifier.padding(8.dp),
            ) {
                Text(
                    movieItemData.movie.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.weight(1.0f))
                Text(
                    "${stringResource(R.string.movies_item_rating)}: ${movieItemData.movie.rating}",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    "${stringResource(R.string.movies_item_revenue)}: ${movieItemData.details.revenue}",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    "${stringResource(R.string.movies_item_budget)}: ${movieItemData.details.budget}",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }

}

@Preview
@Composable
fun MovieGridItemPreview() {
    Box(Modifier.height(200.dp)) {
        MovieGridItem(
            MovieItemData(
                movie = Movie(
                    0,
                    "Some movie title",
                    "https://i.ebayimg.com/images/g/aN0AAOSw50BduC3-/s-l1200.jpg",
                    5.0,
                ),
                details = MovieDetails(
                    100,
                    1000
                ),
            )
        )
    }
}
