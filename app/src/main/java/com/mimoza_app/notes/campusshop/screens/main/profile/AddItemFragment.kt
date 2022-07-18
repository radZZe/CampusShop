package com.mimoza_app.notes.campusshop.screens.main.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.text.capitalize
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.database.firebase.AppFirebaseRepository
import com.mimoza_app.notes.campusshop.databinding.FragmentAddItemBinding
import com.mimoza_app.notes.campusshop.screens.signin.SignInFragmentViewModel
import com.mimoza_app.notes.campusshop.util.APP_ACTIVITY
import com.mimoza_app.notes.campusshop.util.PreferenceManager
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class AddItemFragment : Fragment() {

    private var _binding: FragmentAddItemBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var captureImage: ImageView
    private lateinit var encodedImage: String
    private lateinit var mViewModel: AddItemFragmentViewModel


    override fun onStart() {
        super.onStart()
        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.categories_dropdown_item, categories)
        mBinding.tvCategory.setAdapter(arrayAdapter)
        captureImage = mBinding.imgAddImage
        initialization()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    private fun initialization() {

        mViewModel = ViewModelProvider(this)[AddItemFragmentViewModel::class.java]

        mBinding.imgAddImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pickImage.launch(intent)
        }

        mBinding.btnFinish.setOnClickListener {
            addItem()
            APP_ACTIVITY.navController.navigate(R.id.action_addItemInShopFragment_to_profileFragment)
        }

        mBinding.btnBack.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_addItemInShopFragment_to_profileFragment)
        }

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

    private fun addItem() {
        val name = mBinding.etSetName.text.toString().capitalize()
        val price = mBinding.etSetPrice.text.toString()
        val description = mBinding.etSetDescription.text.toString().capitalize()
        val building = mBinding.etSetBuilding.text.toString()
        val category = mBinding.tvCategory.text.toString().capitalize()
        val image = encodedImage
        mViewModel.addItem(name, price, description, building, category, image, "jopa")
    }
}