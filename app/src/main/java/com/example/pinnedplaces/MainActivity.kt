package com.example.pinnedplaces

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinnedplaces.adapter.ListLocationAdapter
import com.example.pinnedplaces.databinding.ActivityMainBinding
import com.example.pinnedplaces.model.Location

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.list_menu -> {
                    binding.rvLocation.layoutManager =
                        StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    true
                }

                R.id.grid_menu -> {
                    binding.rvLocation.layoutManager =
                        GridLayoutManager(this, 3)
                    true
                }

                R.id.about_page -> {
                    startActivity(Intent(this@MainActivity, AboutActivity::class.java))
                    true
                }

                else -> false
            }
        }

        binding.shareButton.setOnClickListener {

        }

        showRecyclerView(getData())
    }

    fun showRecyclerView(data: ArrayList<Location>) {
        val listAdapter = ListLocationAdapter(data)
        binding.rvLocation.adapter = listAdapter
        binding.rvLocation.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
    }

    fun getData(): ArrayList<Location> {
        val dataPhoto = resources.getStringArray(R.array.location_image_list)
        val dataTitle = resources.getStringArray(R.array.location_title_list)
        val dataSecond = resources.getStringArray(R.array.location_second_list)
        val dataSupport = resources.getStringArray(R.array.location_support_list)
        val dataDetail = resources.getStringArray(R.array.location_detail_list)

        var listLocation = ArrayList<Location>()
        for (i in dataTitle.indices) {
            val location =
                Location(dataPhoto[i], dataTitle[i], dataSecond[i], dataSupport[i], dataDetail[i])
            listLocation.add(location)
        }
        return listLocation
    }
}