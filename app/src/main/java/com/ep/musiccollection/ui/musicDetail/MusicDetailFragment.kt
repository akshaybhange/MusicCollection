package com.ep.musiccollection.ui.musicDetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.ep.musiccollection.R
import com.ep.musiccollection.databinding.MusicDetailFragmentBinding

class MusicDetailFragment : Fragment() {
    private lateinit var viewModel: MusicDetailViewModel
    private lateinit var binding : MusicDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.music_detail_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MusicDetailViewModel::class.java)
        binding.music = arguments?.let { MusicDetailFragmentArgs.fromBundle(it).StringArgumentMusic }
    }

}
