package com.example.iniciosesion

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main_activityregister.*

class MainActivityregister : AppCompatActivity() {
    // se crea las variables que necesitaremos
    lateinit var etEmail: EditText
    lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSignUp: ImageButton
    lateinit var tvRedirectLogin: ImageButton
    // creamos la variable para la base de datos
     private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activityregister)

        // le asignamos los objetos a las variables
        etEmail = findViewById(R.id.etcorreo)
        etConfPass = findViewById(R.id.etconfircontra)
        etPass = findViewById(R.id.etcontraa)
        btnSignUp = findViewById(R.id.btnsingup)
        tvRedirectLogin = findViewById(R.id.atras)

        // Iniciamos la variiableaut
        auth = Firebase.auth

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        // le decimos que al darle clic al registrate lo redireccionara a la otra actividad
        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun signUpUser() {
        val email = etEmail.text.toString().trim()
        val pass = etPass.text.toString().trim()
        val confirmPassword = etconfircontra.text.toString()

        //verificamos que  los campos de la contraseña este vacio si lo esta le diremos que necesita llenar los campos
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Campos vacion necesitamos datos", Toast.LENGTH_SHORT).show()
            return
        }
          // si no verificaremos que las contraseñas sean iguales
        if (pass != confirmPassword) {
            Toast.makeText(this, "las contraseñas no coiciden", Toast.LENGTH_SHORT)
                .show()
            return
        }




        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // verificammos que los datos sean agregados correctamente si se envio le diremmos que
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    Toast.makeText(baseContext, "Registrado correctamente.",Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                } else {
                    // si no fue enviado la informacion le diremos que el registro fello
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Registro fallidoo.",
                        Toast.LENGTH_SHORT).show()
                }
            }








    }
}