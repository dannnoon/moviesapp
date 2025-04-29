package pl.dkd.movieapp.data.movies.repository

import jakarta.inject.Inject
import pl.dkd.movieapp.data.movies.datasource.MoviesApiDataSource
import pl.dkd.movieapp.data.movies.mapper.toDomain
import pl.dkd.movieapp.domain.movies.model.Movie
import pl.dkd.movieapp.domain.movies.model.MovieDetails
import pl.dkd.movieapp.domain.movies.model.MovieGenre
import pl.dkd.movieapp.domain.movies.repository.MoviesRepository

class MoviesRepositoryImpl @Inject constructor(private val moviesApi: MoviesApiDataSource) :
    MoviesRepository {
    override suspend fun getMovieGenreList(): List<MovieGenre> {
        val genreDtoList = moviesApi.fetchGenreList()
        return genreDtoList.genres.map { it.toDomain() }
    }

    override suspend fun getMovieList(genreId: Int?): List<Movie> {
        val movieDtoList = moviesApi.fetchMovieList(genreId)
        return movieDtoList.results.map { it.toDomain() }
    }

    override suspend fun getMovieDetails(id: Int): MovieDetails {
        val movieDetailsDto = moviesApi.fetchMovieDetails(id)
        return movieDetailsDto.toDomain()
    }
}
