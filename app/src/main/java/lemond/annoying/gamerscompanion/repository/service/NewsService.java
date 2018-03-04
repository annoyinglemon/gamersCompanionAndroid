package lemond.annoying.gamerscompanion.repository.service;


import java.util.List;

import io.reactivex.Single;
import lemond.annoying.gamerscompanion.repository.objects.Pulse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("/pulses/?order=published_at:desc&fields=title,url,image,author,published_at")
    Single<List<Pulse>> getLatestNews(@Query("filter[published_at][lt]") long timeNowInMilliSeconds);

}
