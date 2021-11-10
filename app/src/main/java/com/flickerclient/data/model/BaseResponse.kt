package com.flickerclient.data.model

import com.google.gson.annotations.SerializedName

class BaseResponse
    (@SerializedName("photos")
     val photos:  PhotoResponse)
{
}