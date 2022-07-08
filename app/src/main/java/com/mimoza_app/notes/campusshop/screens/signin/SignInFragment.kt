package com.mimoza_app.notes.campusshop.screens.signin

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.mimoza_app.notes.campusshop.databinding.FragmentSignInBinding
import com.mimoza_app.notes.campusshop.util.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream


class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: SignInFragmentViewModel
    private lateinit var preferenceManager:PreferenceManager
    private lateinit var encodedImage:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }
    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        preferenceManager = PreferenceManager()
        preferenceManager.PreferenceManager(APP_ACTIVITY)
        mViewModel = ViewModelProvider(this)[SignInFragmentViewModel::class.java]
        mBinding.createAccount.setOnClickListener{
            signUp()
        }
        mBinding.avatarImage.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pickImage.launch(intent)
        }


    }

    private fun signUp() {
        val inputEmail = mBinding.inputEmail.text.toString()
        val inputPassword = mBinding.inputPassword.text.toString()
        val inputRepPass = mBinding.repPassInput.text.toString()
        val inputName = mBinding.inputName.text.toString()
        val inputSurname = mBinding.inputSurname.text.toString()
        val image = encodedImage
        if(isValidSignUp(inputEmail,inputPassword,inputRepPass,inputName,inputSurname)){
            mViewModel.signUp(inputEmail,inputPassword,preferenceManager,inputName,inputSurname,image)
        }
    }

    private fun isValidSignUp(email: String, pass: String, repPass: String,name:String,surname:String): Boolean {
        if (email.isNotEmpty() && pass.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty()) {
            EMAIL = email
            PASSWORD = pass
            if (PASSWORD == repPass) {
                return true
            } else {
                showToast("Пороли не совпадают")
                return false
            }
        } else {
            showToast("Введите логин и пороль")
            return false
        }
    }

    private fun encodedImage(bitmap:Bitmap): String? {
        val previewWidth = 150
        val previewHeight = bitmap.height * previewWidth / bitmap.width
        val previewBitmap = Bitmap.createScaledBitmap(bitmap,previewWidth,previewHeight,false)
        val byteArrayOutputStream = ByteArrayOutputStream()
        previewBitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream)
        val bytes =byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytes,Base64.DEFAULT)
    }

    val pickImage:ActivityResultLauncher <Intent>  = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == RESULT_OK){
            if(it.data != null){
                val imageUri: Uri? = it.data!!.data
                try {
                    val inputStream: InputStream? = imageUri?.let { it1 ->
                        APP_ACTIVITY.contentResolver.openInputStream(
                            it1
                        )
                    }
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    mBinding.avatarImage.setImageBitmap(bitmap)
                    mBinding.addImage.visibility = View.GONE
                    encodedImage = encodedImage(bitmap).toString()
                }catch(e: FileNotFoundException){
                        e.printStackTrace()
                }
            }
        }
    }
}