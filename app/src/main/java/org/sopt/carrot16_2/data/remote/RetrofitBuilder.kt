package org.sopt.carrot16_2.data.remote

import org.sopt.carrot16_2.data.remote.service.CreateService
import org.sopt.carrot16_2.data.remote.service.HomeService
import org.sopt.carrot16_2.data.remote.service.ReadService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitBuilder {
    private const val BASE_URL = "http://13.125.157.62:8000/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // val sampleService: SampleService = retrofit.create(SampleService::class.java)
    val readService: ReadService = retrofit.create(ReadService::class.java)
    val createService: CreateService = retrofit.create(CreateService::class.java)
    val homeService: HomeService = retrofit.create(HomeService::class.java)
}
