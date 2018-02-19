package lemond.annoying.gamerscompanion.app.module;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.BuildConfig;
import lemond.annoying.gamerscompanion.app.GamersApplicationScope;
import lemond.annoying.gamerscompanion.app.module.qualifier.ConnectivityInterceptor;
import lemond.annoying.gamerscompanion.core.exception.NoConnectivityException;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {

    private static final String ACCEPT_HEADER = "Accept";
    private static final String ACCEPT_PARAM = "application/json";
    private static final String KEY_HEADER = "user-key";

    @Provides
    @GamersApplicationScope
    File provideCacheFile(Context context) {
        File cacheFile = new File(context.getCacheDir(), "gamers_companion");
        cacheFile.mkdirs();
        return cacheFile;
    }

    @Provides
    @GamersApplicationScope
    Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 100);
    }

    @Provides
    @GamersApplicationScope
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Timber.i(message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @GamersApplicationScope
    Interceptor provideRequestInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder();
            builder.addHeader(ACCEPT_HEADER, ACCEPT_PARAM);
            builder.addHeader(KEY_HEADER, BuildConfig.API_KEY);
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        };
    }

    @Provides
    @GamersApplicationScope
    @ConnectivityInterceptor
    Interceptor provideConnectivityInterceptor(Context context) {
        return chain -> {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
                if (netInfo == null || !netInfo.isConnectedOrConnecting()) {
                    throw new NoConnectivityException();
                }
            } else {
                throw new NoConnectivityException();
            }
            Request.Builder builder = chain.request().newBuilder();
            return chain.proceed(builder.build());
        };
    }

    @Provides
    @GamersApplicationScope
    OkHttpClient provideOkHttpClient(Cache cache, HttpLoggingInterceptor loggingInterceptor, Interceptor requestInterceptor, @ConnectivityInterceptor Interceptor connectivityInterceptor) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.cache(cache);
        clientBuilder.addInterceptor(loggingInterceptor);
        clientBuilder.addInterceptor(requestInterceptor);
        clientBuilder.addInterceptor(connectivityInterceptor);
        return clientBuilder.build();
    }

    @Provides
    @GamersApplicationScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // TODO: 2017-12-17 find out what registerTypeAdapter is
//        gsonBuilder.registerTypeAdapter()
        return gsonBuilder.create();
    }

    @Provides
    @GamersApplicationScope
    RxJava2CallAdapterFactory provideRxJava2AdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @GamersApplicationScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson, RxJava2CallAdapterFactory rxJavaFactory) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJavaFactory)
                .client(okHttpClient)
                .build();
    }

}
