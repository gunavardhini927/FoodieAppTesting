package com.example.foodieapp
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.regex.Matcher
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    private lateinit var name: AppCompatEditText;
    private lateinit var email: AppCompatEditText;
    private lateinit var phoneNumber: AppCompatEditText
    private lateinit var password: AppCompatEditText
    private lateinit var address: AppCompatEditText;
    private lateinit var pincode: AppCompatEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
        Toast.makeText(this@RegisterActivity, "You can register now", Toast.LENGTH_SHORT).show()

        name = findViewById(R.id.full_name_text)
        email = findViewById(R.id.email_id_text)
        phoneNumber = findViewById(R.id.phn_no_text)
        password = findViewById(R.id.password_text)
        address = findViewById(R.id.address_text);
        pincode = findViewById(R.id.pin_text);
        var register_button = findViewById<Button>(R.id.btnRegister);
        // When user clicks on register
        register_button.setOnClickListener {
            registerUser()
        }
    }


    private fun registerUser() {
        if(name.text.toString().trim().isEmpty()){
            name.setError("Please enter Full Name");
            name.requestFocus();
        }
        else if(email.text.toString().trim().isEmpty()){
            email.setError("Please enter your email ")
            email.requestFocus()
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString().trim()).matches()){
            email.setError("Email pattern is not matchecd")
            email.requestFocus()
        }
        else if(phoneNumber.text!!.length<10){
            phoneNumber.setError("Number can't be less than 10 digits")
            phoneNumber.requestFocus()
        }
        else if(address.text!!.length<10){
            address.setError("Address can't be empty")
            address.requestFocus()
        }
        else if(pincode.text.toString().length < 6){
            pincode.setError("Pincode can't be less than 6 digits")
            pincode.requestFocus()
        }
        else{
            auth.createUserWithEmailAndPassword(email.text.toString().trim(), password.text.toString().trim()).addOnCompleteListener(
            ) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "User registered successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    var fireBaseData= FirebaseDatabase.getInstance()


                    var User1=UserProfile();
                    User1.address=address.text.toString().trim();
                    User1.name=name.text.toString().trim();
                    User1.email=email.text.toString().trim();
                    User1.pincode=pincode.text.toString().trim()
                    User1.phoneNumber=phoneNumber.text.toString().trim()
                    fireBaseData.getReference("Users").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(User1).addOnCompleteListener(this){
                        if(it.isSuccessful){
                            Toast.makeText(
                                this,
                                "User data saved successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else{
                            Toast.makeText(
                                this,
                                "User data not saved due to error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {

                    Toast.makeText(
                        this,
                        "registration failed ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }




    }
}
