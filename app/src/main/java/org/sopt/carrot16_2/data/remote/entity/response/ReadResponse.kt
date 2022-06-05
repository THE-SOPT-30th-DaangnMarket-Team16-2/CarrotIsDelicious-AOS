package org.sopt.carrot16_2.data.remote.entity.response

data class ReadResponse(
    val id: String,
    val category: String,
    val contents: String,
    val createAt: String,
    val image: List<String>,
    var isLiked: Boolean,
    val isPriceSuggestion: Boolean,
    var onSale: String,
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
