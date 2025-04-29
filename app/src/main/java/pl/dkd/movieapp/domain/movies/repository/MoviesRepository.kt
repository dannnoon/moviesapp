package pl.dkd.movieapp.domain.movies.repository

import pl.dkd.movieapp.domain.movies.model.Movie
import pl.dkd.movieapp.domain.movies.model.MovieDetails
import pl.dkd.movieapp.domain.movies.model.MovieGenre

interface MoviesRepository {
    suspend fun getMovieGenreList(): List<MovieGenre>
    suspend fun getMovieList(genreId: Int? = null): List<Movie>
    suspend fun getMovieDetails(id: Int): MovieDetails
}
