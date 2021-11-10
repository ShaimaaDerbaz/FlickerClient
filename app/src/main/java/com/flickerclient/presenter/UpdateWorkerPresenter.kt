package com.flickerclient.presenter

import com.flickerclient.data.model.FlickrImage
import io.reactivex.Observable


interface UpdateWorkerPresenter {
    fun getPopularImages(
        apiKey: String?,
        photosPerPage: Int,
        pageNumber: Int,
        format: String?,
        jsonCallback: Int
    ): Observable<List<FlickrImage>>


}