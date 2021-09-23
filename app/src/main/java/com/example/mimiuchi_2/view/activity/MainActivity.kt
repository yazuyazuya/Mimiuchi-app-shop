package com.example.mimiuchi_2.view.activity

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mimiuchi_2.R
import com.example.mimiuchi_2.model.api.Response.MenuData
import java.util.*

class MainActivity : AppCompatActivity(){





    var list : MutableList<MenuData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        supportActionBar?.title = "みみうち(店舗用)"
        Log.d("日付", Date().toString())

    }



}
