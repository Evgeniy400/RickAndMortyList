package com.example.rickandmortylist.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import coil.load
import com.example.rickandmortylist.R
import com.example.rickandmortylist.databinding.FragmentDescriptionBinding
import com.example.rickandmortylist.network.Character
import com.example.rickandmortylist.network.InteractorAPIImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDescriptionBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData(arguments?.getInt(ID) ?: 1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getData(id: Int) {
        lifecycleScope.launch(Dispatchers.Main) {
            val api = InteractorAPIImpl()
            api.getCharacterById(id)?.let {
                setCharacter(it)
            }

        }
    }

    private fun setCharacter(character: Character){
        binding.apply {
            descriptionName.text = character.name
            descriptionImage.load(character.image)
            descriptionSpecies.text = character.species
            descriptionGender.text = character.gender
            descriptionStatus.text = character.status
            descriptionLocation.text = character.location.name
            descriptionEpisodes.text = character.episode.count().toString()
        }
    }


    companion object {
        const val ID = "ID"
    }
}