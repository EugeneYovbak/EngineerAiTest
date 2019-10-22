package com.example.engineeraitest.presentation.screen.main.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.example.engineeraitest.R
import com.example.engineeraitest.app.di.GlideApp
import com.example.engineeraitest.domain.model.User
import com.example.engineeraitest.presentation.tools.layoutInflater
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    companion object {
        private const val ITEMS_ROW_COUNT = 2
    }

    private val mUserList = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent.context.layoutInflater.inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(mUserList[position])
    }

    override fun getItemCount(): Int {
        return mUserList.size
    }

    fun addUserList(userList: List<User>) {
        mUserList.addAll(userList)
        notifyItemRangeChanged(mUserList.size - userList.size, userList.size)
    }

    inner class UserViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(user: User) {

            GlideApp.with(containerView.context)
                .load(user.image)
                .placeholder(R.color.color_placeholder)
                .error(R.color.color_placeholder)
                .apply(RequestOptions.circleCropTransform())
                .into(iv_userImage)

            tv_userName.text = user.name

            if (user.items.size % 2 == 0) {
                iv_oddItem.visibility = View.GONE
                setItems(containerView.context, user.items)
            } else {
                iv_oddItem.visibility = View.VISIBLE

                GlideApp.with(containerView.context)
                    .load(user.items[0])
                    .placeholder(R.color.color_placeholder)
                    .error(R.color.color_placeholder)
                    .into(iv_oddItem)
                setItems(containerView.context, user.items.subList(1, user.items.size))
            }
        }

        private fun setItems(context: Context, items: List<String>) {
            rv_items.layoutManager = GridLayoutManager(context, ITEMS_ROW_COUNT)
            val itemAdapter = ItemAdapter()
            rv_items.adapter = itemAdapter
            itemAdapter.setItemList(items)
        }
    }
}