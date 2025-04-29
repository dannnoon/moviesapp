package pl.dkd.movieapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.dkd.movieapp.data.movies.datasource.MoviesApiDataSource
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideMoviesApiDataSource(@MoviesApi retrofit: Retrofit): MoviesApiDataSource =
        retrofit.create(MoviesApiDataSource::class.java)
}
