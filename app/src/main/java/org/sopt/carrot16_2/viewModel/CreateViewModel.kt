package org.sopt.carrot16_2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateViewModel : ViewModel() {
    var title = MutableLiveData("")
    var category = MutableLiveData("")
    var money = MutableLiveData("")
    var post = MutableLiveData("")
    private val _complete : MutableLiveData<Boolean> = MutableLiveData(false)

    val complete : MutableLiveData<Boolean> get() = _complete

  /* fun imageComplete() {
        when {
            !title.value.isNullOrBlank() && !category.value.isNullOrBlank() && !money.value.isNullOrBlank() && !post.value.isNullOrBlank() -> {
                _complete.value = true
            }
            else -> {
                _complete.value = false
            }
        }
    }*/


}
