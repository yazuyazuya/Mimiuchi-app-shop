package com.example.mimiuchi_2.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mimiuchi_2.R

class MenuViewHolder(view: View): RecyclerView.ViewHolder(view) {

    // 独自に作成したListener
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    val menuImage : ImageView = view.findViewById(R.id.menuImage)
    val menuDeadline : TextView = view.findViewById(R.id.menuDeadline)
    val menuName : TextView = view.findViewById(R.id.menuName)
    val menuCount : TextView = view.findViewById(R.id.menuCount)
    val menuPrice : TextView = view.findViewById(R.id.menuPrice)

    init {
        // layoutの初期設定するときはココ
        menuImage.setImageResource(R.mipmap.ic_launcher)
        menuDeadline.text = ""
        menuName.text = ""
        menuCount.text = ""
        menuPrice.text = ""
    }

}