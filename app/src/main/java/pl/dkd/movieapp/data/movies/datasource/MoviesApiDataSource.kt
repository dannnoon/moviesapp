package pl.dkd.movieapp.data.movies.datasource

import pl.dkd.movieapp.data.movies.model.MovieDetailsDTO
import pl.dkd.movieapp.data.movies.model.MovieGenreResponseDTO
import pl.dkd.movieapp.data.movies.model.MovieResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiDataSource {
    @GET("genre/movie/list")
    suspend fun fetchGenreList(): MovieGenreResponseDTO

    @GET("discover/movie")
    suspend fun fetchMovieList(@Query("with_genres") genre: Int?): MovieResponseDTO

    @GET("movie/{movieId}")
    suspend fun fetchMovieDetails(@Path("movieId") movieId: Int): MovieDetailsDTO
}
