package lemond.annoying.gamerscompanion.module;


import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.game.model.GameService;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private static final String BASE_URL = "https://api-2445582011268.apicast.io";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String ACCEPT_PARAM = "application/json";
    private static final String KEY_HEADER = "user-key";
    private static final String KEY_PARAM = "631d96fbe98593bdbe7d51e9dadcdbc8";

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.cache(cache);
        clientBuilder.addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder();
            builder.addHeader(ACCEPT_HEADER, ACCEPT_PARAM);
            builder.addHeader(KEY_HEADER, KEY_PARAM);
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        });
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    GameService provideGameService(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(GameService.class);
    }
}
