package lemond.annoying.gamerscompanion.app.module;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GamersApplicationScope;
import lemond.annoying.gamerscompanion.app.module.qualifier.ConnectivityInterceptor;
import lemond.annoying.gamerscompanion.repository.exception.NoConnectivityException;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module(includes = AppContextModule.class)
public class NetworkModule {

    private static final String BASE_URL = "https://api-2445582011268.apicast.io";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String ACCEPT_PARAM = "application/json";
    private static final String KEY_HEADER = "user-key";
    private static final String KEY_PARAM = "631d96fbe98593bdbe7d51e9dadcdbc8";

    //context is an external dependency
    @Provides
    @GamersApplicationScope
    public File provideCacheFile(Context context) {
        File cacheFile = new File(context.getCacheDir(), "gamers_companion");
        cacheFile.mkdirs();
        return cacheFile;
    }

    @Provides
    @GamersApplicationScope
    public Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 100);
    }

    @Provides
    @GamersApplicationScope
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Timber.i(message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @GamersApplicationScope
    public Interceptor provideRequestInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder();
            builder.addHeader(ACCEPT_HEADER, ACCEPT_PARAM);
            builder.addHeader(KEY_HEADER, KEY_PARAM);
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        };
    }

    @Provides
    @GamersApplicationScope
    @ConnectivityInterceptor
    public Interceptor provideConnectivityInterceptor(Context context) {
        return chain -> {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo == null || !netInfo.isConnectedOrConnecting()) {
                throw new NoConnectivityException();
            }
            Request.Builder builder = chain.request().newBuilder();
            return chain.proceed(builder.build());
        };
    }

    @Provides
    @GamersApplicationScope
    public OkHttpClient provideOkHttpClient(Cache cache, HttpLoggingInterceptor loggingInterceptor, Interceptor requestInterceptor, @ConnectivityInterceptor Interceptor connectivityInterceptor) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.cache(cache);
        clientBuilder.addInterceptor(loggingInterceptor);
        clientBuilder.addInterceptor(requestInterceptor);
        clientBuilder.addInterceptor(connectivityInterceptor);
        return clientBuilder.build();
    }

    @Provides
    @GamersApplicationScope
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // TODO: 2017-12-17 find out what registerTypeAdapter is
//        gsonBuilder.registerTypeAdapter()
        return gsonBuilder.create();
    }

    // this method provide dependency on GameServiceModule that has a 'retrofit' on its provide method
    @Provides
    @GamersApplicationScope
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

}
