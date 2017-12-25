package lemond.annoying.gamerscompanion.repository.util;


import android.util.Log;

public class ImageUtil {

    // big image cover:
    // https://images.igdb.com/igdb/image/upload/cuwotikeyasdnpthudjf.jpg
    // small image cover
    // https://images.igdb.com/igdb/image/upload/t_thumb/cuwotikeyasdnpthudjf.jpg

    public static String getLargeCoverImageUrl(String url) {
        Log.d("kurt", url);
//        String toBeRemoved = url.substring(url.indexOf("/t_thumb"), url.lastIndexOf("/"));
//        if (!url.contains("https:"))
//            return "https:" + url.replace(toBeRemoved, "");
//        else
//            return url.replace(toBeRemoved, "");
        if (!url.contains("https:"))
            return "https:" + url;
        else
            return url;
    }

}
