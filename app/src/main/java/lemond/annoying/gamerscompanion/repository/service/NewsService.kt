package lemond.annoying.gamerscompanion.repository.service


import io.reactivex.Single
import lemond.annoying.gamerscompanion.repository.objects.Pulse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/pulses/?order=published_at:desc&fields=title,url,image,author,published_at")
    fun getLatestNews(@Query("filter[published_at][lt]") timeNowInMilliSeconds: Long): Single<List<Pulse>>

}
