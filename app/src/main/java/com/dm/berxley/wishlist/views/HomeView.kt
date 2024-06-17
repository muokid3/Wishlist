package com.dm.berxley.wishlist.views

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.dm.berxley.wishlist.R

@Composable
fun HomeView() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppBarView(
                title = "Wishlist",
                onBackNavClicked = {
                    Toast.makeText(context, "Back clicked", Toast.LENGTH_SHORT).show()
                })
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 16.dp),
                contentColor = colorResource(id = R.color.white),
                containerColor = colorResource(id = R.color.black),
                onClick = {
                    Toast.makeText(context, "FAB clicked", Toast.LENGTH_SHORT).show()
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {

        }
    }
}