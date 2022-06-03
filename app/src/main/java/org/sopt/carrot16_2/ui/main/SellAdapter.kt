package org.sopt.carrot16_2.ui.main

import android.content.Context
import android.content.Intent
import android.view.Gravity.apply
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.data.remote.entity.response.HomeResponse
import org.sopt.carrot16_2.databinding.ItemSellListBinding
import org.sopt.carrot16_2.ui.read.ReadActivity

class SellAdapter(private val itemClick: (HomeResponse.Data) -> (Unit)) :
    RecyclerView.Adapter<SellAdapter.SellViewHolder>() {
    val dataList = mutableListOf<HomeResponse.Data>()

    class SellViewHolder(
        private val binding: ItemSellListBinding,
        private val itemClick: (HomeResponse.Data) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(sell: HomeResponse.Data) {
            binding.homeResponseData = sell
            binding.root.setOnClickListener {
                itemClick(sell)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellViewHolder {
        val binding = DataBindingUtil.inflate<ItemSellListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_sell_list,
            parent,
            false
        )
        return SellViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: SellViewHolder, position: Int) {
        holder.onBind(dataList[position])

    }

    override fun getItemCount(): Int = dataList.size
}