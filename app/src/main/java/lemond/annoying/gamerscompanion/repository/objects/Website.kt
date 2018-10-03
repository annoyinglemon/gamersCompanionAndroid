package lemond.annoying.gamerscompanion.repository.objects


class Website {

    var category: Category? = null
    var url: String? = null

    enum class Category private constructor(val value: Int) {
        OFFICIAL(1),
        WIKIA(2),
        WIKIPEDIA(3),
        FACEBOOK(4),
        TWITTER(5),
        TWITCH(6),
        INSTAGRAM(8),
        YOUTUBE(9),
        IPHONE(10),
        IPAD(11),
        ANDROID(12),
        STEAM(13)
    }

}
