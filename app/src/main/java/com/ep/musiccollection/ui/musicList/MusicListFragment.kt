package com.ep.musiccollection.ui.musicList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ep.musiccollection.R
import com.ep.musiccollection.data.Resource
import com.ep.musiccollection.databinding.MusicListFragmentBinding
import com.ep.musiccollection.util.RetryCallback

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
        observeChanges()
    }

    private fun observeChanges() {
        viewModel.getMusicListData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { data ->
                        if (data.result.isNotEmpty()) {
                            adapter.updateMusicList(data.result)
                            showHideView(binding.rvMusicList)
                        } else {
                            binding.textErrorMessage.text = getString(R.string.msg_no_data)
                            showHideView(binding.layoutError)
                        }
                    }
                }
                is Resource.Loading -> showHideView(binding.progressMusicList)
                is Resource.Error -> {
                    binding.textErrorMessage.text = it.message
                    showHideView(binding.layoutError)
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
        binding.retryCallback = object : RetryCallback {
            override fun retry() {
                viewModel.getMusicList(getString(R.string.default_search_term))
            }

        }
    }

    private fun showHideView(view: View) {
        binding.progressMusicList.visibility =
            if (view.id == binding.progressMusicList.id) VISIBLE else GONE
        binding.rvMusicList.visibility =
            if (view.id == binding.rvMusicList.id) VISIBLE else GONE
        binding.layoutError.visibility =
            if (view.id == binding.layoutError.id) VISIBLE else GONE
    }
}
