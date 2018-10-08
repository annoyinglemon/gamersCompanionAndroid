package lemond.annoying.gamerscompanion.repository.objects

import android.os.Parcel
import android.os.Parcelable


class Pulse() : Parcelable {

    var id: Long = 0
    var url: String? = null
    var title: String? = null
    var image: String? = null
    var author: String? = null
    var published_at: Long = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        url = parcel.readString()
        title = parcel.readString()
        image = parcel.readString()
        author = parcel.readString()
        published_at = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(url)
        parcel.writeString(title)
        parcel.writeString(image)
        parcel.writeString(author)
        parcel.writeLong(published_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pulse> {
        override fun createFromParcel(parcel: Parcel): Pulse {
            return Pulse(parcel)
        }

        override fun newArray(size: Int): Array<Pulse?> {
            return arrayOfNulls(size)
        }
    }

}
