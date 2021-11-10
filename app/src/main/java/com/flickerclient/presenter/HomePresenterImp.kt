package com.flickerclient.presenter

import com.flickerclient.core.converter.FlickerImagerConverter
import com.flickerclient.data.repository.FlickerImageRepository
import com.flickerclient.presenter.view.HomeView
import com.flickerclient.data.model.FlickrImage
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class HomePresenterImp(var flickerImageRepository: FlickerImageRepository, var homeView: HomeView) : HomePresenter {
    override fun getPopularImages(
        apiKey: String?,
        photosPerPage: Int,
        pageNumber: Int,
        format: String?,
        jsonCallback: Int
    ) {
        var popularImageObservable =
            flickerImageRepository.getPopularImages(apiKey, photosPerPage, pageNumber, format, jsonCallback)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


        var popularImageObserver = object : Observer<List<FlickrImage>> {

            override fun onNext(flickerImages: List<FlickrImage>) {
                var flickrImages: ArrayList<com.flickerclient.ui.model.FlickrImage> = ArrayList()

                for (i in 0..flickerImages.size - 1) {
                    var flickrImage:com.flickerclient.ui.model.FlickrImage =
                        FlickerImagerConverter.convertFlickerImagerFromServerToView(flickerImages[i])
                    flickrImages.add(flickrImage)
                }
                homeView.showPopularImages(flickrImages)
            }

            override fun onComplete() {

            }

            override fun onError(e: Throwable) {

            }

            override fun onSubscribe(s: Disposable) {
            }


        }

        popularImageObservable?.subscribe(popularImageObserver)
    }

}