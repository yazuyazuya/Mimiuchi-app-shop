package com.example.mimiuchi_2.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mimiuchi_2.R
import com.example.mimiuchi_2.model.api.Response.CouponData
import com.squareup.picasso.Picasso

class CouponAdapter(private val context : Context,
                    private val itemClickListener: CouponViewHolder.ItemClickListener,
                    private val couponList : List<CouponData>)
    : androidx.recyclerview.widget.RecyclerView.Adapter<CouponViewHolder>() {

    private var couponRecyclerView : RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder {
        var layoutInflater = LayoutInflater.from(context)
        var cView = layoutInflater.inflate(R.layout.list_item_coupon, parent, false)
        cView.setOnClickListener { view ->
            couponRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }
        return CouponViewHolder(cView)
    }

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
       // holder.couponImage.setImageResource(R.mipmap.ic_launcher)
        try {

            Picasso.get()
                //画像URL
                .load("http://35.189.144.179/"+couponList[position].couponImgURL)
                .resize(1000, 500) //表示サイズ指定
                .centerCrop() //resizeで指定した範囲になるよう中央から切り出し
                .into(holder.couponImage) //imageViewに流し込み

        } catch (e: IllegalArgumentException) {
            Picasso.get()
                //画像URL
                .load(R.drawable.m_e_others_501)
                .resize(1000, 500) //表示サイズ指定
                .centerCrop() //resizeで指定した範囲になるよう中央から切り出し
                .into(holder.couponImage) //imageViewに流し込み
        }
        holder.couponDeadline.text = couponList[position].limit.toString()
        holder.couponName.text = couponList[position].couponName
        holder.couponCount.text = couponList[position].releaseCount.toString()
        holder.couponPermission.text = couponList[position].permission
        holder.couponPrice.text = couponList[position].value.toString()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        couponRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        couponRecyclerView = null
    }

    override fun getItemCount(): Int {
        return couponList.size
    }

}