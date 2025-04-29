package pl.dkd.movieapp.data.movies.api

import okhttp3.Interceptor
import okhttp3.Response

class MoviesAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authorizedUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", "")
            .build()
        val authorizedRequest = chain.request()
            .newBuilder()
            .url(authorizedUrl)
            .build()

        return chain.proceed(authorizedRequest);
    }
}
