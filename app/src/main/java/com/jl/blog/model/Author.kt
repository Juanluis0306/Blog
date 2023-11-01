package com.jl.blog.model

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("displayName")
    var displayName: String? = null,
    @SerializedName("url")
    var url: String? = null,
)
