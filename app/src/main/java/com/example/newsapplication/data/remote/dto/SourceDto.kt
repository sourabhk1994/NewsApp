package com.example.newsapplication.data.remote.dto

import com.example.newsapplication.data.local.entity.Source

data class SourceDto(
    val name: String
)
fun SourceDto.toSource(): Source {
    return Source(
        name = this.name,
    )
}