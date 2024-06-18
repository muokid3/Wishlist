package com.dm.berxley.wishlist.utils

import android.content.Context
import androidx.room.Room
import com.dm.berxley.wishlist.repositories.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(database.wishDao())
    }

    fun provide (context: Context){
        database = Room.databaseBuilder(context,WishDatabase::class.java,"wishlist.db").build()
    }
}