package com.flickerclient.data.repository

import com.flickerclient.data.model.BaseResponse
import com.flickerclient.data.model.FlickrImage
import com.flickerclient.data.network.FlickerApi
import io.reactivex.Observable


class RemoteFlickerImageData  (private var flickerApi: FlickerApi){
    fun getPopularImagesFromServer(apiKey: String?,
                                   photosPerPage: Int,
                                   pageNumber: Int,
                                   format: String?,
                                   jsonCallback: Int ): Observable<List<FlickrImage>>
    {
         var imagesPopualerObservable =
             flickerApi.getPopularImages(apiKey,photosPerPage,
         pageNumber,format,jsonCallback)?.map {
             t: BaseResponse ->  t.photos.flickrImages
         }

        return imagesPopualerObservable
    }


}