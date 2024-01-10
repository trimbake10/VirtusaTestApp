package com.virtusatestapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.virtusatestapp.adapter.PhotoAdapter
import com.virtusatestapp.databinding.ActivityMainBinding
import com.virtusatestapp.viewmodels.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val galleryViewModel: GalleryViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var photoAdapter: PhotoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        galleryViewModel.galleryObserver.observe(this) {
            binding.apply {
                pBar.visibility= View.GONE
                if (it?.isNotEmpty()!!) {
                    binding.tvEmptyMessage.visibility = View.GONE
                    rvImageList.layoutManager =
                        GridLayoutManager(this@MainActivity, 2)
                    photoAdapter = PhotoAdapter()
                    photoAdapter.differ.submitList(it)
                    rvImageList.adapter = photoAdapter
                } else {
                    tvEmptyMessage.visibility = View.VISIBLE
                }
            }
        }
        binding.pBar.visibility = View.VISIBLE
        galleryViewModel.getGalleryData()

    }
}