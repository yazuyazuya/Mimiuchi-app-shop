package com.example.mimiuchi_2.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mimiuchi_2.R
import com.example.mimiuchi_2.model.api.Response.MenuData
import com.squareup.picasso.Picasso

class MenuAdapter(private val context : Context,
                  private val itemClickListener: MenuViewHolder.ItemClickListener,
                  private val menuList : List<MenuData>)
                    : androidx.recyclerview.widget.RecyclerView.Adapter<MenuViewHolder>() {

    private var menuRecyclerView : RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
//        val menuView : View = LayoutInflater.from(parent.context)
//            .inflate(R.layout.list_item_menu, parent, false)
//        return MenuViewHolder(menuView)
        var layoutInflater = LayoutInflater.from(context)
        var mView = layoutInflater.inflate(R.layout.list_item_menu, parent, false)
        mView.setOnClickListener {view ->
            menuRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }
        return MenuViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {

        try {

            Picasso.get()
                //画像URL
                .load("http://35.189.144.179/"+menuList[position].menuImgURL)
                .resize(1000, 500) //表示サイズ指定
                .centerCrop() //resizeで指定した範囲になるよう中央から切り出し
                .into(holder.menuImage) //imageViewに流し込み

            Log.d("tag",menuList[position].menuImgURL.toString())
            Log.d("tag","流し込み")

        } catch (e: IllegalArgumentException) {
            Picasso.get()
                //画像URL
                .load(R.drawable.m_e_others_501)
                .resize(1000, 500) //表示サイズ指定
                .centerCrop() //resizeで指定した範囲になるよう中央から切り出し
                .into(holder.menuImage) //imageViewに流し込み
        }
        holder.menuDeadline.text = menuList[position].limit.toString()
        holder.menuName.text = menuList[position].menuName
        holder.menuCount.text = menuList[position].releaseCount.toString()
        holder.menuPrice.text = menuList[position].value.toString()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        menuRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        menuRecyclerView = null
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

}