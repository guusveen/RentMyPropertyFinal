package com.example.rentmyproperty.ui.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.rentmyproperty.R
import com.example.rentmyproperty.R.string.take_picture
import com.example.rentmyproperty.databinding.FragmentCameraBinding


class Camera : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private var imageUri: Uri? = null
    private var imageView: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = binding.imageviewPicture
        binding.buttonTakePicture.setOnClickListener {
            // Request permission
            if (isCameraPermissionGranted()) {
                // Open the camera interface
                openCameraInterface()
            }
        }
    }

    private fun isCameraPermissionGranted(): Boolean {
        val appContext = context?.applicationContext!!

        return if (ActivityCompat.checkSelfPermission(
                appContext,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    android.Manifest.permission.CAMERA,
                ),
                1
            )
            false
        } else {
            true
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(activity as Context)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.ok_button_title, null)

        val dialog = builder.create()
        dialog.show()
    }

    private fun openCameraInterface() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, take_picture)
        values.put(MediaStore.Images.Media.DESCRIPTION, take_picture)
        imageUri =
            activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        // Create intent for camera
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        resultLauncher.launch(intent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                imageView?.setImageURI(imageUri)
            } else {
                showAlert("Failed to take camera picture")
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "Camera Fragment"
    }
}


