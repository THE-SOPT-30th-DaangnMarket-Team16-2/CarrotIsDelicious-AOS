package org.sopt.carrot16_2.data.remote.service

import org.sopt.carrot16_2.data.remote.entity.response.BaseResponse
import org.sopt.carrot16_2.data.remote.entity.response.ReadResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ReadService {
    @GET("feed/:{itemId}")
    suspend fun getReadItem(
        @Path("itemId") itemId: String
    ): BaseResponse<ReadResponse>
}
