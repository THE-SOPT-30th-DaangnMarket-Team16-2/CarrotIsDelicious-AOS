package org.sopt.carrot16_2.data.remote.entity.response

data class ReadResponse(
    val category: String,
    val content: String,
    val createdAt: String,
    val image: List<String>,
    var isLiked: Boolean,
    val isPriceSuggestion: String,
    var onSale: Int,
    val price: Int,
    val title: String,
    val user: User,
    val view: Int
)

data class User(
    val area: String,
    val name: String,
    val profile: String
)
