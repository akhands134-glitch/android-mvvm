package com.learndroid.jetpackcompose.di

import com.learndroid.jetpackcompose.data.remote.TodoApi
import com.learndroid.jetpackcompose.data.repository.TodoRepositoryImpl
import com.learndroid.jetpackcompose.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer token")
                .build()
            chain.proceed(request)
        }
        .addInterceptor(AuthInterceptor())
        .build()

    class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = "your_token_here"

            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()

            return chain.proceed(newRequest)
        }
    }
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideTodoApi(retrofit: Retrofit): TodoApi {
        return retrofit.create(TodoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTodoRepository(api: TodoApi): TodoRepository {
        return TodoRepositoryImpl(api)
    }

}
