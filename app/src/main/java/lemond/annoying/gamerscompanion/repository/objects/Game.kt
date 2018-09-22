package lemond.annoying.gamerscompanion.repository.objects


import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class Game() : Parcelable {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("cover")
    var cover: Image? = null
    @SerializedName("rating")
    var userRatingAverage: Double? = null
    @SerializedName("rating_count")
    var userRatingCount: Int? = null
    @SerializedName("aggregated_rating")
    var criticRatingAverage: Double? = null
    @SerializedName("aggregated_rating_count")
    var criticRatingCount: Int? = null
    @SerializedName("total_rating")
    var totalRatingAverage: Double? = null
    @SerializedName("summary")
    var summary: String? = null
    @SerializedName("storyline")
    var storyLine: String? = null

    @SerializedName("first_release_date")
    var first_release_date: Long? = null
    @SerializedName("themes")
    var themes: List<Theme>? = null
    @SerializedName("platforms")
    var platforms: List<Platform>? = null

    @SerializedName("keywords")
    var keywordTags: List<Keyword>? = null
    @SerializedName("genres")
    var genreTags: List<Genre>? = null

    @SerializedName("game")
    var mainParentGame: Game? = null
    @SerializedName("version_parent")
    var versionParentGame: Game? = null

    @SerializedName("dlcs")
    var dlcGames: List<Game>? = null
    @SerializedName("expansions")
    var expansionGames: List<Game>? = null
    @SerializedName("standalone_expansions")
    var standaloneExpansionGames: List<Game>? = null
    @SerializedName("games")
    var similarGames: List<Game>? = null

    @SerializedName("collection")
    var seriesCollection: Collection? = null
    @SerializedName("franchise")
    var franchise: Franchise? = null

    @SerializedName("developers")
    var developers: List<Company>? = null
    @SerializedName("publishers")
    var publishers: List<Company>? = null


    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Long::class.java.classLoader) as? Long
        name = parcel.readString()
        cover = parcel.readParcelable(Image::class.java.classLoader)
        userRatingAverage = parcel.readValue(Double::class.java.classLoader) as? Double
        userRatingCount = parcel.readValue(Int::class.java.classLoader) as? Int
        criticRatingAverage = parcel.readValue(Double::class.java.classLoader) as? Double
        criticRatingCount = parcel.readValue(Int::class.java.classLoader) as? Int
        totalRatingAverage = parcel.readValue(Double::class.java.classLoader) as? Double
        summary = parcel.readString()
        storyLine = parcel.readString()
        first_release_date = parcel.readValue(Long::class.java.classLoader) as? Long
        themes = parcel.createTypedArrayList(Theme)
        platforms = parcel.createTypedArrayList(Platform)
        keywordTags = parcel.createTypedArrayList(Keyword)
        genreTags = parcel.createTypedArrayList(Genre)
        mainParentGame = parcel.readParcelable(Game::class.java.classLoader)
        versionParentGame = parcel.readParcelable(Game::class.java.classLoader)
        dlcGames = parcel.createTypedArrayList(CREATOR)
        expansionGames = parcel.createTypedArrayList(CREATOR)
        standaloneExpansionGames = parcel.createTypedArrayList(CREATOR)
        similarGames = parcel.createTypedArrayList(CREATOR)
        seriesCollection = parcel.readParcelable(Collection::class.java.classLoader)
        franchise = parcel.readParcelable(Franchise::class.java.classLoader)
        developers = parcel.createTypedArrayList(Company)
        publishers = parcel.createTypedArrayList(Company)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeParcelable(cover, flags)
        parcel.writeValue(userRatingAverage)
        parcel.writeValue(userRatingCount)
        parcel.writeValue(criticRatingAverage)
        parcel.writeValue(criticRatingCount)
        parcel.writeValue(totalRatingAverage)
        parcel.writeString(summary)
        parcel.writeString(storyLine)
        parcel.writeValue(first_release_date)
        parcel.writeTypedList(themes)
        parcel.writeTypedList(platforms)
        parcel.writeTypedList(keywordTags)
        parcel.writeTypedList(genreTags)
        parcel.writeParcelable(mainParentGame, flags)
        parcel.writeParcelable(versionParentGame, flags)
        parcel.writeTypedList(dlcGames)
        parcel.writeTypedList(expansionGames)
        parcel.writeTypedList(standaloneExpansionGames)
        parcel.writeTypedList(similarGames)
        parcel.writeParcelable(seriesCollection, flags)
        parcel.writeParcelable(franchise, flags)
        parcel.writeTypedList(developers)
        parcel.writeTypedList(publishers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }


}
