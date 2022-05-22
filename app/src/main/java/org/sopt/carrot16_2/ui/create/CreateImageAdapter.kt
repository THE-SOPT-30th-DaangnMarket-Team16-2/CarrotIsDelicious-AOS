package org.sopt.carrot16_2.ui.create

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.databinding.ItemImageListBinding
import org.sopt.carrot16_2.model.ImageData

class CreateImageAdapter : RecyclerView.Adapter<CreateImageAdapter.CreateImageViewHolder>() {
    val imageList = mutableListOf<ImageData>()

    class CreateImageViewHolder(private val binding: ItemImageListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(image: ImageData) {
            binding.image = image
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateImageViewHolder {
        val binding = DataBindingUtil.inflate<ItemImageListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_image_list,
            parent,
            false
        )
        return CreateImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CreateImageViewHolder, position: Int) {
        holder.onBind(imageList[position])

    }

    override fun getItemCount(): Int = imageList.size
}