package org.sopt.carrot16_2.util

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <ResponseType> Call<ResponseType>.enqueueUtil(
    onSuccess: (ResponseType) -> (Unit),
    onError:((stateCode:Int) -> Unit)? = null
){
    this.enqueue(object : Callback<ResponseType>{
        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if(response.isSuccessful){
                onSuccess.invoke(response.body() ?: return)
                Log.e("rrrrrrrrrrrr",response.code().toString())
                Log.e("rrrrrrrrrrrr",response.body().toString())
            }else{
                onError?.invoke(response.code())
                Log.e("rrrrrrrrrrrr",response.code().toString())
            }
        }

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Log.d("NetworkTest","error:$t")
        }
    })
}