package com.example.rickandmortylist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortylist.databinding.ActivityMainBinding
import com.example.rickandmortylist.view.fragment.RecyclerFragment
import com.example.rickandmortylist.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainerView.id, RecyclerFragment())
            .addToBackStack(null)
            .commit()
    }
}