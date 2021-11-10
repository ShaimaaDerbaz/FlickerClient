package com.flickerclient.core.converter

import com.flickerclient.data.model.FlickrImage

class FlickerImagerConverter {
    companion object {
        fun convertFlickerImagerFromServerToView(flickrImage: FlickrImage): com.flickerclient.ui.model.FlickrImage {
            val imageUrl = "https://farm1.staticflickr.com/" +
                    flickrImage.serverId + "/" +
                    flickrImage.photoId + "_" +
                    flickrImage.secret + "_" +
                    "z"+ ".jpg"
            var flickrImageView: com.flickerclient.ui.model.FlickrImage =
                com.flickerclient.ui.model.FlickrImage(imageUrl, flickrImage.title)

            return flickrImageView
        }
    }
}