package com.jl.blog.model

import com.google.gson.annotations.SerializedName

data class ResponseBlogs(
    @SerializedName("kind")
    var kind: String? = null,
    @SerializedName("nextPageToken")
    var nextPageToken: String? = null,
    @SerializedName("items")
    var items: ArrayList<Items> = arrayListOf(),
    @SerializedName("etag")
    var etag: String? = null
)
