package com.example.mimiuchi_2.view.activity

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64OutputStream
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.mimiuchi_2.R
import android.util.Base64
import com.example.mimiuchi_2.model.api.Response.MenuData
import com.example.mimiuchi_2.presenter.Globals
import com.example.mimiuchi_2.presenter.activity.MenuRegisterContract
import com.example.mimiuchi_2.presenter.activity.MenuRegisterPresenter
import com.example.mimiuchi_2.view.fragment.DatePickerFragment
import kotlinx.android.synthetic.main.activity_menu_register.*
import java.io.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*



class MenuRegisterActivity : AppCompatActivity(), MenuRegisterContract.View, DatePickerFragment.OnDateSelectedListener{
    override fun success() {
        finish()
    }

    override fun showError() {
        Toast.makeText(applicationContext, "エラー", Toast.LENGTH_LONG).show()

    }

    override lateinit var presenter: MenuRegisterContract.Presenter

    var dateTag = ""
    var limit = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_register)

        supportActionBar?.title = "メニュー登録"
        Log.d("menu登録","メニュー登録画面に移動")

        // 左上の戻る
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        selectButton.setOnClickListener {
            selectPhoto()
        }
        presenter= MenuRegisterPresenter(this)


        registerButton.setOnClickListener {
            android.app.AlertDialog.Builder(this)
                .setMessage("メニューを登録しますか")
                .setPositiveButton("OK"){ dialog, which ->

                    /**
                     * 登録
                     */
                    if(mNameEditText.text.toString()!=""&&mCountEditText.text.toString()!=""&&mPriceEditText.text.toString()!=""){
                        presenter.start()
                        Log.d("メニュー追加","完了")

                    }else{
                        showError()
                        Log.d("メニュー追加","エラー")
                    }

                }
                .setNegativeButton("キャンセル"){dialog, which ->

                }
                .show()

        }

        calender.setOnClickListener{
            val datePicker = DatePickerFragment()
            datePicker.show(supportFragmentManager,"date_picker")
            dateTag = "date_picker"
        }

        calenderFinish.setOnClickListener {
            val datePickerFinish = DatePickerFragment()
            datePickerFinish.show(supportFragmentManager,"date_picker_finish")
            dateTag = "date_picker_finish"
        }
    }

    /**
     * カレンダー選択
     */
    override fun onSelected(year: Int, month: Int, day: Int) {
        /**
         * 一ヶ月ずれてる
         */
        val realMonth = month+1
        Log.d("date", "$year/$realMonth/$day")

        when(dateTag){
            "date_picker" ->{
                mDeadlineEditText.setText(year.toString()+"年"+realMonth.toString()+"月"+day.toString()+"日")
            }

            "date_picker_finish"->{
                mDeadlineEditText_finish.setText(year.toString()+"年"+realMonth.toString()+"月"+day.toString()+"日")
                var global = application as Globals
                var df = SimpleDateFormat("yyyy-MM-dd")
                global.limit = df.parse("$year-$realMonth-$day")

            }
        }
    }

    override fun createMenuData(): MenuData {

        var global = application as Globals

        return MenuData(
            mNameEditText.text.toString(),
            //mDeadlineEditText.text.toString()+"~"+mDeadlineEditText_finish.text.toString(),
            global.limit,
            mPriceEditText.text.toString().toInt(),
            null,
            mCountEditText.text.toString().toInt(),
            global.shopID,
            global.couponID,
            "")
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

                        val globals = application as Globals

                        val uri = bitmapToFile(image)
                        val file =  File(uri.path)
                        globals.file = file


                        mSelectImageView.setImageBitmap(image)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "エラーが発生しました", Toast.LENGTH_LONG).show()
                }
            }
        }

        Log.d("画像",data?.data.toString())

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


    // Method to save an bitmap to a file
    private fun bitmapToFile(bitmap:Bitmap): Uri {
        val globals = application as Globals
        // Get the context wrapper
        val wrapper = ContextWrapper(applicationContext)
        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images",Context.MODE_PRIVATE)
        file = File(file,"${UUID.randomUUID()}.jpg")
        globals.file =file
            try{
            // Compress the bitmap and save in jpg format
            val stream:OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
            stream.flush()
            stream.close()
        }catch (e:IOException){
            e.printStackTrace()
        }
        // Return the saved bitmap uri
        return Uri.parse(file.absolutePath)
    }
}
