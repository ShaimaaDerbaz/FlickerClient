package com.flickerclient.data.model

import com.google.gson.annotations.SerializedName

class PhotoResponse
    (
    @SerializedName("page")
    val pageNumber: Int,

    @SerializedName("pages")
    val totalPages: Int,

    @SerializedName("perpage")
    val dataPerPage: Int,

    @SerializedName("total")
    val total: Int,
    @SerializedName("photo")
    val flickrImages: List<FlickrImage>
) {
}