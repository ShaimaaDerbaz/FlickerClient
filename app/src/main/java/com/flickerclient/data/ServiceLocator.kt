package com.flickerclient.data

import android.content.Context
import com.flickerclient.data.database.FlickerImageDatabase
import com.flickerclient.data.network.FlickerApi
import com.flickerclient.data.network.RetrofitModule
import com.flickerclient.data.repository.FlickerImageRepository
import com.flickerclient.data.repository.FlickerImageRepositoryImp
import com.flickerclient.data.repository.LocalFlickerImageData
import com.flickerclient.data.repository.RemoteFlickerImageData

class ServiceLocator private constructor(baseUrl: String, context: Context) {
    private val remoteFlickerImageRepository: FlickerImageRepository


    fun geFlickerImageRepository(): FlickerImageRepository {
        return remoteFlickerImageRepository
    }

    companion object {
        private var sInstance: ServiceLocator? = null
        @JvmStatic
        fun initServiceLocator(baseUrl: String, context: Context) {
            if (sInstance == null) synchronized(
                ServiceLocator::class.java
            ) {
                if (sInstance == null) sInstance =
                    ServiceLocator(baseUrl, context)
            }
        }

        val instance: ServiceLocator?
            get() {
                if (sInstance == null) throw RuntimeException(
                    "ServiceLocator must be initialized"
                )
                return sInstance
            }
    }

    init {
        val retrofitModule = RetrofitModule(baseUrl)
        val flickerImageDatabase = FlickerImageDatabase.getInstance(context)
        val homeApi = FlickerApi(retrofitModule.retrofit)
        val remoteData=RemoteFlickerImageData(homeApi)
        val localData=LocalFlickerImageData(flickerImageDatabase)
        remoteFlickerImageRepository = FlickerImageRepositoryImp(remoteData,localData)
    }
}