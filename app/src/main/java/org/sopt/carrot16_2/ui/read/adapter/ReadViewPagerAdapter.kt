package org.sopt.carrot16_2.ui.read.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import org.sopt.carrot16_2.databinding.ItemReadVpItemBinding

class ReadViewPagerAdapter :
    ListAdapter<String, ReadViewPagerAdapter.ViewPagerViewHolder>(readDiffUtil) {

    inner class ViewPagerViewHolder(val binding: ItemReadVpItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(url: String) {
            Glide.with(binding.root.context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivReadVpImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding =
            ItemReadVpItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun updateHabitList(images: List<String>) {
        submitList(images)
    }

    companion object {
        private val readDiffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem.equals(newItem)
        }
    }
}
