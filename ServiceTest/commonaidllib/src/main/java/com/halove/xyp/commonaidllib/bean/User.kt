package com.halove.xyp.commonaidllib.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by xyp on 2018/9/25.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
data class User(var name: String, var nameBean: Name, var enum: Type) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readParcelable(Name::class.java.classLoader),
            parcel.readParcelable(Type::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeParcelable(nameBean, flags)
        parcel.writeParcelable(enum, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}
