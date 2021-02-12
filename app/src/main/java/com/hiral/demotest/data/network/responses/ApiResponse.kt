package com.hiral.demotest.data.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse(

    @SerializedName("userId")
    @Expose
    val userId: String?,
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("body")
    @Expose
    val body: String?
)