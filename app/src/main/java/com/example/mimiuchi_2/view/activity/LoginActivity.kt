package com.example.mimiuchi_2.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mimiuchi_2.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = "ログイン"

        // 左上の戻る
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var shopName = shopNameEditText.text
        var password = passwordEditText.text

        loginButton.setOnClickListener {
            val loginIntent = Intent(this, MainActivity::class.java)

            Log.d("ShopName", shopName.toString())
            Log.d("Password", password.toString())

            startActivity(loginIntent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
//        val intent = Intent(this, StartActivity::class.java)
//        startActivity(intent)
        return super.onSupportNavigateUp()
    }

}
