package org.sopt.carrot16_2.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://83008f6f-c9e8-4bb3-a1f8-db102f7e9b13.mock.pstmn.io"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // val sampleService: SampleService = retrofit.create(SampleService::class.java)

}
