package lemond.annoying.gamerscompanion.fragment_news.viewmodel;


public class PublishedTimeFromNow {

    public enum TimeUnit {
        DAY,
        HOUR,
        MINUTE,
        SECOND,
        MOMENT
    }

    public TimeUnit timeUnit;
    public int timeDuration = 0;

    PublishedTimeFromNow(long publishedTimeMillis) {
        long now = System.currentTimeMillis();
        long diff = now - publishedTimeMillis;

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long numberDuration;

        if ((numberDuration = (int) diff / daysInMilli) > 0) {
            timeUnit = TimeUnit.DAY;
            timeDuration = (int) numberDuration;
        } else if ((numberDuration = diff / hoursInMilli) > 0) {
            timeUnit = TimeUnit.HOUR;
            timeDuration = (int) numberDuration;
        } else if ((numberDuration = diff / minutesInMilli) > 0) {
            timeUnit = TimeUnit.MINUTE;
            timeDuration = (int) numberDuration;
        } else if ((numberDuration = diff / secondsInMilli) > 0) {
            timeUnit = TimeUnit.SECOND;
            timeDuration = (int) numberDuration;
        } else {
            timeUnit = TimeUnit.MOMENT;
        }
    }
}
