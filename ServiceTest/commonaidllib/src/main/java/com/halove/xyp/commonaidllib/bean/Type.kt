package com.halove.xyp.commonaidllib.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by xyp on 2018/10/8.
 */
enum class Type : Parcelable{
    RED,BLUE;

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Type> {
        override fun createFromParcel(parcel: Parcel): Type {
            return Type.values()[parcel.readInt()]
        }

        override fun newArray(size: Int): Array<Type?> {
            return arrayOfNulls(size)
        }
    }
}