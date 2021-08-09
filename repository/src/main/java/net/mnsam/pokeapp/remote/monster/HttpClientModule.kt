package net.mnsam.pokeapp.remote.monster

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.mnsam.pokeapp.repository.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logger)
        }
        return Retrofit.Builder()
            .client(clientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://pokeapi.co/api/")
            .build()
    }
}
