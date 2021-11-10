package com.flickerclient.data.network

import com.flickerclient.data.model.BaseResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


class FlickerApi (private  var retrofit : Retrofit) {



    fun getPopularImages(  apiKey: String?,
                          photosPerPage: Int,
                          pageNumber: Int,
                           format: String?,
                          jsonCallback: Int): Observable<BaseResponse>{
        val api = retrofit.create(Api::class.java)
        return api.getPopularImages(apiKey,photosPerPage,pageNumber,format,jsonCallback)
    }

    fun getRecentImages(  apiKey: String?,
                           photosPerPage: Int,
                           pageNumber: Int,
                           format: String?,
                           jsonCallback: Int): Observable<BaseResponse?>? {
        val api = retrofit.create(Api::class.java)
        return api.getRecentImages(apiKey,photosPerPage,pageNumber,format,jsonCallback)
    }


    interface Api
    {

        @GET("/services/rest/?method="+"flickr.interestingness.getList")
        fun getPopularImages(
            @Query("api_key") apiKey: String?,
            @Query("per_page") photosPerPage: Int,
            @Query("page") pageNumber: Int,
            @Query("format") format: String?,
            @Query("nojsoncallback") jsonCallback: Int
        ): Observable<BaseResponse>

        @GET("/services/rest/?method="+"flickr.photos.getRecent")
        fun getRecentImages(
            @Query("api_key") apiKey: String?,
            @Query("per_page") photosPerPage: Int,
            @Query("page") pageNumber: Int,
            @Query("format") format: String?,
            @Query("nojsoncallback") jsonCallback: Int
        ): Observable<BaseResponse?>?
    }

}