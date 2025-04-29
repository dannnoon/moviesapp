package pl.dkd.movieapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.dkd.movieapp.data.movies.repository.MoviesRepositoryImpl
import pl.dkd.movieapp.domain.movies.repository.MoviesRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMoviesRepository(
        repository: MoviesRepositoryImpl
    ): MoviesRepository
}