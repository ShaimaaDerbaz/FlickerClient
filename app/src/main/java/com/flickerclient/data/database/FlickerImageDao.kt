package com.flickerclient.data.database

import androidx.room.*
import com.flickerclient.data.model.FlickrImage
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface FlickerImageDao {

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertFlickerImage(flickerImages: List<FlickrImage>):Completable

    @Query("select * from flicker_image_table")
    fun getFlikerImages(): Observable<List<FlickrImage>>

    @Query ("delete from flicker_image_table")
    fun deleteAllImages() :Completable
}