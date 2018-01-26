package lemond.annoying.gamerscompanion.fragment_news.view;


import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.widget.TextView;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.PublishedTimeFromNow;

public class PublishTimeBindingAdapter {

    @BindingAdapter("android:publishTimeFromNow")
    public static void setPublishTimeFromNow(TextView textView, PublishedTimeFromNow publishedTimeFromNow) {
        Resources resources = textView.getResources();
        int timeDuration = publishedTimeFromNow.timeDuration;

        switch (publishedTimeFromNow.timeUnit) {
            case DAY:
                textView.setText(resources.getQuantityString(R.plurals.publish_past_day, timeDuration, timeDuration));
                break;
            case HOUR:
                textView.setText(resources.getQuantityString(R.plurals.publish_past_hour, timeDuration, timeDuration));
                break;
            case MINUTE:
                textView.setText(resources.getQuantityString(R.plurals.publish_past_minute, timeDuration, timeDuration));
                break;
            case SECOND:
                textView.setText(resources.getQuantityString(R.plurals.publish_past_second, timeDuration, timeDuration));
                break;
            default:
            case MOMENT:
                textView.setText(resources.getString(R.string.publish_past_moment));
                break;
        }
    }


}
