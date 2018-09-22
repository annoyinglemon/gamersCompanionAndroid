package lemond.annoying.gamerscompanion.repository.objects

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Collection() : Parcelable {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("games")
    var games: List<Game>? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Long::class.java.classLoader) as? Long
        name = parcel.readString()
        games = parcel.createTypedArrayList(Game.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeTypedList(games)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Collection> {
        override fun createFromParcel(parcel: Parcel): Collection {
            return Collection(parcel)
        }

        override fun newArray(size: Int): Array<Collection?> {
            return arrayOfNulls(size)
        }
    }


}
