package lemond.annoying.gamerscompanion.fragment_news.viewmodel;


import android.view.View;

import java.net.MalformedURLException;
import java.net.URL;

import lemond.annoying.gamerscompanion.repository.objects.Pulse;

public class NewsItemViewModel {

    private final Pulse pulse;

    public NewsItemViewModel(Pulse pulse) {
        this.pulse = pulse;
    }

    public Pulse getPulse() {
        return pulse;
    }

    public void onPulseItemClick(View view) {
        // TODO: 2018-01-05 launch website using pulse.url
    }

    public String getBaseUrl() {
        try {
            URL myURL = new URL(pulse.url);
            return myURL.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PublishedTimeFromNow getPublishDateDifference() {
        return new PublishedTimeFromNow(pulse.published_at);
    }

}
