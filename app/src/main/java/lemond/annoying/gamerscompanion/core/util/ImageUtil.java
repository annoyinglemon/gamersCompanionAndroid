package lemond.annoying.gamerscompanion.core.util;



public class ImageUtil {

    public enum ImageSize {
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

        private final String name;

        ImageSize(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            // (otherName == null) check is not needed because name.equals(null) returns false
            return name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }
    }

    /**
     * https://igdb.github.io/api/references/images/
     * @param imageId id of image, e.g cloudinary_id
     * @param imageSize imageSize enum
     * @param enableRetina enables retina (DPR 2.0) size if true
     * @return
     */
    public static String getImageUrl(String imageId, ImageSize imageSize, boolean enableRetina) {
        String pathFileName = imageId.concat(".jpg");
        String pathImageSize = "t_".concat(imageSize.toString());
        if (enableRetina) {
            pathImageSize = pathImageSize.concat("_2x");
        }
        return "https://images.igdb.com/igdb/image/upload/" + pathImageSize + "/" + pathFileName;
    }

}
