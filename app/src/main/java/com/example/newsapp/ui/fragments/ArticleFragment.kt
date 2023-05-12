package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.models.Article
import com.example.newsapp.ui.NewsViewModel
import com.google.gson.Gson

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding

    private lateinit var viewModel: NewsViewModel
    private lateinit var article: Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        getData()

        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

    private fun getData() {
        val jsonArticle = arguments?.getString("article")
        if (jsonArticle != null) {
            article = Gson().fromJson(jsonArticle, Article::class.java)
            // article.let {
            Toast.makeText(requireContext(), "article" + article.title, Toast.LENGTH_SHORT)
                .show()
            //}
        }
    }
}