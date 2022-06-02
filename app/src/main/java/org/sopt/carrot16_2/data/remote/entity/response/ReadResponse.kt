package org.sopt.carrot16_2.data.remote.entity.response

data class ReadResponse(
    val __v: Int,
    val _id: String,
    val category: String,
    val contents: String,
    val createAt: String,
    val images: List<String>,
    var isLiked: Boolean,
    val isPriceSuggestion: Boolean,
    var onSale: Int,
    val price: Int,
    val title: String,
    val user: User,
    val view: Int
)

data class User(
    val _id: String,
    val name: String,
    val profile: String,
    val region: String
)
