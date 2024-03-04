package com.example.pinnedplaces

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.example.pinnedplaces.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private var _binding: ActivityAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val profileUrl =
            "https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/small/avatar/dos:8d4edfe349b779b8aca5d0e9f4b75b7d20231116074736.png"
        binding.profileImage.load(profileUrl) {
            placeholder(R.drawable.loading_img)
            error(R.drawable.disconnected)
        }

    }
}