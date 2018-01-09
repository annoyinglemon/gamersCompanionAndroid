package lemond.annoying.gamerscompanion.repository.service;


import java.util.List;

import io.reactivex.Single;
import lemond.annoying.gamerscompanion.repository.objects.Pulse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsService {

    @GET("/pulses/?order=published_at:desc")
    Single<List<Pulse>> getLatestNewsIds(@Query("filter[published_at][lt]") long timeNowInMilliSeconds);

    @GET("/pulses/{pulseIds}?fields=title,url,image,author,published_at")
    Single<List<Pulse>> getNewsDetails(@Path("pulseIds") String pulseIds);

}
