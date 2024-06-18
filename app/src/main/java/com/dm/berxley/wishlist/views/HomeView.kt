package com.dm.berxley.wishlist.views

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dm.berxley.wishlist.R
import com.dm.berxley.wishlist.models.DummyWish
import com.dm.berxley.wishlist.models.Wish
import com.dm.berxley.wishlist.viewModels.WishViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppBarView(
                title = "Wishlist"
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 16.dp),
                contentColor = colorResource(id = R.color.white),
                containerColor = colorResource(id = R.color.black),
                onClick = {
                    navController.navigate(Screen.AddScreen.route + "/0L")
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        val listItems = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            items(listItems.value, key = { wish -> wish.id }) { wishItem ->

                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                            viewModel.deleteWish(wishItem)
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            if (dismissState.dismissDirection == DismissDirection.EndToStart) Color.Red else Color.Transparent,
                            label = ""
                        )
                        val alignment = Alignment.CenterEnd
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp),
                            contentAlignment = alignment
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    directions = setOf( DismissDirection.EndToStart),
                    dismissContent = {
                        Wish(wish = wishItem) {
                            val id = wishItem.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }

                )
//                Wish(wish = wishItem) {
//                    val id = wishItem.id
//                    navController.navigate(Screen.AddScreen.route + "/$id")
//                }
            }
        }
    }
}


@Composable
fun Wish(wish: Wish, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }
    }
}
