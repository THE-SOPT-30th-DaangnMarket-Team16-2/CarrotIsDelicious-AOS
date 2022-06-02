package org.sopt.carrot16_2.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.sopt.carrot16_2.R

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageview: ImageView, url: String?) {
        Glide.with(imageview.context)
            .load(url)
            .into(imageview)
    }

    @JvmStatic
    @BindingAdapter("setCircleImage")
    fun setCircleImage(imageview: ImageView, url: String?) {
        Glide.with(imageview.context)
            .load(url)
            .circleCrop()
            .into(imageview)
    }

    @JvmStatic
    @BindingAdapter("setReadState")
    fun setReadState(textView: TextView, state: Int?) {
        textView.setText(
            when (state) {
                0 -> R.string.state_selling
                1 -> R.string.state_completed
                2 -> R.string.state_reserving
                else -> throw IllegalStateException("바인딩 어댑터 setReadState 오류")
            }
        )
    }
}
