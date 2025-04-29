package pl.dkd.movieapp.data.movies.repository

import pl.dkd.movieapp.domain.movies.model.Movie
import pl.dkd.movieapp.domain.movies.model.MovieDetails
import pl.dkd.movieapp.domain.movies.model.MovieGenre
import pl.dkd.movieapp.domain.movies.repository.MoviesRepository

class MoviesRepositoryImpl : MoviesRepository {
    override suspend fun getMovieGenreList(): List<MovieGenre> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieList(genreId: Int?): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieDetails(id: Int): MovieDetails {
        TODO("Not yet implemented")
    }
}