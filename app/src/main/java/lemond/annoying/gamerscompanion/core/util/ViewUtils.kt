package lemond.annoying.gamerscompanion.core.util

import android.content.Context
import android.util.DisplayMetrics




class ViewUtils {

    companion object {
        /**
         * This method converts dp unit to equivalent pixels, depending on device density.
         *
         * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
         * @param context Context to get resources and device specific display metrics
         * @return A float value to represent px equivalent to dp depending on device density
         */
        fun convertDpToPixel(dp: Int, context: Context): Int {
            val resources = context.getResources()
            val metrics = resources.getDisplayMetrics()
            return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
        }

        /**
         * This method converts device specific pixels to density independent pixels.
         *
         * @param px A value in px (pixels) unit. Which we need to convert into db
         * @param context Context to get resources and device specific display metrics
         * @return A float value to represent dp equivalent to px value
         */
        fun convertPixelsToDp(px: Int, context: Context): Int {
            val resources = context.getResources()
            val metrics = resources.getDisplayMetrics()
            return px / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

}