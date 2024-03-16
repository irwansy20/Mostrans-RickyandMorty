package com.irwan.testmostrans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.irwan.testmostrans.databinding.ActivityDetailBinding
import com.irwan.testmostrans.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(DetailViewModel::class.java)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val name = intent.getStringExtra(EXTRA_NAME).toString()
        binding.nameChar.text = name
        val foto = intent.getStringExtra(EXTRA_PHOTO).toString()
        Glide.with(binding.charImg)
            .load(foto)
            .into(binding.charImg)

        detailViewModel.isLoading.observe(this,{
            showLoading(it)
        })

        detailViewModel.detailChar.observe(this,{character ->
            character?.let {
                binding.status.text = "Status : ${it.status}"
                binding.gender.text = "Gender : ${it.gender}"
            }
        })

        detailViewModel.loadDetails(id)

        supportActionBar?.hide()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object{
        var EXTRA_NAME = "detail_name"
        var EXTRA_ID = "detail_id"
        var EXTRA_PHOTO = "detail_foto"
    }
}