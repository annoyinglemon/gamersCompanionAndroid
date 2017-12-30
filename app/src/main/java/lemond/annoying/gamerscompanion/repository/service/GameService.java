package lemond.annoying.gamerscompanion.repository.service;


import java.util.List;

import lemond.annoying.gamerscompanion.repository.objects.Game;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameService {

    @GET("/games/?fields=name,cover&order=popularity:asc&filter[popularity][gt]=99")
    Call<List<Game>> getTrendingGames();

    @GET("/games/?fields=name,cover&order=hypes:asc&filter[hypes][gt]=99")
    Call<List<Game>> getPopularGames(@Query("filter[first_release_date][lt]") long timeNowInMilliSeconds);

    @GET("/games/?fields=name,cover,first_release_date&order=first_release_date:asc&filter[hypes][gt]=19")
    Call<List<Game>> getHypedGames(@Query("filter[first_release_date][gt]") long timeNowInMilliSeconds);

}
