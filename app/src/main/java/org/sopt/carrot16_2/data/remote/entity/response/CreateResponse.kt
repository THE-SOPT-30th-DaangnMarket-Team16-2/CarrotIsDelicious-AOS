package org.sopt.carrot16_2.data.remote.entity.response

data class CreateResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : Data
){
    data class Data(
        val id : String
    )
}
