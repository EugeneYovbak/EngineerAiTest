package com.example.engineeraitest.presentation.tools

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addPagingScrollListener(onScroll: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0) {
                val visibleItemCount = (layoutManager as LinearLayoutManager).childCount
                val totalItemCount = (layoutManager as LinearLayoutManager).itemCount
                val pastVisibleItems = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    onScroll.invoke()
                }
            }
        }
    })
}