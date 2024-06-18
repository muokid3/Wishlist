package com.dm.berxley.wishlist.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dm.berxley.wishlist.models.Wish
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wish: Wish)

    @Query("select * from `wishes_table`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updateWish(wishEntity: Wish)

    @Delete
    abstract suspend fun deleteWish(wishEntity: Wish)

    @Query("select * from `wishes_table` where id=:id")
    abstract fun getWishById(id:Long): Flow<Wish>
}