package org.sopt.carrot16_2.data.remote.entity.request

data class CreateRequest(
    val imageCount : Int,
    val title : String,
    val category : String,
    val price : Int,
    val contents : String,
    val isPriceSuggestion : Boolean
)
