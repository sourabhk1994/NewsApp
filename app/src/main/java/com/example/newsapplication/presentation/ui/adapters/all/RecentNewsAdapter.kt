package com.example.newsapplication.presentation.ui.adapters.all

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.newsapplication.R
import com.example.newsapplication.databinding.RcvRecentNewsBinding
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.presentation.ui.adapters.difUtil.RecentNewsDiffCallback

class RecentNewsAdapter(private val context: Context) : PagingDataAdapter<Article, RecentNewsViewHolder>(
    RecentNewsDiffCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentNewsViewHolder {
        val binding = RcvRecentNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentNewsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecentNewsViewHolder, position: Int) {
        with(holder){
            binding.txtTitleRecent.text = getItem(position)?.title
            binding.description.text = getItem(position)?.description
            val requestOptions = RequestOptions()
                .transform(CircleCrop())
            Glide.with(context)
                .load(getItem(position)?.urlToImage)
                .apply(requestOptions)
                .into(binding.imgDetails)
        }
    }
}
class RecentNewsViewHolder( val binding: RcvRecentNewsBinding) : RecyclerView.ViewHolder(binding.root)
