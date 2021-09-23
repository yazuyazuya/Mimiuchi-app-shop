package com.example.mimiuchi_2.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mimiuchi_2.view.adapter.CouponViewHolder
import com.example.mimiuchi_2.viewmodel.CouponViewModel
import com.example.mimiuchi_2.R
import com.example.mimiuchi_2.model.api.Response.CouponData
import com.example.mimiuchi_2.presenter.Globals
import com.example.mimiuchi_2.presenter.fragment.CouponContract
import com.example.mimiuchi_2.presenter.fragment.CouponPresenter
import com.example.mimiuchi_2.view.activity.CouponRegisterActivity
import com.example.mimiuchi_2.view.activity.CouponEditActivity
import com.example.mimiuchi_2.view.adapter.CouponAdapter
import kotlinx.android.synthetic.main.fragment_coupon.*
import java.util.*

class CouponFragment : Fragment(), CouponViewHolder.ItemClickListener,CouponContract.View {
    override fun showError() {

    }

    override lateinit var presenter: CouponContract.Presenter


    private lateinit var couponViewModel: CouponViewModel

    var list : List<CouponData> = mutableListOf()

    private var cName: String? = null
    private var cDeadline: String? = null
    private var cPrice: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        couponViewModel =
            ViewModelProviders.of(this).get(CouponViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_coupon, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = CouponPresenter(this)
        presenter.start()




        couponFab.setOnClickListener {
            val intent = Intent(this.context, CouponRegisterActivity::class.java)
            startActivity(intent)
        }

    }

    override fun listCreate(list: List<CouponData>) {
        if (this.context != null) {
            couponRecyclerView.adapter =
                CouponAdapter(this.context!!, this, list)
            couponRecyclerView.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        this.list = list
    }


    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this.context, CouponEditActivity::class.java)
        val globals = activity?.application as Globals
        globals.coupon = list[position]
        globals.couponID = list[position].couponID

        startActivity(intent)
    }

}