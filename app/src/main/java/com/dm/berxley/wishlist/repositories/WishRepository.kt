package com.dm.berxley.wishlist.repositories

import com.dm.berxley.wishlist.daos.WishDao
import com.dm.berxley.wishlist.models.Wish
import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {
    suspend fun addWish(wish: Wish){
        wishDao.addWish(wish)
    }

    fun getAllWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getWishById(id: Long):Flow<Wish>{
        return wishDao.getWishById(id)
    }

    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }

    suspend fun deleteWish(wish: Wish){
        wishDao.deleteWish(wish)
    }

}