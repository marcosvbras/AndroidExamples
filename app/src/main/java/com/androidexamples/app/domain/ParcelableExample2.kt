package com.androidexamples.app.domain

/**
 * Created by marcos on 08/01/2017.
 */

@org.parceler.Parcel
class ParcelableExample2 {

    // Este exemplo necessita da lib Parceler no Gradle
    // ao passar um objeto parcelable por uma Intent, utilizar: intent.putExtra(key, Parcels.wrap(objeto))
    // ao recuperar o objeto, utilizar: Objeto obj = Parcels.unwrap(intent.getParcelableExtra(key))

    // Attributes
    // Getters and Setters
    var id: Long = 0
    var age: Int = 0
    var name: String? = null
    var weight: Double = 0.toDouble()
}
