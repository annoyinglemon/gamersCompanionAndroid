package lemond.annoying.gamerscompanion.game_activity.view

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.core.util.ViewUtils
import lemond.annoying.gamerscompanion.repository.objects.Genre
import lemond.annoying.gamerscompanion.repository.objects.Keyword
import lemond.annoying.gamerscompanion.repository.objects.Platform
import lemond.annoying.gamerscompanion.repository.objects.Theme
import android.view.Gravity


class GameDetailsBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("tags")
        fun setThemes(flexboxLayout: FlexboxLayout, tags: List<Any>?) {
            if (tags != null) {
                val themesIterator = tags.iterator()

                themesIterator.forEach {
                    when (it) {
                        is Theme -> {
                            val tagTextView: TextView = createTextView(flexboxLayout.context, it.name!!, false)
                            flexboxLayout.addView(tagTextView)
                        }
                        is Keyword -> {
                            val tagTextView: TextView = createTextView(flexboxLayout.context, it.name!!, false)
                            flexboxLayout.addView(tagTextView)
                        }
                        is Genre -> {
                            val tagTextView: TextView = createTextView(flexboxLayout.context, it.name!!, false)
                            flexboxLayout.addView(tagTextView)
                        }
                    }
                }
            }
        }

        @JvmStatic
        @BindingAdapter("keywords")
        fun setKeywords(flexboxLayout: FlexboxLayout, keywords: List<Keyword>?) {
            if (keywords != null) {
                val themesIterator = keywords.iterator()

                themesIterator.forEach {
                    val tagTextView: TextView = createTextView(flexboxLayout.context, it.name!!, false)
                    flexboxLayout.addView(tagTextView)
                }
            }
        }

        @JvmStatic
        @BindingAdapter("genres")
        fun setGenres(flexboxLayout: FlexboxLayout, genres: List<Genre>?) {
            if (genres != null) {
                val themesIterator = genres.iterator()

                themesIterator.forEach {
                    val tagTextView: TextView = createTextView(flexboxLayout.context, it.name!!, false)
                    flexboxLayout.addView(tagTextView)
                }
            }
        }

        @JvmStatic
        @BindingAdapter("platforms")
        fun setPlatforms(flexboxLayout: FlexboxLayout, platforms: List<Platform>?) {
            if (platforms != null) {
                val themesIterator = platforms.iterator()

                themesIterator.forEach {
                    val tagTextView: TextView = createTextView(flexboxLayout.context, it.name!!, true)
                    flexboxLayout.addView(tagTextView)
                }
            }
        }

        @JvmStatic
        private fun createTextView(context: Context, string: String, showPlatformIcon: Boolean): TextView {
            val tagTextView = TextView(context)
            tagTextView.text = string
            tagTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11f)
            tagTextView.typeface = Typeface.DEFAULT_BOLD
            tagTextView.background = ContextCompat.getDrawable(context, R.drawable.background_game_tags)
            tagTextView.setTextColor(ContextCompat.getColor(context, R.color.white))
            tagTextView.elevation = 5f
            tagTextView.gravity = Gravity.CENTER
            if (showPlatformIcon) {
                val iconRes : Int = getPlatformIcon(string)

                if (iconRes != -1) {
                    val image = context.getDrawable(iconRes)
                    tagTextView.setCompoundDrawablesWithIntrinsicBounds(image, null, null, null)
                }
            }

            val flexLayoutParams: FlexboxLayout.LayoutParams = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT)
            val margin: Int = ViewUtils.convertDpToPixel(3, context)
            flexLayoutParams.setMargins(margin, margin, margin, margin)
            tagTextView.layoutParams = flexLayoutParams

            val paddingTop: Int = ViewUtils.convertDpToPixel(5, context)
            val paddingBottom: Int = ViewUtils.convertDpToPixel(5, context)
            val paddingEnd: Int = ViewUtils.convertDpToPixel(8, context)
            val paddingStart: Int = ViewUtils.convertDpToPixel(8, context)
            tagTextView.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)

            return tagTextView
        }

        @JvmStatic
        private fun getPlatformIcon(platformString: String): Int {
            return when {
                platformString.contains("pc", true) -> R.drawable.ic_windows
                platformString.contains("playstation", true) -> R.drawable.ic_playstation
                platformString.contains("nintendo switch", true) -> R.drawable.ic_nintendo_switch
                platformString.contains("xbox", true) -> R.drawable.ic_xbox
                platformString.contains("mac", true) -> R.drawable.ic_apple
                platformString.contains("android", true) -> R.drawable.ic_android
                platformString.contains("ios", true) -> R.drawable.ic_ios
                platformString.contains("linux", true) -> R.drawable.ic_linux
                platformString.contains("wii u", true) -> R.drawable.ic_wii_u
                platformString.contains("web browser", true) -> R.drawable.ic_web_browser
                else -> -1
            }
        }
    }
}