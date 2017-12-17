package lemond.annoying.gamerscompanion.game.model;


import java.util.List;

import lemond.annoying.gamerscompanion.game.objects.Game;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GameService {

    @GET("games/?fields=*&order=popularity:desc")
    Call<List<Game>> getMostPopular();

}
