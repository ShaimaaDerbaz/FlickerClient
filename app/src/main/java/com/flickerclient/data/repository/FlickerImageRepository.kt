package com.flickerclient.data.repository


import com.flickerclient.data.model.FlickrImage
import io.reactivex.Observable


interface FlickerImageRepository {
    fun getPopularImages(
        apiKey: String?,
        photosPerPage: Int,
        pageNumber: Int,
        format: String?,
        jsonCallback: Int
    ): Observable<List<FlickrImage>>

}