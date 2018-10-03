package lemond.annoying.gamerscompanion.core.util


object ImageUtil {

    enum class ImageSize constructor(private val sizeString: String) {
        COVER_SMALL("cover_small"),
        SCREENSHOT_MED("screenshot_med"),
        COVER_BIG("cover_big"),
        LOGO_MED("logo_med"),
        SCREENSHOT_BIG("screenshot_big"),
        SCREENSHOT_HUGE("screenshot_huge"),
        THUMB("thumb"),
        MICRO("micro"),
        P720("720p"),
        P1080("1080p");

        fun equalsName(otherName: String): Boolean {
            // (otherName == null) check is not needed because name.equals(null) returns false
            return sizeString == otherName
        }

        override fun toString(): String {
            return this.sizeString
        }
    }

    /**
     * returns the image url of the specified image
     * https://igdb.github.io/api/references/images/
     * @param imageId id of image, e.g cloudinary_id
     * @param imageSize imageSize enum
     * @param enableRetina enables retina (DPR 2.0) size if true
     * @return
     */
    fun getImageUrl(imageId: String?, imageSize: ImageSize, enableRetina: Boolean): String {
        if (imageId != null) {
            val pathFileName = "$imageId.jpg"
            var pathImageSize = "t_" + imageSize.toString()
            if (enableRetina) {
                pathImageSize = pathImageSize + "_2x"
            }
            return "https://images.igdb.com/igdb/image/upload/$pathImageSize/$pathFileName"
        } else {
            return ""
        }
    }


    /**
     * returns the favicon url of the specified website
     * @param websiteUrl url of website e.g. google.com
     * @return
     */
    fun getWebsiteFavIcon(websiteUrl: String): String {
        return "https://www.google.com/s2/favicons?domain=$websiteUrl"
    }

}
