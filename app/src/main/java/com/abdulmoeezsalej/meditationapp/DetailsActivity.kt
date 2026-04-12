package com.abdulmoeezsalej.meditationapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Get data from intent
        val artworkTitle = intent.getStringExtra("artworkTitle") ?: ""
        val artist = intent.getStringExtra("artist") ?: ""
        val medium = intent.getStringExtra("medium") ?: ""
        val year = intent.getIntExtra("year", 0)
        val description = intent.getStringExtra("description") ?: ""

        // Set data to views
        findViewById<TextView>(R.id.detailArtworkTitle).text = artworkTitle
        findViewById<TextView>(R.id.detailArtist).text = "Artist: $artist"
        findViewById<TextView>(R.id.detailMedium).text = "Medium: $medium"
        findViewById<TextView>(R.id.detailYear).text = "Year: $year"
        findViewById<TextView>(R.id.detailDescription).text = description

        // Back button
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}