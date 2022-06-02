package org.sopt.carrot16_2.ui.read.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.carrot16_2.data.remote.RetrofitBuilder
import org.sopt.carrot16_2.data.remote.entity.request.StateRequest
import org.sopt.carrot16_2.data.remote.entity.response.ReadResponse
import org.sopt.carrot16_2.data.remote.service.ReadService

class ReadViewModel : ViewModel() {
    private val readService: ReadService = RetrofitBuilder.readService

    private val _readItem = MutableLiveData<ReadResponse>()
    val readItem: LiveData<ReadResponse> = _readItem

    private val _isLiked = MutableLiveData<Boolean>()
    val isLiked: LiveData<Boolean> = _isLiked

    private val _state = MutableLiveData<Int>()
    val state: LiveData<Int> = _state

    fun initState(state: Int) {
        _state.value = state
        _readItem.value?.onSale = state
        viewModelScope.launch {
            kotlin.runCatching {
                _readItem.value?._id?.let { id ->
                    readService.setOnSale(StateRequest(id, state))
                }
            }.onSuccess {
                Log.d("testtt", it.toString())
            }.onFailure {
                Log.d("testtt", it.toString())
            }
        }
    }

    fun initHeart() {
        _isLiked.value = _isLiked.value != true
        _readItem.value?.isLiked = readItem.value?.isLiked != true
        viewModelScope.launch {
            kotlin.runCatching {
                _readItem.value?._id?.let { readService.setLike(it) }
            }.onSuccess {
                Log.d("testtt", it.toString())
            }.onFailure {
                Log.d("testtt", it.toString())
            }
        }
    }

    fun getReadItem(readId: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                readService.getReadItem(readId)
            }.onSuccess { response ->
                val data = response.data ?: throw NullPointerException("Read 통신 에러")
                _readItem.postValue(data)
            }.onFailure {

            }
        }
    }

    companion object {
        const val STATE_SELLING = 0
        const val STATE_COMPLETED = 1
        const val STATE_RESERVING = 2
    }
}
