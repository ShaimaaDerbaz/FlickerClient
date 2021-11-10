package com.flickerclient.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flickerclient.data.model.FlickrImage

@Database(entities = [FlickrImage::class], version = 1)
abstract class FlickerImageDatabase : RoomDatabase() {


    abstract fun flickerImageDao(): FlickerImageDao

    companion object {
        private var instance: FlickerImageDatabase? = null

        @Synchronized
         fun getInstance(context: Context): FlickerImageDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlickerImageDatabase::class.java, "flicker_image_database"
                ).fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }


}