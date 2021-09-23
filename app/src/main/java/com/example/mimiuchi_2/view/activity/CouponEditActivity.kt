package com.example.mimiuchi_2.view.activity

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mimiuchi_2.R
import com.example.mimiuchi_2.presenter.Globals
import com.example.mimiuchi_2.presenter.activity.CouponEditContract
import com.example.mimiuchi_2.presenter.activity.CouponEditPresenter
import kotlinx.android.synthetic.main.activity_coupon_edit.*
import java.lang.Exception

class CouponEditActivity : AppCompatActivity(),CouponEditContract.View {
    override fun showError() {

    }

    override lateinit var presenter: CouponEditContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_edit)

        supportActionBar?.title = "クーポン編集"

        // 左上の戻る
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter =  CouponEditPresenter(this)

        val globals = application as Globals
        val coupon = globals.coupon
        val cImage = coupon?.couponImgURL
        val cDeadline = coupon?.limit.toString()
        val cName = coupon?.couponName
        val cCount = coupon?.releaseCount
        val cPermission = coupon?.permission
        val cPrice = coupon?.value.toString()

      //  cSelectImageView.setImageResource(cImage)
        cNameEditText.text.append(cName)
        cDeadlineEditText.text.append(cDeadline)
        cCountEditText.text.append(cCount.toString())
        cPermissionEditText.text.append(cPermission)
        cPriceEditText.text.append(cPrice)

        selectButton.setOnClickListener {
            selectPhoto()
        }

        saveButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            Log.d("CouponName", cNameEditText.text.toString())
            Log.d("CouponDeadline", cDeadlineEditText.text.toString())
            Log.d("CouponCount", cCountEditText.text.toString())
            Log.d("CouponPermission", cPermissionEditText.text.toString())
            Log.d("CouponPrice", cPriceEditText.text.toString())
            startActivity(intent)
        }

        clearButton.setOnClickListener {
            android.app.AlertDialog.Builder(this)
                .setMessage("クーポンを削除しますか")
                .setPositiveButton("OK"){ dialog, which ->
                    presenter.deleteStart()
                    finish()
                }
                .setNegativeButton("キャンセル"){dialog, which ->

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
