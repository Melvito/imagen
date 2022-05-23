package com.example.iniciosesion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    // creamos las variables que usaremos
    private lateinit var btnRegistrartee: TextView
    lateinit var etemaill: EditText
    private lateinit var etcontraa: EditText
    lateinit var imgloginn: ImageButton
    // creamos el objeto de firebase para la base de datos
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // asignamos a las variables los objetos de donde sacaran informaciuon
        btnRegistrartee = findViewById(R.id.btnregistrarte)
        etemaill = findViewById(R.id.etemail)
        etcontraa = findViewById(R.id.etcontra)
        imgloginn = findViewById(R.id.imglogin)

        // Iniciamos  la variable del objeto antes creado
        auth = FirebaseAuth.getInstance()

         imgloginn.setOnClickListener {
            login()
        }

        btnRegistrartee.setOnClickListener {
            val intent = Intent(this, MainActivityregister::class.java)
            startActivity(intent)
            // cerramos la actividad actual
            finish()
        }
    }
    // creamos un metodo para hacer  el proceso del login
    private fun login() {
        // le asignamos las variabless de caddena a una nueva variabe para los campos llnados
        val email = etemaill.text.toString().trim()
        val pass = etcontraa.text.toString().trim()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            // si la informacion es correcrra le  damos acceso a la sigueiente actividad
            if (it.isSuccessful) {
                Toast.makeText(this, "Acceso Autorizado!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
                // cerramos actividad actual
                finish()
              // si no le decimos que no tiene acceso a esa activada
            } else
                Toast.makeText(this, "Acceso no autorizado! ", Toast.LENGTH_SHORT).show()







        }
    }

}