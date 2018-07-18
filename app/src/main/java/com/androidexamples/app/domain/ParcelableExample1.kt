package com.androidexamples.app.domain

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by marcos on 08/01/2017.
 */

class ParcelableExample1 : Parcelable {

    // ao passar um objeto parcelable por uma Intent, utilizar: intent.putExtra(key, objeto)
    // ao recuperar o objeto, utilizar: Objeto obj = (Objeto)intent.GetParcelableExtra(key)

    // Attributes
    // Getters and Setters
    var id: Long = 0
    var age: Int = 0
    var name: String? = null
    var weight: Double = 0.toDouble()

    // Parcelable

    constructor() {}

    protected constructor(`in`: Parcel) {
        id = `in`.readLong()
        age = `in`.readInt()
        name = `in`.readString()
        weight = `in`.readDouble()
    }

    override fun describeContents(): Int {
        return 0
    }

    // Dados a serem serializados
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeInt(age)
        dest.writeString(name)
        dest.writeDouble(weight)
    }

    // Lê os dados na mesma ordem em que foram escritos
    fun readFromParcel(parcel: Parcel) {
        id = parcel.readLong()
        age = parcel.readInt()
        name = parcel.readString()
        weight = parcel.readDouble()
    }

    companion object {

        val CREATOR: Parcelable.Creator<ParcelableExample1> = object : Parcelable.Creator<ParcelableExample1> {
            override fun createFromParcel(`in`: Parcel): ParcelableExample1 {
                return ParcelableExample1(`in`)
            }

            override fun newArray(size: Int): Array<ParcelableExample1?> {
                return arrayOfNulls(size)
            }
        }
    }
}
