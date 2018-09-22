package lemond.annoying.gamerscompanion.repository.objects

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Company() : Parcelable {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("logo")
    var logo: Image? = null
    @SerializedName("website")
    var websiteUrl: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Long::class.java.classLoader) as? Long
        name = parcel.readString()
        logo = parcel.readParcelable(Image::class.java.classLoader)
        websiteUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeParcelable(logo, flags)
        parcel.writeString(websiteUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Company> {
        override fun createFromParcel(parcel: Parcel): Company {
            return Company(parcel)
        }

        override fun newArray(size: Int): Array<Company?> {
            return arrayOfNulls(size)
        }
    }

}