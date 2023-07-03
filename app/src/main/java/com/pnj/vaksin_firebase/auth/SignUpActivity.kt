package com.pnj.vaksin_firebase.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pnj.vaksin_firebase.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignup.setOnClickListener {
            val email = binding.txtSignupEmail.text.toString()
            val password = binding.txtSignupPass.text.toString()
            val confirm_password = binding.txtConfirmSignupPass.text.toString()

            signup_firebase(email, password, confirm_password)
        }

        //triger untuk kembali ke activity sign
        binding.tvSignin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signup_firebase(email: String, password: String, confirm_password: String) {
        if(email.isNotEmpty() && password.isNotEmpty() && confirm_password.isNotEmpty()) {
            if (password == confirm_password) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, SignInActivity::class.java)
                        startActivity(intent)
                    }
                    else { Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show() }
                }
            }
            else {
                Toast.makeText(this, "Samakan Password dan Konfirmasi Password", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            Toast.makeText(this, "lengkapi input ", Toast.LENGTH_SHORT).show()
        }
    }

}