package com.example.mimiuchi_2.presenter.fragment

import android.view.Menu
import com.example.mimiuchi_2.model.api.ApiService
import com.example.mimiuchi_2.model.api.Response.CouponData
import com.example.mimiuchi_2.model.api.Response.MenuData
import com.example.mimiuchi_2.presenter.BasePresenter
import com.example.mimiuchi_2.presenter.BaseView

class MenuContract {
    interface Presenter: BasePresenter {
        fun getApiService(): ApiService
        fun apirequest(apiService: ApiService)

    }
    interface View:
        BaseView<Presenter> {
        fun showError()
        fun listCreate(list:List<MenuData>)
    }
}