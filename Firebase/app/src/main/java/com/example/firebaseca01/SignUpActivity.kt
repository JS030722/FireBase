package com.example.firebaseca01

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var age : EditText
    private lateinit var pass: EditText
    private lateinit var cpass: EditText
    private lateinit var button: Button
    private lateinit var tv: TextView

    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        email = findViewById(R.id.email)
        age = findViewById(R.id.age)
        pass = findViewById(R.id.pass)
        cpass = findViewById(R.id.cpass)
        button = findViewById(R.id.button)
        tv = findViewById(R.id.textView)

        firebaseAuth = FirebaseAuth.getInstance()

        tv.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)

        }

        button.setOnClickListener {
            button.setOnClickListener {
                if (email.toString().isNotEmpty() && pass.toString().isNotEmpty() && cpass.toString()
                        .isNotEmpty() ) {
                        firebaseAuth.createUserWithEmailAndPassword(
                            email.text.toString(),
                            pass.text.toString()
                        )
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(this,"Sign Up Successful",Toast.LENGTH_LONG).show()
                                    val intent = Intent(this, SignInActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(
                                        this,
                                        it.exception.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }

                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }


            }
        }
    }
}