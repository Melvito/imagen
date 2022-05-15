package com.example.iniciosesion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var btnRegistrartee: TextView
    lateinit var etemaill: EditText
    private lateinit var etcontraa: EditText
    lateinit var imgloginn: ImageButton
    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View Binding
        btnRegistrartee = findViewById(R.id.btnregistrarte)
        etemaill = findViewById(R.id.etemail)
        etcontraa = findViewById(R.id.etcontra)
        imgloginn = findViewById(R.id.imglogin)

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()

         imgloginn.setOnClickListener {
            login()
        }

        btnRegistrartee.setOnClickListener {
            val intent = Intent(this, MainActivityregister::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()
        }
    }
    private fun login() {
        val email = etemaill.text.toString().trim()
        val pass = etcontraa.text.toString().trim()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Acceso Autorizado!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainAmikos::class.java)
                startActivity(intent)
                // using finish() to end the activity
                finish()

            } else
                Toast.makeText(this, "Acceso no autorizado! ", Toast.LENGTH_SHORT).show()







        }
    }

}