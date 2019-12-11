package com.payback.api

import com.google.gson.annotations.SerializedName
import com.payback.models.Photo

class PhotoSearchResponse(
    @SerializedName("totalHits")
    val totalHits: Int = 0,
    @SerializedName("hits")
    val photos: List<Photo>,
    @SerializedName("total")
    val total: Int
)