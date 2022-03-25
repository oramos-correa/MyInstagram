package com.example.myinstagram

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(ParseUser.getCurrentUser() != null) {
            gotoMainActivity()
        }
        
        findViewById<Button>(R.id.bnLogin).setOnClickListener { 
            val username = findViewById<EditText>(R.id.userName).text.toString()
            val password = findViewById<EditText>(R.id.passWord).text.toString()
            loginUser(username, password)
            
        }
        findViewById<Button>(R.id.bnSignUp).setOnClickListener {
            val username = findViewById<EditText>(R.id.userName).text.toString()
            val password = findViewById<EditText>(R.id.passWord).text.toString()
            signUpUser(username, password)

        }
    }

    private fun signUpUser(username: String, password: String){
        // Create the ParseUser
        val user = ParseUser()

// Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // Hooray! Let them use the app now.
            } else {
                e.printStackTrace()
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Logged in")
                gotoMainActivity()
                // Hooray!  The user is logged in.
            } else {
                e.printStackTrace()
               Toast.makeText(this, "ErrorLogging in", Toast.LENGTH_SHORT).show()
                // Signup failed.  Look at the ParseException to see what happened.
            }})
        )
    }
    private fun gotoMainActivity(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        const val TAG = "LoginActivity"
    }
}