package pl.dkd.movieapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Qualifier
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import pl.dkd.movieapp.data.movies.api.MoviesAuthInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoviesApi


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private val json = Json { ignoreUnknownKeys = true }

    @Provides
    @MoviesApi
    fun provideMoviesRetrofit(@MoviesApi okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(
            json.asConverterFactory(
                MediaType.get("application/json; charset=UTF8")
            )
        )
        .client(okHttpClient)
        .build()

    @Provides
    @MoviesApi
    fun provideMoviesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(MoviesAuthInterceptor())
        .build()
}
