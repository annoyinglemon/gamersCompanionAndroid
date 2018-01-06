package lemond.annoying.gamerscompanion.repository.service;


import java.util.List;

import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsItemViewModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsService {

    @GET("/pulses/?order=published_at:desc")
    Call<List<Long>> getLatestNewsIds(@Query("filter[published_at][lt]") long timeNowInMilliSeconds);

    @GET("/pulses/{pulseId}")
    Call<NewsItemViewModel> getNewsDetails(@Path("pulseId") long pulseId);

}
