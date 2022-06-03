package org.sopt.carrot16_2.ui.create.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateViewModel : ViewModel() {
    var title = MutableLiveData("")
    var category = MutableLiveData("")
    var money = MutableLiveData("")
    var content = MutableLiveData("")
    private val _complete : MutableLiveData<Boolean> = MutableLiveData(false)
    val complete : MutableLiveData<Boolean> get() = _complete

    private val _imageCount = MutableLiveData<Int>()
    val imageCount : LiveData<Int> = _imageCount

    init {
        _imageCount.value = 0
    }

    fun updateImageCount(counts : Int){
        _imageCount.value = counts
    }




}
