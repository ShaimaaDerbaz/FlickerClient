package com.flickerclient.data.repository

import com.flickerclient.data.database.FlickerImageDatabase
import com.flickerclient.data.model.FlickrImage
import io.reactivex.Completable
import io.reactivex.Observable


class LocalFlickerImageData(private var flickerImageDatabase: FlickerImageDatabase) {
    fun getPopularImagesFromLocal(): Observable<List<FlickrImage>> {
        var imagesPopualerObservable =
            flickerImageDatabase.flickerImageDao().getFlikerImages()


        return imagesPopualerObservable
    }

    fun insertFlickerImagesLocally(flickrImage: List<FlickrImage>): Completable {
        var imagesPopualerInsert =
            flickerImageDatabase.flickerImageDao().insertFlickerImage(flickrImage)

        return imagesPopualerInsert

    }

    fun deleteFlickerImagesLocally(): Completable {
        var imagesPopualerDelete =
            flickerImageDatabase.flickerImageDao().deleteAllImages()

        return imagesPopualerDelete

    }
}