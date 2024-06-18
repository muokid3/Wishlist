package com.dm.berxley.wishlist.utils

import android.app.Application

class WishApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}