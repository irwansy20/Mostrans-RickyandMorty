package com.irwan.testmostrans

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irwan.testmostrans.adapter.CharAdapter
import com.irwan.testmostrans.api.ApiConfig
import com.irwan.testmostrans.api.CharResponse
import com.irwan.testmostrans.api.ResultsItem
import com.irwan.testmostrans.databinding.ActivityMainBinding
import com.irwan.testmostrans.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvChar: RecyclerView
    private lateinit var charAdapter: CharAdapter
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MainViewModel::class.java)

        rvChar = binding.rvChar
        rvChar.layoutManager = LinearLayoutManager(this)
        charAdapter = CharAdapter()
        rvChar.adapter = charAdapter

        mainViewModel.isLoading.observe(this,{
            showLoading(it)
        })

        mainViewModel.loadChar.observe(this,{characters ->
            charAdapter.setData(characters)
        })

        mainViewModel.loadCharacter()

        charAdapter.setOnItemClickCallback(object : CharAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ResultsItem) {
                showSelectedChar(data.id, data.name, data.image)
            }
        })
    }

    private fun showSelectedChar(id: Int, name: String, foto: String){
        val moveData = Intent(this@MainActivity, DetailActivity::class.java)
        moveData.putExtra(DetailActivity.EXTRA_ID, id)
        moveData.putExtra(DetailActivity.EXTRA_NAME, name)
        moveData.putExtra(DetailActivity.EXTRA_PHOTO, foto)
        startActivity(moveData)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}