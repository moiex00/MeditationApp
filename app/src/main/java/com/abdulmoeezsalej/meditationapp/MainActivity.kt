package com.abdulmoeezsalej.meditationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val emailField = findViewById<EditText>(R.id.emailField)
        val passwordField = findViewById<EditText>(R.id.passwordField)

        // Observe login result
        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { keypass ->
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("keypass", keypass)
                intent.putExtra("username", emailField.text.toString().trim())
                intent.putExtra("password", passwordField.text.toString().trim())
                startActivity(intent)
            }
            result.onFailure {
                Toast.makeText(this, "Login failed: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }

        // Observe loading state
        viewModel.isLoading.observe(this) { isLoading ->
            loginButton.isEnabled = !isLoading
        }

        loginButton.setOnClickListener {
            val username = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.login(username, password)
        }
    }
}