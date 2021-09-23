package com.example.mimiuchi_2.model.api

import android.graphics.Bitmap
import android.media.Image
import com.example.mimiuchi_2.model.api.Response.CouponData
import com.example.mimiuchi_2.model.api.Response.MenuData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File
import java.net.URI
import java.sql.Time
import java.util.*

interface ApiService {


    @GET("api/myCouponAll")
    fun menuAll(@Query("shopID")shopID: Int): Call<String>

    @GET("api/myMenuAll")
    fun couponAll(@Query("shopID")shopID: Int): Call<String>

    @GET("api/shopDetail")
    fun shopDetail( @Query("shopID") shopId: Int): Call<String>

    @GET("api/menuDelete")
    fun menuDelete( @Query("shopID") shopId: Int, @Query("menuID") menuId: Int): Call<String>

    @GET("api/couponDelete")
    fun couponDelete( @Query("shopID") shopId: Int, @Query("couponID") couponId: Int): Call<String>

    @Multipart
    @POST("api/menuAdd")
    fun menuAdd(@Part("menuName")menuName:String,
                @Part("limit")limit:Date?,
                @Part("value")value:Int,
                @Part("img")img: RequestBody,
                @Part("releaseCount")releaseCount:Int,
                @Part("shopID")shopID:Int
    ): Call<String>

    @Multipart
    @POST("api/couponAdd")
    fun couponAdd(@Part("couponName")couponName:String,
                  @Part("limit")limit:Date?,
                  @Part("value")value:Int,
                  @Part("img")img:File,
                  @Part("releaseCount")releaseCount:Int,
                  @Part("shopID")shopID:Int,
                  @Part("permission")permission:String
    ):Call<String>
}