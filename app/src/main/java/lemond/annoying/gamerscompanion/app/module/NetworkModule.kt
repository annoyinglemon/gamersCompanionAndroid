package lemond.annoying.gamerscompanion.app.module


import android.content.Context
import android.net.ConnectivityManager

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.io.File

import dagger.Module
import dagger.Provides
import lemond.annoying.gamerscompanion.BuildConfig
import lemond.annoying.gamerscompanion.app.GamersApplicationScope
import lemond.annoying.gamerscompanion.app.module.qualifier.ConnectivityInterceptor
import lemond.annoying.gamerscompanion.core.exception.NoConnectivityException
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

@Module
class NetworkModule {

    @Provides
    @GamersApplicationScope
    internal fun provideCacheFile(context: Context): File {
        val cacheFile = File(context.cacheDir, "gamers_companion")
        cacheFile.mkdirs()
        return cacheFile
    }

    @Provides
    @GamersApplicationScope
    internal fun provideCache(cacheFile: File): Cache {
        return Cache(cacheFile, (10 * 1000 * 100).toLong())
    }

    @Provides
    @GamersApplicationScope
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> Timber.i(message) }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @GamersApplicationScope
    internal fun provideRequestInterceptor(): Interceptor {
        return Interceptor {
            chain ->
                val originalRequest = chain.request()
                val builder = originalRequest.newBuilder()
                builder.addHeader(ACCEPT_HEADER, ACCEPT_PARAM)
                builder.addHeader(KEY_HEADER, BuildConfig.API_KEY)
                val newRequest = builder.build()
               chain.proceed(newRequest)
        }
    }

    @Provides
    @GamersApplicationScope
    @ConnectivityInterceptor
    internal fun provideConnectivityInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            if (netInfo == null || !netInfo.isConnected) {
                throw NoConnectivityException()
            }
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        }
    }

    @Provides
    @GamersApplicationScope
    internal fun provideOkHttpClient(cache: Cache, loggingInterceptor: HttpLoggingInterceptor, requestInterceptor: Interceptor, @ConnectivityInterceptor connectivityInterceptor: Interceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.cache(cache)
        clientBuilder.addInterceptor(loggingInterceptor)
        clientBuilder.addInterceptor(requestInterceptor)
        clientBuilder.addInterceptor(connectivityInterceptor)
        return clientBuilder.build()
    }

    @Provides
    @GamersApplicationScope
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        // TODO: 2017-12-17 find out what registerTypeAdapter is
        //        gsonBuilder.registerTypeAdapter()
        return gsonBuilder.create()
    }

    @Provides
    @GamersApplicationScope
    internal fun provideRxJava2AdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @GamersApplicationScope
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson, rxJavaFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJavaFactory)
                .client(okHttpClient)
                .build()
    }

    companion object {

        private val ACCEPT_HEADER = "Accept"
        private val ACCEPT_PARAM = "application/json"
        private val KEY_HEADER = "user-key"
    }

}
