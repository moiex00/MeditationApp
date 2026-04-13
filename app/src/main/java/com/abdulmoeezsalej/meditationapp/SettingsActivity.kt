package com.abdulmoeezsalej.meditationapp

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Get user info from intent
        val username = intent.getStringExtra("username") ?: "s8092899"
        val password = intent.getStringExtra("password") ?: "Abdul Moeez"
        val keypass = intent.getStringExtra("keypass") ?: "art"

        // Update profile info with real user data
        val userNameView = findViewById<TextView>(R.id.userName)
        val userEmailView = findViewById<TextView>(R.id.userEmail)

        userNameView.text = password   // password is the real name e.g. Abdul Moeez
        userEmailView.text = username  // username is the student ID e.g. s8092899

        // Navigate home
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        navHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("keypass", keypass)
            intent.putExtra("username", username)
            intent.putExtra("password", password)
            startActivity(intent)
        }
    }
}