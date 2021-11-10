package com.flickerclient.presenter



interface HomePresenter {
    fun getPopularImages(apiKey: String?,
                         photosPerPage: Int,
                         pageNumber: Int,
                         format: String?,
                         jsonCallback: Int)


}