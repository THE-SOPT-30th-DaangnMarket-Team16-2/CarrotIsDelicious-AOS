package org.sopt.carrot16_2.data.remote.service

import org.sopt.carrot16_2.data.remote.entity.request.CreateRequest
import org.sopt.carrot16_2.data.remote.entity.response.CreateResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateService {
    @POST("feed")
    fun postCreate(
        @Body body : CreateRequest
    ): Call<CreateResponse>
}