package com.test.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(

        var page: Int? = null,
        var pages: Int? = null,
        var perpage: Int? = null,
        var total: Int? = null,
        var photo: ArrayList<Pics>? = null

)
