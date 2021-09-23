package com.example.mimiuchi_2.view.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mimiuchi_2.R

class StatisticsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    // 独自に作成したListener
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    val statMenuName : TextView = view.findViewById(R.id.statMenuName)
    val statMenuCount : TextView = view.findViewById(R.id.statMenuCount)
    val statMenuDetail : TextView = view.findViewById(R.id.statMenuDetail)

    init {
        statMenuName.text = ""
        statMenuCount.text = ""
        statMenuDetail.text = ""
    }

}