package org.sopt.carrot16_2.ui.create

import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.databinding.ItemImageListBinding
import org.sopt.carrot16_2.model.ImageData
import org.sopt.carrot16_2.ui.create.viewmodel.CreateViewModel

class CreateImageAdapter(private val itemCounts: (Int) -> (Unit)) :
    RecyclerView.Adapter<CreateImageAdapter.CreateImageViewHolder>() {
    val imageList = mutableListOf<ImageData>()

    inner class CreateImageViewHolder(
        private val binding: ItemImageListBinding,
        private val itemCounts: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(image: ImageData) {
            binding.image = image
            binding.btnX.setOnClickListener {
                imageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                notifyItemRangeChanged(adapterPosition, imageList.size)
                itemCounts(imageList.size)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateImageViewHolder {
        val binding = DataBindingUtil.inflate<ItemImageListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_image_list,
            parent,
            false
        )
        return CreateImageViewHolder(binding, itemCounts)
    }

    override fun onBindViewHolder(holder: CreateImageViewHolder, position: Int) {
        holder.onBind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
}
