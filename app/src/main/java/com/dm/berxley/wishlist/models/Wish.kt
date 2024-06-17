package com.dm.berxley.wishlist.models

data class Wish(
    val id: Long = 0,
    val title: String = "",
    val description: String = ""
)


object DummyWish{
    val wishList = listOf<Wish>(
        Wish(id = 1, title = "Google Watch 2", description = "An android watch"),
        Wish(id = 1, title = "Geforce RTX 4070", description = "A badass PC computer, looks cool as well"),
        Wish(id = 1, title = "Sci-Fi Book", description = "A science fiction book from one of the best sellers"),
        Wish(id = 1, title = "Audi RSQ8", description = "A super powerful, comfortable and luxurious SUV that I will own and drive one day"),
    )
}