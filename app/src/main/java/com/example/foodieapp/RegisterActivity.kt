package com.example.foodieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    private lateinit var  name:AppCompatEditText;
    private lateinit var email:AppCompatEditText;
    private lateinit var  phoneNumber:AppCompatEditText
    private lateinit var adress:AppCompatEditText;
    private lateinit var pincode:AppCompatEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
        name=findViewById(R.id.full_name_text);
        email=findViewById(R.id.email_id_text);
        phoneNumber=findViewById(R.id.phn_no_text);
        adress=findViewById(R.id.address_text);
        pincode=findViewById(R.id.pin_text);
        var register_button=findViewById<Button>(R.id.btnRegister);
        register_button.setOnClickListener {
            fireBaseSignup();
        }
    }

    private fun fireBaseSignup() {
    auth.createUserWithEmailAndPassword(email.text.toString(),phoneNumber.text.toString()).addOnCompleteListener({
        task ->
        if(task.isSuccessful){
            Toast.makeText(this,"Register Successfull",Toast.LENGTH_LONG);
        }
    })
    }

}