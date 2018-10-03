package lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel


class PublishedTimeFromNow internal constructor(publishedTimeMillis: Long) {

    var timeUnit: TimeUnit
    var timeDuration = 0

    enum class TimeUnit {
        DAY,
        HOUR,
        MINUTE,
        SECOND,
        MOMENT
    }

    init {
        val now = System.currentTimeMillis()
        val diff = now - publishedTimeMillis

        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24

        when {
            (diff.toInt() / daysInMilli) > 0 -> {
                timeUnit = TimeUnit.DAY
                val numberDuration: Long = (diff.toInt() / daysInMilli)
                timeDuration = numberDuration.toInt()

            }
            (diff / hoursInMilli) > 0 -> {
                timeUnit = TimeUnit.HOUR
                val numberDuration: Long = (diff.toInt() / hoursInMilli)
                timeDuration = numberDuration.toInt()

            }
            (diff / minutesInMilli) > 0 -> {
                timeUnit = TimeUnit.MINUTE
                val numberDuration: Long = (diff.toInt() / minutesInMilli)
                timeDuration = numberDuration.toInt()

            }
            (diff / secondsInMilli) > 0 -> {
                timeUnit = TimeUnit.SECOND
                val numberDuration: Long = (diff.toInt() / secondsInMilli)
                timeDuration = numberDuration.toInt()

            }
            else -> timeUnit = TimeUnit.MOMENT
        }
    }
}
