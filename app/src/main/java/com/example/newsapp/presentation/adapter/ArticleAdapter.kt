package com.example.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.databinding.LayoutNewsItemBinding

class ArticleAdapter() :
    ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleListDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutNewsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ArticleViewHolder(val _binding: LayoutNewsItemBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        fun bind(article: Article) {
            _binding.article = article
            _binding.executePendingBindings()
        }
    }
}

object ArticleListDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.publishedAt === newItem.publishedAt
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}