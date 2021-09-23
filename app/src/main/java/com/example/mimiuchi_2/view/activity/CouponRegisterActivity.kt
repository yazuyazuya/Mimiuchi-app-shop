package com.example.mimiuchi_2.view.activity

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mimiuchi_2.R
import kotlinx.android.synthetic.main.activity_coupon_register.*
import java.lang.Exception

class CouponRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_register)

        supportActionBar?.title = "クーポン登録"

        // 左上の戻る
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        selectButton.setOnClickListener {
            selectPhoto()
        }

        registerButton.setOnClickListener {
            android.app.AlertDialog.Builder(this)
                .setMessage("クーポンを登録しますか")
                .setPositiveButton("OK") { dialog, which ->


                    finish()
                }
                .setNegativeButton("キャンセル") { dialog, which ->

                }
                .show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {
            READ_REQUEST_CODE -> {
                try {
                    data?.data?.also { uri ->
                        val inputStream = contentResolver?.openInputStream(uri)
                        val image = BitmapFactory.decodeStream(inputStream)
                        cSelectImageView.setImageBitmap(image)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "エラーが発生しました", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent,
            READ_REQUEST_CODE
        )
    }

    companion object {
        private const val READ_REQUEST_CODE: Int = 42
    }

}
