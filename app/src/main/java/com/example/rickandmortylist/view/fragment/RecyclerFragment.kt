package com.example.rickandmortylist.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.rickandmortylist.R
import com.example.rickandmortylist.adapter.RecyclerAdapter
import com.example.rickandmortylist.databinding.FragmentRecyclerBinding
import com.example.rickandmortylist.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RecyclerFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRecyclerBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val pagingAdapter = RecyclerAdapter { id ->
            val fragment = DescriptionFragment()
            fragment.arguments = Bundle().apply {
                putInt(DescriptionFragment.ID, id+1) // в Api нумерация с 1 а не 0
            }

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, fragment)
                ?.addToBackStack(null)
                ?.commit()

        }


        binding.recyclerView.adapter = pagingAdapter


        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}