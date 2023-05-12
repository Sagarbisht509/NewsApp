package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.models.Article

class NewsAdapter(
    private val onArticleClicked: (Article) -> Unit,
    private val onSaveArticleClicked: (Article) -> Unit,
    private val from: String
) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            Glide.with(binding.root.context).load(article.urlToImage).into(binding.newsImage)

            binding.root.setOnClickListener {
                onArticleClicked(article)
            }

            if (from == "saveFragment") {
                binding.saveBtn.visibility = View.GONE
            }

            binding.saveBtn.setOnClickListener {
                if (from != "saveFragment") {
                    onSaveArticleClicked(article)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        article?.let {
            holder.bind(it)
        }
    }
}