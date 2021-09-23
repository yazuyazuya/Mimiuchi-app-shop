package com.example.mimiuchi_2.view.activity

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mimiuchi_2.R
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.selectButton
import java.lang.Exception

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.title = "新規店舗登録"
        // 左上の戻る
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        selectButton.setOnClickListener {
            selectPhoto()
        }

        var beaconid = beaconidEditText.text
        var password = passwordEditText.text
        var shopName = shopNameEditText.text
        var shopCategory = shopCategoryEditText.text
        var shopImage = shopSelectImageView
        var shopOpenHours = shopOpenEditText.text
        var shopPhoneNumber = shopPhoneEditText.text
        var shopStreetAdress = shopAdressEditText.text

        signupButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("BEACON_ID", beaconid.toString())
            intent.putExtra("PASSWORD", password.toString())
            intent.putExtra("SHOP_NAME", shopName.toString())
            intent.putExtra("Shop_Cate", shopCategory.toString())
//            intent.putExtra("Shop_Image", shopImage.toString())
            intent.putExtra("Shop_Hours", shopOpenHours.toString())
            intent.putExtra("Shop_Phone", shopPhoneNumber.toString())
            intent.putExtra("Shop_Street", shopStreetAdress.toString())
            Log.d("BeaconID", beaconid.toString())
            Log.d("Password", password.toString())
            Log.d("ShopName", shopName.toString())
            Log.d("ShopCategory", shopCategory.toString())
            Log.d("ShopImage", shopImage.toString())
            Log.d("ShopOpenHours", shopOpenHours.toString())
            Log.d("ShopPhoneNumber", shopPhoneNumber.toString())
            Log.d("ShopStreetAdress", shopStreetAdress.toString())
            startActivity(intent)
        }

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
                        shopSelectImageView.setImageBitmap(image)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "エラーが発生しました", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //アクションバーの[戻る]処理
    override fun onSupportNavigateUp(): Boolean {
        finish()
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
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
