package uz.gita.firstlesson.a2048game.screens

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import uz.gita.firstlesson.a2048game.R
import java.io.File
import java.io.FileOutputStream

class InfoScreen : Fragment(R.layout.info_screen) {
    private lateinit var imgView : ImageView
    private val myImage = registerForActivityResult(ActivityResultContracts.GetContent()){uri : Uri? ->
        uri?.let {
            imgView.setImageURI(uri)
            saveImageToInternalStorage(uri)
        }
    }

    private fun saveImageToInternalStorage(uri: Uri) {
        try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val file = File(requireContext().filesDir, "background.jpg")
            val outputStream = FileOutputStream(file)

            inputStream?.copyTo(outputStream)

            inputStream?.close()
            outputStream.close()
            Toast.makeText(requireContext(), "Fon rasmi saqlandi ✅", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadBackgroundImage()

        view.findViewById<ImageView>(R.id.back_btn).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        view.findViewById<ImageView>(R.id.telegram).setOnClickListener {
            val url = "https://t.me/Islombek_Yoqubov"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        view.findViewById<ImageView>(R.id.telephone).setOnClickListener {
            val phone = "+998200132227"
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))

            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED
            ) {
                startActivity(intent)
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    1
                )
            }
        }

    }

    private fun loadBackgroundImage() {
        val file = File(requireContext().filesDir, "background.jpg")
        if (file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            view?.findViewById<ImageView>(R.id.info_background_img)?.setImageBitmap(bitmap)
        }
    }

}