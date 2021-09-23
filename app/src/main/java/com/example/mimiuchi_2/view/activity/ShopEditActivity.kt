package com.example.mimiuchi_2.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mimiuchi_2.R
import kotlinx.android.synthetic.main.activity_shop_edit.*
import java.lang.Exception

class ShopEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_edit)

        supportActionBar?.title = "店舗詳細編集"

        // 左上の戻る
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        selectButton.setOnClickListener {
            selectPhoto()
        }

//        beaconidEditText.text.append(intent.getStringExtra("BEACON_ID"))
//        passwordEditText.text.append(intent.getStringExtra("PASSWORD"))
//        shopNameEditText.text.append(intent.getStringExtra("SHOP_NAME"))
//        shopCategoryEditText.text.append(intent.getStringExtra("Shop_Cate"))
//        shopOpenEditText.text.append(intent.getStringExtra("Shop_Hours"))
//        shopPhoneEditText.text.append(intent.getStringExtra("Shop_Phone"))
//        shopAdressEditText.text.append(intent.getStringExtra("Shop_Street"))

        var shopid = beaconidEditText.text
        var password = passwordEditText.text
        var shopName = shopNameEditText.text
        var shopCategory = shopCategoryEditText.text
//        var shopImage = shopSelectImageView
        var shopOpenHours = shopOpenEditText.text
        var shopPhoneNumber = shopPhoneEditText.text
        var shopStreetAdress = shopAdressEditText.text

//        shopidEditText.text.append(shopid)

        saveButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("BEACON_ID", shopid.toString())
            intent.putExtra("PASSWORD", password.toString())
            intent.putExtra("SHOP_NAME", shopName.toString())
            intent.putExtra("Shop_Cate", shopCategory.toString())
//            intent.putExtra("Shop_Image", shopImage.toString())
            intent.putExtra("Shop_Hours", shopOpenHours.toString())
            intent.putExtra("Shop_Phone", shopPhoneNumber.toString())
            intent.putExtra("Shop_Street", shopStreetAdress.toString())
            startActivity(intent)
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
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        when (requestCode) {
            READ_REQUEST_CODE -> {
                try {
                    data?.data?.also { uri ->
                        val inputStream = contentResolver?.openInputStream(uri)
                        val image = BitmapFactory.decodeStream(inputStream)
                        shopSelectImageView.setImageBitmap(image)
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
