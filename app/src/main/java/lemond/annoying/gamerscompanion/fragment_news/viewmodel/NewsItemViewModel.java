package lemond.annoying.gamerscompanion.fragment_news.viewmodel;


import android.content.res.Resources;
import android.view.View;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.repository.objects.Pulse;

public class NewsItemViewModel {

    private Resources resources;
    private Pulse pulse;

    public NewsItemViewModel(Resources resources, Pulse pulse) {
        this.resources = resources;
        this.pulse = pulse;
    }

    public Pulse getPulse() {
        return pulse;
    }

    public void onPulseItemClick(View view) {
        // TODO: 2018-01-05 launch website using pulse.url
    }

    public String getPublishDateDifference() {
        long now = System.currentTimeMillis();
        long diff = now - pulse.published_at;

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long numberDuration;

        if ((numberDuration = diff / daysInMilli) > 0) {
            return resources.getQuantityString(R.plurals.publish_past_day, (int) numberDuration);
        } else if ((numberDuration = diff / hoursInMilli) > 0) {
            return resources.getQuantityString(R.plurals.publish_past_hour, (int) numberDuration);
        } else if ((numberDuration = diff / minutesInMilli) > 0) {
            return resources.getQuantityString(R.plurals.publish_past_minute, (int) numberDuration);
        } else if ((numberDuration = diff / secondsInMilli) > 0) {
            return resources.getQuantityString(R.plurals.publish_past_second, (int) numberDuration);
        } else {
            return resources.getString(R.string.publish_past_moment);
        }
    }

}
