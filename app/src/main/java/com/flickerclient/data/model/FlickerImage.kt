package com.flickerclient.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "flicker_image_table")
data class FlickrImage (

    @SerializedName("id")
    @PrimaryKey
     val photoId :String,

    @SerializedName("owner")
     val ownerId:String,

    @SerializedName("secret")
     val secret :String,

    @SerializedName("server")
     val serverId:String,

    @SerializedName("farm")
     val farmId :Int,

    @SerializedName("title")
     val title:String
){
}