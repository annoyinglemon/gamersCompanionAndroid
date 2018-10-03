package lemond.annoying.gamerscompanion.repository.objects


import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class Image protected constructor(parcel: Parcel) : Parcelable {
    @SerializedName("url")
    var url: String = parcel.readString()!!
    @SerializedName("cloudinary_id")
    var cloudinary_id: String = parcel.readString()!!

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(url)
        dest.writeString(cloudinary_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Image> {
            override fun createFromParcel(parcel: Parcel): Image {
                return Image(parcel)
            }

            override fun newArray(size: Int): Array<Image?> {
                return arrayOfNulls(size)
            }
        }
    }
}
