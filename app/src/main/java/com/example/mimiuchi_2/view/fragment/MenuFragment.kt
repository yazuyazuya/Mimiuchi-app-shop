package com.example.mimiuchi_2.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mimiuchi_2.R
import com.example.mimiuchi_2.model.api.Response.MenuData
import com.example.mimiuchi_2.presenter.Globals
import com.example.mimiuchi_2.presenter.fragment.MenuContract
import com.example.mimiuchi_2.presenter.fragment.MenuPresenter
import com.example.mimiuchi_2.viewmodel.MenuViewModel
import com.example.mimiuchi_2.view.adapter.MenuAdapter
import com.example.mimiuchi_2.view.activity.MenuEditActivity
import com.example.mimiuchi_2.view.activity.MenuRegisterActivity
import com.example.mimiuchi_2.view.adapter.MenuViewHolder
import kotlinx.android.synthetic.main.fragment_menu.*
import java.util.*

class MenuFragment : Fragment(), MenuViewHolder.ItemClickListener,MenuContract.View {


    override fun showError() {

    }

    override lateinit var presenter: MenuContract.Presenter


    private lateinit var menuViewModel: MenuViewModel

    var list : List<MenuData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        menuViewModel =
            ViewModelProviders.of(this).get(MenuViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_menu, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MenuPresenter(this)
        presenter.start()


        menuFab.setOnClickListener {
            val intent = Intent(this.context, MenuRegisterActivity::class.java)
            startActivity(intent)
        }

    }

    override fun listCreate(list:List<MenuData>) {
        if (this.context != null) {
            menuRecyclerView.adapter =
                MenuAdapter(this.context!!, this, list)
            menuRecyclerView.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        this.list = list

    }

    override fun onItemClick(view: View, position: Int) {
        // 既存のメニューを編集or削除する画面に遷移するようにする
        val intent = Intent(this.context, MenuEditActivity::class.java)


        val global = activity?.application as Globals
        global.menu = list[position]
        global.menuID = list[position].menuID

        startActivity(intent)
    }

}