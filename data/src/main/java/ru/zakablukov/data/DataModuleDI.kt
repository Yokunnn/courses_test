package ru.zakablukov.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.zakablukov.data.service.CourseService

object DataModuleDI {

    private const val BASE_URL = "https://drive.usercontent.google.com/"

    val dataModuleDI = module {

        single<Retrofit> {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(get<OkHttpClient>())
                .build()
        }

        single<HttpLoggingInterceptor> {
            HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
        }

        single<OkHttpClient> {
            OkHttpClient.Builder()
                .addInterceptor(get<HttpLoggingInterceptor>())
                .build()
        }

        single<CourseService> {
            get<Retrofit>().create(CourseService::class.java)
        }
    }
}