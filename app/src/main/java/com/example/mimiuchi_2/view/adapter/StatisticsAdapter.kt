package com.example.mimiuchi_2.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mimiuchi_2.R
import com.example.mimiuchi_2.model.StatisticsData

class StatisticsAdapter(private val context: Context,
                        private val itemClickListener: StatisticsViewHolder.ItemClickListener,
                        private val statList: List<StatisticsData>)
                        : RecyclerView.Adapter<StatisticsViewHolder>() {

    private var statRecyclerView: RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder {
        var layoutInflater = LayoutInflater.from(context)
        var sView = layoutInflater.inflate(R.layout.list_item_statistics, parent, false)
        sView.setOnClickListener { view ->
            statRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }
        return StatisticsViewHolder(sView)
    }

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        holder.statMenuName.text = statList[position].sMenuName + "の注文回数"
        holder.statMenuCount.text = statList[position].sMenuCount.toString() + "回"
        holder.statMenuDetail.text =
            "(男性：" + statList[position].sMenuDetail1.toString() + "回, " +
                    "女性：" + statList[position].sMenuDetail2.toString() + "回, " +
                    "区別しない：" + statList[position].sMenuDetail3.toString() + "回)"
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        statRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        statRecyclerView = null
    }

    override fun getItemCount(): Int {
        return statList.size
    }

}