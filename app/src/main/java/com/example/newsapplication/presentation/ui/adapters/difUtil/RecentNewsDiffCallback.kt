package com.example.newsapplication.presentation.ui.adapters.difUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapplication.domain.model.Article

class RecentNewsDiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}