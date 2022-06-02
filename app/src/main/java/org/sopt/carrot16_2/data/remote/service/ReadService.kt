package org.sopt.carrot16_2.data.remote.service

import org.sopt.carrot16_2.data.remote.entity.request.StateRequest
import org.sopt.carrot16_2.data.remote.entity.response.BaseResponse
import org.sopt.carrot16_2.data.remote.entity.response.NoDataResponse
import org.sopt.carrot16_2.data.remote.entity.response.ReadResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReadService {
    @GET("feed/{itemId}")
    suspend fun getReadItem(
        @Path("itemId") itemId: String
    ): BaseResponse<ReadResponse>

    @PUT("feed/like/{productId}")
    suspend fun setLike(
        @Path("productId") productId: String
    ): NoDataResponse

    @PUT("feed/on-sale")
    suspend fun setOnSale(
        @Body body: StateRequest
    ): NoDataResponse
}
