package com.example.memberlist

data class DataX(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<User>
)