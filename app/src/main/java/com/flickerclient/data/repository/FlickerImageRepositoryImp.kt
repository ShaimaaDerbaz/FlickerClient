package com.flickerclient.data.repository

import com.flickerclient.data.model.FlickrImage
import io.reactivex.Completable
import io.reactivex.Observable
import java.lang.Exception

class FlickerImageRepositoryImp(
    private val remoteFlickerImageData: RemoteFlickerImageData,
    private val localFlickerImageData: LocalFlickerImageData
) : FlickerImageRepository {
    override fun getPopularImages(
        apiKey: String?,
        photosPerPage: Int,
        pageNumber: Int,
        format: String?,
        jsonCallback: Int
    ): Observable<List<FlickrImage>> {
        val deleteLocalCompletable = Completable.create { emitter ->
            if (pageNumber == 1) {
                try {
                    localFlickerImageData.deleteFlickerImagesLocally().blockingAwait()
                    emitter.onComplete()
                } catch (exc: Exception) {
                    emitter.onError(exc)
                }
            }
            emitter.onComplete()
        }
        val remoteObservable = remoteFlickerImageData.getPopularImagesFromServer(
            apiKey,
            photosPerPage,
            pageNumber,
            format,
            jsonCallback
        )
            .flatMap { deleteLocalCompletable.andThen(Observable.just(it)) }
            .flatMap {
                localFlickerImageData.insertFlickerImagesLocally(it).andThen(Observable.just(it))
            }
        val dataObservable = if (pageNumber == 1) {
            remoteObservable.onErrorResumeNext(localFlickerImageData.getPopularImagesFromLocal())
        } else {
            remoteObservable
        }

        return dataObservable
    }

}