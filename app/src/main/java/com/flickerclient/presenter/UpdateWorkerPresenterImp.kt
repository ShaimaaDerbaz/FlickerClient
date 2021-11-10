package com.flickerclient.presenter

import com.flickerclient.data.model.FlickrImage
import com.flickerclient.data.repository.FlickerImageRepository
import io.reactivex.Observable

class UpdateWorkerPresenterImp(var flickerImageRepository: FlickerImageRepository) :
    UpdateWorkerPresenter {
    override fun getPopularImages(
        apiKey: String?,
        photosPerPage: Int,
        pageNumber: Int,
        format: String?,
        jsonCallback: Int
    ): Observable<List<FlickrImage>> {
        return flickerImageRepository.getPopularImages(
            apiKey,
            photosPerPage,
            pageNumber,
            format,
            jsonCallback
        )

    }
}
