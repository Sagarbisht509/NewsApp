package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.models.Article
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.util.Resource
import com.google.gson.Gson

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
        setupRecyclerView()

        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e("findME", message)
                    }
                }

                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(::onArticleClicked, ::onSaveArticleClicked, "newsFragment")
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun onArticleClicked(article: Article) {
        val bundle = Bundle()
        bundle.putString("article", Gson().toJson(article))
        findNavController().navigate(R.id.action_newsFragment_to_articleFragment, bundle)
        Toast.makeText(requireContext(), article.title, Toast.LENGTH_SHORT).show()
    }

    private fun onSaveArticleClicked(article: Article) {
        viewModel.upsertArticle(article)
    }
}