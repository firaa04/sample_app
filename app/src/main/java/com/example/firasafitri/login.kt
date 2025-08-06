package com.example.firasafitri

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

    class login:AppCompatActivity() {
    private lateinit var dbhelper: UserDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbhelper = UserDatabaseHelper(this)

        val username = findViewById<EditText>(R.id.username)
        val pass = findViewById<EditText>(R.id.pass)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener{
            val username = username.text.toString()
            val password = pass.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val isValid = dbhelper.checkUser(username, password)
            if (isValid) {
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)

                intent.putExtra("username", username) // kirim data username
                startActivity(intent)
                // tutup LoginActivity agar tidak bisa balik pakai tombol back
                finish()
            } else {
                Toast.makeText(this, "Login gagal : user tidak ditemukan", Toast.LENGTH_SHORT).show()

            }
        }


        }
    }
