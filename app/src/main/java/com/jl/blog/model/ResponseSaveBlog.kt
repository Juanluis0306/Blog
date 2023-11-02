package com.jl.blog.model

import com.google.gson.annotations.SerializedName

data class ResponseSaveBlog(
    @SerializedName("kind") var kind: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("blog") var blog: Blog? = Blog(),
    @SerializedName("published") var published: String? = null,
    @SerializedName("updated") var updated: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("selfLink") var selfLink: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("content") var content: String? = null,
    @SerializedName("author") var author: Author? = Author(),
)
