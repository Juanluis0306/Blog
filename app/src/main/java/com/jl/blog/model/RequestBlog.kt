package com.jl.blog.model

import com.google.gson.annotations.SerializedName

data class RequestBlog(
    var kind: String? = null,
    var blog: Blog? = Blog(),
    var title: String? = null,
    var content: String? = null
)

data class Blog(
    var id: String? = null
)
