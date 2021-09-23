package com.example.mimiuchi_2.presenter.activity

import com.example.mimiuchi_2.model.api.ApiService
import com.example.mimiuchi_2.model.api.Response.MenuData
import com.example.mimiuchi_2.presenter.BasePresenter
import com.example.mimiuchi_2.presenter.BaseView

class MenuRegisterContract{

    interface Presenter: BasePresenter {
        fun getApiService(): ApiService
        fun apirequest(apiService: ApiService)

    }
    interface View: BaseView<Presenter> {
        fun createMenuData(): MenuData
        fun showError()
        fun success()
    }
}