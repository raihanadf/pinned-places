package com.example.pinnedplaces

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.example.pinnedplaces.databinding.ActivityDetailBinding
import com.example.pinnedplaces.model.Location

class DetailActivity : AppCompatActivity() {
    companion object {
        const val location_detail = "LOCATION_DETAIL"
    }

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: Tidy up
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(location_detail, Location::class.java)
        } else {
            intent.getParcelableExtra(location_detail)
        }

        binding.imagePhoto.load(data?.imgPhoto) {
            placeholder(R.drawable.loading_img)
            error(R.drawable.disconnected)
        }
        binding.titleName.text = data?.title
        binding.secondText.text = data?.second
        binding.supportText.text = data?.support
        binding.detailText.text = data?.detail

    }
}