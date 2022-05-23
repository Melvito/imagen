package com.example.iniciosesion

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*


class MainActivity3 : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    lateinit var previewimg: ImageView
    lateinit var seleccionar: Button
    lateinit var subir: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        seleccionar = findViewById(R.id.btnbuscar)
        subir = findViewById(R.id.btnsubir)
        previewimg = findViewById(R.id.imgimage)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        seleccionar.setOnClickListener {
          selec()
        }
        subir.setOnClickListener {
            sub()
        }
    }

    private fun selec(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Elija un archivo de imagen"), PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST  && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                previewimg.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    private fun sub(){
        if(filePath != null){
            val ref = storageReference?.child("cositias/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)
            Toast.makeText(this, "Enviado", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Seleccione imagen", Toast.LENGTH_SHORT).show()
        }
    }

}