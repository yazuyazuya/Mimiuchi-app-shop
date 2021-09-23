package com.example.mimiuchi_2.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mimiuchi_2.R
import com.example.mimiuchi_2.view.activity.ShopEditActivity
import com.example.mimiuchi_2.model.api.Response.ShopData
import com.example.mimiuchi_2.presenter.fragment.ShopContract
import com.example.mimiuchi_2.presenter.fragment.ShopPresenter
import com.example.mimiuchi_2.viewmodel.ShopViewModel
import com.squareup.picasso.Picasso
//import com.example.mimiuchi_2.presenter.ShopViewModel
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment : Fragment() ,ShopContract.View{


    override lateinit var presenter: ShopContract.Presenter


    private lateinit var shopViewModel: ShopViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shopViewModel =
            ViewModelProviders.of(this).get(ShopViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_shop, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        presenter = ShopPresenter(this)
        presenter.start()

        editButton.setOnClickListener {
            val intent = Intent(this.context, ShopEditActivity::class.java)
            startActivity(intent)
        }

    }

    override fun showError() {

    }

    override fun showData(data: ShopData) {
        beaconidTextView2.text = data.beaconID
        //passwordTextView2.text = password
        shopNameTextView2.text = data.shopName
        shopCategoryTextView2.text = data.category[0]
//        shopSelectImageView.setImageResource(shopImage.toInt())
        try {

            Picasso.get()
                //画像URL
                .load("http://35.189.144.179/"+data.pictureID)
                .resize(1000, 500) //表示サイズ指定
                .centerCrop() //resizeで指定した範囲になるよう中央から切り出し
                .into(shopSelectImageView) //imageViewに流し込み

        } catch (e: IllegalArgumentException) {
            Picasso.get()
                //画像URL
                .load(R.drawable.m_e_others_501)
                .resize(1000, 500) //表示サイズ指定
                .centerCrop() //resizeで指定した範囲になるよう中央から切り出し
                .into(shopSelectImageView) //imageViewに流し込み
        }
        shopOpenTextView2.text = data.openHours
        shopPhoneTextView2.text = data.phoneNumber
        shopAdressTextView2.text = data.streetAddress
    }

}