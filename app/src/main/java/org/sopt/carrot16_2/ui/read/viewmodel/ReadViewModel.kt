package org.sopt.carrot16_2.ui.read.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.carrot16_2.data.remote.RetrofitBuilder
import org.sopt.carrot16_2.data.remote.entity.response.ReadResponse
import org.sopt.carrot16_2.data.remote.entity.response.User
import org.sopt.carrot16_2.data.remote.service.ReadService

class ReadViewModel : ViewModel() {
    private val readService: ReadService = RetrofitBuilder.readService

    private val _readItem = MutableLiveData<ReadResponse>()
    val readItem: LiveData<ReadResponse> = _readItem

    fun initState(state: Int) {
        _readItem.value?.onSale = state
        Log.d("testttt", readItem.value?.onSale.toString())
    }

    fun initHeart() {
        _readItem.value?.isLiked = readItem.value?.isLiked != true
        Log.d("testttt", readItem.value?.isLiked.toString())
    }

    fun getReadItem(readId: Int) {
        //test
        val list: List<String> = listOf(
            "https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png",
            "https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png",
            "https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png"
        )
        _readItem.value = ReadResponse(
            "category",
            "content",
            "createdAt",
            list,
            true,
            "가격 제안 가능",
            0,
            16000,
            "title",
            User(
                "area",
                "name",
                "https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png"
            ),
            1
        )

        readItem.value?.let { initState(it.onSale) }
    }

    companion object {
        const val STATE_SELLING = 0
        const val STATE_COMPLETED = 1
        const val STATE_RESERVING = 2
    }
}
