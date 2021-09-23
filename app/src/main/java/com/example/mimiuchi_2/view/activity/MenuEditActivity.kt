package com.example.mimiuchi_2.view.activity

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mimiuchi_2.R
import com.example.mimiuchi_2.presenter.Globals
import com.example.mimiuchi_2.presenter.activity.MenuEditContract
import com.example.mimiuchi_2.presenter.activity.MenuEditPresenter
import kotlinx.android.synthetic.main.activity_menu_edit.*
import kotlinx.android.synthetic.main.activity_menu_edit.mDeadlineEditText
import kotlinx.android.synthetic.main.activity_menu_edit.mNameEditText
import kotlinx.android.synthetic.main.activity_menu_edit.mPriceEditText
import kotlinx.android.synthetic.main.activity_menu_edit.mSelectImageView
import kotlinx.android.synthetic.main.activity_menu_edit.selectButton
import java.lang.Exception

class MenuEditActivity : AppCompatActivity(),MenuEditContract.View {
    override fun showError() {

    }

    override lateinit var presenter: MenuEditContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_edit)

        supportActionBar?.title = "メニュー編集"

        // 左上の戻る
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = MenuEditPresenter(this)

        val globals = application as Globals
        val menu = globals.menu
        val mImage = menu?.menuImgURL
        val mDeadline:String? = menu?.limit.toString()
        val mName:String? = menu?.menuName
        val mCount = menu?.releaseCount
        val mPrice:String? = menu?.value.toString()

        //mSelectImageView.setImageResource(mImage)
        mNameEditText.text.append(mName)
        mDeadlineEditText.text.append(mDeadline)
        mCountEditText.text.append(mCount.toString())
        mPriceEditText.text.append(mPrice)

        selectButton.setOnClickListener {
            selectPhoto()
        }

        saveButton.setOnClickListener {
            finish()
        }

        clearButton.setOnClickListener {
            android.app.AlertDialog.Builder(this)
                .setMessage("メニューを削除しますか")
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
                    data?.data?.also {uri ->
                        val inputStream = contentResolver?.openInputStream(uri)
                        val image = BitmapFactory.decodeStream(inputStream)
                        mSelectImageView.setImageBitmap(image)
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
