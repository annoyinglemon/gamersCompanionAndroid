package lemond.annoying.gamerscompanion.repository.objects

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Genre() : Parcelable {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Long::class.java.classLoader) as? Long
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Genre> {
        override fun createFromParcel(parcel: Parcel): Genre {
            return Genre(parcel)
        }

        override fun newArray(size: Int): Array<Genre?> {
            return arrayOfNulls(size)
        }
    }

}