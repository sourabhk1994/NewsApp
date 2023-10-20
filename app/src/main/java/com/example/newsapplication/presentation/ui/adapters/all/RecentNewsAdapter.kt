package com.example.newsapplication.presentation.ui.adapters.all

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.databinding.RcvRecentNewsBinding
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.presentation.ui.adapters.difUtil.RecentNewsDiffCallback
import com.example.newsapplication.utils.Utility

class RecentNewsAdapter(
    private val context: Context,
    private val onClickListener: OnNewsItemClickListener
) :
    PagingDataAdapter<Article, RecentNewsAdapter.RecentNewsViewHolder>(
        RecentNewsDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentNewsViewHolder {
        val binding =
            RcvRecentNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentNewsViewHolder, position: Int) {
        with(holder) {
            val item = getItem(position)
            binding.txtTitleRecent.text = item?.title
            binding.description.text =
                item?.publishedAt?.let { Utility.formatDate(it) }
            Glide.with(context)
                .load(item?.urlToImage)
                .into(binding.imgDetails)
            holder.itemView.setOnClickListener {
                if (item != null) {
                    onClickListener.onClickNews(item)
                }
            }
        }
    }

    inner class RecentNewsViewHolder(val binding: RcvRecentNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    class OnNewsItemClickListener(val clickListener: (article: Article) -> Unit) {
        fun onClickNews(article: Article) = clickListener(article)
    }
}
