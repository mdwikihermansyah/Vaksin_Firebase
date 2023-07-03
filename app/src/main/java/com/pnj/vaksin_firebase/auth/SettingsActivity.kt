package com.pnj.vaksin_firebase.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.pnj.vaksin_firebase.MainActivity
import com.pnj.vaksin_firebase.R
import com.pnj.vaksin_firebase.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnUbahPass.setOnClickListener {
            val new_password = binding.txtUbahPass.text.toString()
            edit_password(new_password)
        }

        binding.btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun edit_password(new_password: String){
        val user = FirebaseAuth.getInstance().currentUser
        val new_password = new_password

        user!!.updatePassword(new_password).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Toast.makeText(this, "Password Berhasil Diubah", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Password Gagal dirubah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser == null) {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}