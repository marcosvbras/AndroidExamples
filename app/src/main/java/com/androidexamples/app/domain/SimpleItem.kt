package com.androidexamples.app.domain

/**
 * Created by marcos on 15/12/2016.
 */

class SimpleItem {

    var imageResource: Int? = null
    var name: String? = null
    var colorResource: Int? = null

    constructor(name: String?, imageResource: Int?) {
        this.imageResource = imageResource
        this.name = name
    }

    constructor(name: String?, imageResource: Int?, colorResource: Int?) {
        this.imageResource = imageResource
        this.name = name
        this.colorResource = colorResource
    }

    override fun toString(): String {
        return this.name?: ""
    }
}
