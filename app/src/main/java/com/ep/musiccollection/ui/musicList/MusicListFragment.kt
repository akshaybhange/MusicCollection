package com.ep.musiccollection.ui.musicList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ep.musiccollection.R
import com.ep.musiccollection.data.Resource
import com.ep.musiccollection.databinding.MusicListFragmentBinding

class MusicListFragment : Fragment() {

    private lateinit var viewModel: MusicListViewModel
    private lateinit var binding: MusicListFragmentBinding
    private var adapter: MusicListAdapter = MusicListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.music_list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.adapter = adapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MusicListViewModel::class.java)
        viewModel.getMusicList(getString(R.string.default_search_term))

        observeChanges()
    }

    private fun observeChanges() {
        viewModel.getMusicListData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    adapter.updateMusicList(it.data!!.result)
                }
            }
        })
        adapter.setOnItemClick {
            findNavController()
                .navigate(
                    MusicListFragmentDirections
                        .actionMusicListFragmentToMusicDetailFragment(it)
                )
        }
    }

}
