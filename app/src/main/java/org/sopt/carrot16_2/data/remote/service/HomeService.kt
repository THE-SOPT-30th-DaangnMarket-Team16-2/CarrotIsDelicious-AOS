package org.sopt.carrot16_2.data.remote.service

import org.sopt.carrot16_2.data.remote.entity.response.HomeResponse
import retrofit2.Call
import retrofit2.http.GET

interface HomeService {
    @GET("feed")
    fun getHomeFeed(): Call<HomeResponse>
}