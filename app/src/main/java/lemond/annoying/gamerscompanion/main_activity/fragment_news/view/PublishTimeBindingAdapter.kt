package lemond.annoying.gamerscompanion.main_activity.fragment_news.view


import android.databinding.BindingAdapter
import android.widget.TextView

import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.PublishedTimeFromNow

class PublishTimeBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("publishTimeFromNow")
        fun setPublishTimeFromNow(textView: TextView, publishedTimeFromNow: PublishedTimeFromNow) {
            val resources = textView.resources
            val timeDuration = publishedTimeFromNow.timeDuration

            when (publishedTimeFromNow.timeUnit) {
                PublishedTimeFromNow.TimeUnit.DAY -> textView.text = resources.getQuantityString(R.plurals.publish_past_day, timeDuration, timeDuration)
                PublishedTimeFromNow.TimeUnit.HOUR -> textView.text = resources.getQuantityString(R.plurals.publish_past_hour, timeDuration, timeDuration)
                PublishedTimeFromNow.TimeUnit.MINUTE -> textView.text = resources.getQuantityString(R.plurals.publish_past_minute, timeDuration, timeDuration)
                PublishedTimeFromNow.TimeUnit.SECOND -> textView.text = resources.getQuantityString(R.plurals.publish_past_second, timeDuration, timeDuration)
                PublishedTimeFromNow.TimeUnit.MOMENT -> textView.text = resources.getString(R.string.publish_past_moment)
                else -> textView.text = resources.getString(R.string.publish_past_moment)
            }
        }
    }
}
