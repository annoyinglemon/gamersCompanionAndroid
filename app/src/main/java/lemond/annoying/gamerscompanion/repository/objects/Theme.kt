package lemond.annoying.gamerscompanion.repository.objects


import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class Theme(): Parcelable {

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

    companion object CREATOR : Parcelable.Creator<Theme> {
        override fun createFromParcel(parcel: Parcel): Theme {
            return Theme(parcel)
        }

        override fun newArray(size: Int): Array<Theme?> {
            return arrayOfNulls(size)
        }
    }

}
