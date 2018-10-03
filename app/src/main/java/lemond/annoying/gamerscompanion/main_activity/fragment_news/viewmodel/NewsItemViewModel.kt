package lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel


import android.view.View

import java.net.MalformedURLException
import java.net.URL

import lemond.annoying.gamerscompanion.repository.objects.Pulse

class NewsItemViewModel(val pulse: Pulse) {

    val baseUrl: String?
        get() {
            return try {
                val myURL = URL(pulse.url)
                val domain = myURL.host
                if (domain.startsWith("www.")) domain.substring(4) else domain
            } catch (e: MalformedURLException) {
                e.printStackTrace()
                null
            }

        }

    val linkVisibility: Int
        get() = if (baseUrl == null || baseUrl!!.contains("feedproxy.google.com")) {
            View.GONE
        } else View.VISIBLE

    val publishDateDifference: PublishedTimeFromNow
        get() = PublishedTimeFromNow(pulse.published_at)

    fun onPulseItemClick(view: View) {
        // TODO: 2018-01-05 launch website using pulse.url
    }

}
