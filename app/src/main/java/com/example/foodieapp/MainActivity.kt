package com.example.foodieapp



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Getstarted_button=findViewById<Button>(R.id.Getstarted_button);
           Getstarted_button.setOnClickListener {
               val intent= Intent(this@MainActivity,LoginActivity::class.java);
            startActivity(intent);
            finish()



}}}