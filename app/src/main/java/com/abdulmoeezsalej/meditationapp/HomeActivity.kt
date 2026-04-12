package com.abdulmoeezsalej.meditationapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val keypass = intent.getStringExtra("keypass") ?: ""
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        // Setup RecyclerView
        adapter = EntityAdapter(emptyList()) { entity ->
            // Navigate to details screen
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("artworkTitle", entity.artworkTitle)
            intent.putExtra("artist", entity.artist)
            intent.putExtra("medium", entity.medium)
            intent.putExtra("year", entity.year)
            intent.putExtra("description", entity.description)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observe entities
        viewModel.entities.observe(this) { entities ->
            adapter.updateEntities(entities)
        }

        // Observe loading
        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe error
        viewModel.error.observe(this) { error ->
            Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
        }

        // Fetch data
        viewModel.fetchDashboard(keypass)

        // Navigation
        val navSettings = findViewById<LinearLayout>(R.id.navSettings)
        navSettings.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
    }
}