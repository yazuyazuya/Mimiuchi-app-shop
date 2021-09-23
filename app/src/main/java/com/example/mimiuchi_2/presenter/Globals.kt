package com.example.mimiuchi_2.presenter

import android.app.Application
import android.media.Image
import android.net.Uri
import com.example.mimiuchi_2.model.api.Response.CouponData
import com.example.mimiuchi_2.model.api.Response.MenuData
import retrofit2.http.Url
import java.io.File
import java.net.URI
import java.util.*

class Globals : Application() {

    companion object {
        lateinit var instance: Application private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    //グローバルに使用する変数

     var menu:MenuData? = MenuData("",null,0,"",0,0,0,"")
     var coupon: CouponData? =  CouponData("",null,0,"",0,"",0,0,"")

    var file = File("")

    var shopID = 1
    var couponID = 0
    var menuID = 0

    var limit: Date? = null//Date()


    var fragment = ""

}