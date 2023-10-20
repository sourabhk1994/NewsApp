package com.example.newsapplication.presentation.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsapplication.databinding.FragmentHomeBinding
import com.example.newsapplication.presentation.ui.adapters.all.RecentNewsAdapter
import com.example.newsapplication.presentation.ui.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val newsViewModel: NewsViewModel by viewModels()
    private var currentTheme: String = "light"
    private var isUsersFirstTimeVisit: Boolean = false
    lateinit var recentNewsAdapter: RecentNewsAdapter
    var job: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        if (newsViewModel.isLoading) {
            Toast.makeText(activity,"Loading...",Toast.LENGTH_LONG).show()
        }
        binding.etSearchNews.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }
        setUpAdapter()
        getData()

    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun getData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsViewModel.topHeadlinesFlow().collectLatest {
                    newsViewModel.isLoading = false
                    recentNewsAdapter.submitData(it)
                }
            }
        }
    }

    private fun setUpAdapter() {
        recentNewsAdapter = RecentNewsAdapter(requireContext())
        binding.recentNewsRcv.apply {
            adapter = recentNewsAdapter
        }

    }

}