package com.example.newsapplication.presentation.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.newsapplication.databinding.FragmentSearchBinding
import com.example.newsapplication.presentation.ui.adapters.all.RecentNewsAdapter
import com.example.newsapplication.presentation.ui.viewmodel.SearchNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchNewsAdapter: RecentNewsAdapter
    private val searchNewsViewModel: SearchNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        getData()
        setUpSearch()
    }

    private fun setUpSearch(){
        binding.etSearchNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchNewsViewModel.searchNews(query.toString())
                binding.etSearchNews.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchNewsViewModel.searchNews(newText.toString())
                return true
            }

        })


    }

    private fun setUpRecyclerView(){
        searchNewsAdapter = RecentNewsAdapter(requireContext())
        binding.rcvSearch.apply {
            adapter = searchNewsAdapter
        }
    }
    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun getData(){

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                searchNewsViewModel.searchNewsFlow.collectLatest{
                    searchNewsAdapter.submitData(it)

                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
        binding.etSearchNews.post {
            binding.etSearchNews.requestFocus()
            val input: InputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            input.showSoftInput(binding.etSearchNews, InputMethodManager.SHOW_IMPLICIT)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}