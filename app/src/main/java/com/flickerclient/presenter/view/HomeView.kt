package com.flickerclient.presenter.view

import com.flickerclient.ui.model.FlickrImage

interface HomeView {
    fun showPopularImages( popularImages:List<FlickrImage>)
}