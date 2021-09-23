package com.example.mimiuchi_2.model.api.Response

import retrofit2.Response
import java.net.Inet4Address
import java.util.*


data class MenuData(
    val menuName: String,
    val limit: Date?,
    val value: Int,
    val menuImgURL: String?,
    val releaseCount: Int,//来店回数条件
    val shopID: Int,
    val menuID:Int,
    val response: String
)

data class CouponData(
    val couponName: String,
    val limit: Date?,
    val value: Int,
    val couponImgURL: String?,
    val releaseCount: Int,//来店回数条件
    val permission:String?,
    val shopID: Int,
    val couponID:Int,
    val response: String
)
data class Shop(
    val data:ShopData
)
data class ShopData(
    val shopID: Int?,
    val shopName:String?,
    val pictureID: String?,
    val category: List<String>,
    val beaconID:String?,
    val openHours:String?,
    val phoneNumber:String?,
    val streetAddress: String?,
    val response: String?
)

