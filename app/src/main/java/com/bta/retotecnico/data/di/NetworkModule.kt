package com.bta.retotecnico.data.di

import com.example.errorhandling.exception.ResultCallAdapterFactory
import com.google.gson.GsonBuilder
import com.bta.retotecnico.data.cloud.*
import com.bta.retotecnico.domain.util.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(Constans.URLS.URL)
             .client(okhttpClient(""))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    class AuthInterceptor(token: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
            return chain.proceed(requestBuilder.build())
        }
    }

    private fun okhttpClient(token: String): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
               .readTimeout(60, TimeUnit.SECONDS)
               .connectTimeout(60, TimeUnit.SECONDS)
               .addInterceptor(interceptor)
               .addInterceptor(AuthInterceptor(""))
               .build()
    }

    @Singleton
    @Provides
    fun provideDataServiceCartasApiClient(retrofit: Retrofit): DataServiceCartasApiClientWS {
        return retrofit.create(DataServiceCartasApiClientWS::class.java)
    }

}