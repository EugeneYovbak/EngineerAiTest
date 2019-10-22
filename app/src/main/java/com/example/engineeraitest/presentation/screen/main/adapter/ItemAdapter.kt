package com.example.engineeraitest.presentation.screen.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.engineeraitest.R
import com.example.engineeraitest.app.di.GlideApp
import com.example.engineeraitest.presentation.tools.layoutInflater
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user_item.*

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val mItemList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent.context.layoutInflater.inflate(R.layout.item_user_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(mItemList[position])
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }

    fun setItemList(itemList: List<String>) {
        mItemList.addAll(itemList)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(item: String) {

            GlideApp.with(containerView.context)
                .load(item)
                .placeholder(R.color.color_placeholder)
                .error(R.color.color_placeholder)
                .into(iv_item)
        }
    }
}