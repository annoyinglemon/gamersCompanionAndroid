package lemond.annoying.gamerscompanion.repository.service;


import java.util.List;

import lemond.annoying.gamerscompanion.repository.objects.Game;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GameService {

    @GET("/games/?fields=name,cover&order=popularity:desc")
    Call<List<Game>> getMostPopular();

}
