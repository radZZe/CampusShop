package com.mimoza_app.notes.campusshop.screens.main.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentEditProfileBinding
import com.mimoza_app.notes.campusshop.util.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val mBinding get() = _binding!!
    val database = FirebaseFirestore.getInstance()

    private lateinit var encodedImage: String
    private lateinit var mViewModel: EditProfileFragmentViewModel
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
        loadUserDetails()
    }

    private fun encodedImage(bitmap: Bitmap): String? {
        val previewWidth = 150
        val previewHeight = bitmap.height * previewWidth / bitmap.width
        val previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false)
        val byteArrayOutputStream = ByteArrayOutputStream()
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        val bytes = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    val pickImage: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            if (it.data != null) {
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

    private fun initialization() {
        preferenceManager = PreferenceManager()
        preferenceManager.PreferenceManager(APP_ACTIVITY)

        mViewModel = ViewModelProvider(this)[EditProfileFragmentViewModel::class.java]

        mBinding.imgAddImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pickImage.launch(intent)
        }

        mBinding.btnCompleteEdditing.setOnClickListener {
            // edit()
            APP_ACTIVITY.navController.navigate(R.id.action_editProfileFragment_to_profileFragment)
        }

        mBinding.btnBack.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_editProfileFragment_to_profileFragment)
        }
    }

    private fun loadUserDetails() {
        mBinding.etMail.setText(preferenceManager.getString(KEY_EMAIL))
        mBinding.etName.setText(preferenceManager.getString(KEY_NAME)?.capitalize())
        mBinding.etSurname.setText(preferenceManager.getString(KEY_SURNAME)?.capitalize())
        mBinding.etBuilding.setText(preferenceManager.getString(KEY_BUILDING)?.capitalize())
        val bytes = Base64.decode(preferenceManager.getString(KEY_IMAGE), Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        mBinding.imgAddImage.setImageBitmap(bitmap)
    }

//    private fun edit() {
//        val inputName = mBinding.etName.text.toString()
//        val inputSurname = mBinding.etSurname.text.toString()
//
//        val userId = FirebaseAuth.getInstance().currentUser!!.uid
//
//        val mapUpdate = mapOf(
//            KEY_NAME to inputName,
//            KEY_SURNAME to inputSurname,
//        )
//
//        database.collection(KEY_COLLECTION_CHAT).document(userId).update(
//            "message", "zalupa"
//        )
//            .addOnSuccessListener {
//                showToast("Success")
//            }
//            .addOnFailureListener {
//                showToast("Failed")
//            }
//
//    }


}