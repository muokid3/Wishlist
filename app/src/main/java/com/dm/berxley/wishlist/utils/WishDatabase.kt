package com.dm.berxley.wishlist.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dm.berxley.wishlist.daos.WishDao
import com.dm.berxley.wishlist.models.Wish

@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)
abstract class WishDatabase: RoomDatabase() {
    abstract fun wishDao(): WishDao
}