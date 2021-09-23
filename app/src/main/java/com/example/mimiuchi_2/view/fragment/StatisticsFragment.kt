package com.example.mimiuchi_2.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mimiuchi_2.R
//import sun.jvm.hotspot.utilities.IntArray
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mimiuchi_2.view.adapter.StatisticsAdapter
import com.example.mimiuchi_2.model.StatisticsData
import com.example.mimiuchi_2.view.adapter.StatisticsViewHolder
import com.example.mimiuchi_2.viewmodel.StatisticsViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.util.*
import java.util.Arrays.asList

class StatisticsFragment : Fragment(), StatisticsViewHolder.ItemClickListener {

    private lateinit var statViewModel: StatisticsViewModel

    var list: MutableList<StatisticsData> = mutableListOf()

    internal lateinit var mPieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statViewModel =
            ViewModelProviders.of(this).get(StatisticsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_statistics, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listCreate()

        list.add(
            StatisticsData(
                "メニュー1",
                5,
                3,
                2,
                0
            )
        )
        list.add(
            StatisticsData(
                "メニュー2",
                3,
                1,
                2,
                0
            )
        )
        list.add(
            StatisticsData(
                "メニュー3",
                7,
                2,
                4,
                1
            )
        )
        list.add(
            StatisticsData(
                "メニュー4",
                12,
                5,
                4,
                3
            )
        )
        list.add(
            StatisticsData(
                "メニュー5",
                2,
                2,
                0,
                0
            )
        )

        mPieChart = view.findViewById(R.id.pieChart) as PieChart
        setupPieChartView()
    }

    private fun listCreate() {
        if (this.context != null) {
            statisticsrecyclerView.adapter =
                StatisticsAdapter(
                    this.context!!,
                    this,
                    list
                )
            statisticsrecyclerView.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onItemClick(view: View, position: Int) {
        //
    }

    private fun setupPieChartView() {
        mPieChart.setUsePercentValues(true)
        mPieChart.setDescription("各メニューの注文率")

        val legend = mPieChart.legend
        legend.position = Legend.LegendPosition.BELOW_CHART_LEFT

        val values = asList(5f, 3f, 7f, 12f, 2f)
        val entries = ArrayList<Entry>()
        for (i in 0..4) {
            entries.add(Entry(values[i], i))
        }

        val dataSet = PieDataSet(entries, "")
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        dataSet.setDrawValues(true)

        val labels = asList("メニュー1", "メニュー2", "メニュー3", "メニュー4", "メニュー5")
        val pieData = PieData(labels, dataSet)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(12f)
        pieData.setValueTextColor(Color.WHITE)

        mPieChart.data = pieData
    }

}
