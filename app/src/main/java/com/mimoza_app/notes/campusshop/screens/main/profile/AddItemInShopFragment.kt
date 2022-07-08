package com.mimoza_app.notes.campusshop.screens.main.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentAddItemInShopBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentChatsBinding
import com.mimoza_app.notes.campusshop.util.APP_ACTIVITY
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class AddItemInShopFragment : Fragment() {

    private var _binding: FragmentAddItemInShopBinding? = null
    private val mBinding get() =_binding!!
    private lateinit var path: String
    private lateinit var uri: Uri
    private lateinit var captureImage: ImageView
    private lateinit var encodedImage:String




    override fun onResume() {
        super.onResume()
        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.categories_dropdown_item, categories)
        mBinding.autoCompleteTextView.setAdapter(arrayAdapter)
        captureImage = mBinding.imgAddImage
        mBinding.imgAddImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pickImage.launch(intent)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemInShopBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    private fun encodedImage(bitmap: Bitmap): String? {
        val previewWidth = 150
        val previewHeight = bitmap.height * previewWidth / bitmap.width
        val previewBitmap = Bitmap.createScaledBitmap(bitmap,previewWidth,previewHeight,false)
        val byteArrayOutputStream = ByteArrayOutputStream()
        previewBitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream)
        val bytes =byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    val pickImage: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == Activity.RESULT_OK){
            if(it.data != null){
                val imageUri: Uri? = it.data!!.data
                try {
                    val inputStream: InputStream? = imageUri?.let { it1 ->
                        APP_ACTIVITY.contentResolver.openInputStream(
                            it1
                        )
                    }
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    mBinding.imgAddImage.setImageBitmap(bitmap)
                    encodedImage = encodedImage(bitmap).toString()
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }
}