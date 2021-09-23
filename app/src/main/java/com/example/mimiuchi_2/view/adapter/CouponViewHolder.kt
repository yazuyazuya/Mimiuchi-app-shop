package com.example.mimiuchi_2.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mimiuchi_2.R

class CouponViewHolder(view: View): RecyclerView.ViewHolder(view) {

    // 独自に作成したListener
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    val couponImage : ImageView = view.findViewById(R.id.couponImage)
    val couponDeadline : TextView = view.findViewById(R.id.couponDeadline)
    val couponName : TextView = view.findViewById(R.id.couponName)
    val couponCount : TextView = view.findViewById(R.id.couponCount)
    val couponPermission : TextView = view.findViewById(R.id.couponPermission)
    val couponPrice : TextView = view.findViewById(R.id.couponPrice)

    init {
        // layoutの初期設定するときはココ
        couponImage.setImageResource(R.mipmap.ic_launcher)
        couponDeadline.text = ""
        couponName.text = ""
        couponCount.text = ""
        couponPermission.text = ""
        couponPrice.text = ""
    }

}