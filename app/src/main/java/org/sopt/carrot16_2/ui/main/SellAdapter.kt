package org.sopt.carrot16_2.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.carrot16_2.R
import org.sopt.carrot16_2.databinding.ItemSellListBinding
import org.sopt.carrot16_2.model.SellData

class SellAdapter : RecyclerView.Adapter<SellAdapter.SellViewHolder>() {
    val dataList = mutableListOf<SellData>()
    class SellViewHolder(private val binding : ItemSellListBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun onBind(sell : SellData){
            binding.sellData = sell
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellViewHolder {
        val binding = DataBindingUtil.inflate<ItemSellListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_sell_list,
            parent,
            false
        )
        return SellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SellViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size
}