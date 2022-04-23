package com.example.foodieapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class LoginActivity : AppCompatActivity() {
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        val buttonLogin = findViewById<Button>(R.id.login_button)
        val email=findViewById<EditText>(R.id.email_1)
        val password=findViewById<EditText>(R.id.password_1)
        buttonLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(email.text.toString().trim(),password.text.toString().trim()).addOnCompleteListener(this){
                if(it.isSuccessful){
                    val intent = Intent(this@LoginActivity, LanguageActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(
                        this,
                        "Invalid User Credentials",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        val buttonRegister = findViewById<Button>(R.id.register_button)
        buttonRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}