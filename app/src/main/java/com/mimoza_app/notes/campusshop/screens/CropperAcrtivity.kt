package com.mimoza_app.notes.campusshop.screens

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mimoza_app.notes.campusshop.R
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.*

class CropperAcrtivity : AppCompatActivity() {

    private lateinit var result:String
    private lateinit var fileUri: Uri



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cropper_acrtivity)

        readIntent()

        val dest_uri = StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString()
        val options = UCrop.Options()

        UCrop.of(fileUri,Uri.fromFile(File(cacheDir,dest_uri)))
            .withOptions(options)
            .withAspectRatio(0F, 0F)
            .useSourceImageAspectRatio()
            .withMaxResultSize(2000,2000)
            .start(this)
    }

    private fun readIntent() {
        val intent = getIntent()
        if(intent.extras != null){
            result = intent.getStringExtra("DATA").toString()
            fileUri = Uri.parse(result)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP ){
            var resultUri = UCrop.getOutput(data!!)
            var returnIntent = Intent()
            returnIntent.putExtra("RESULT","${resultUri}")
            setResult(-1,returnIntent)
            finish()
        }else if(resultCode == UCrop.RESULT_ERROR){
            var cropError = UCrop.getError(data!!)
        }
    }
}