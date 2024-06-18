package com.dm.berxley.wishlist.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dm.berxley.wishlist.R
import com.dm.berxley.wishlist.models.Wish
import com.dm.berxley.wishlist.viewModels.WishViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {

    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val snackBarState = remember {
        SnackbarHostState()
    }

    if (id != 0L) {
        val wish = viewModel.getWIshById(id)
            .collectAsState(initial = Wish(id = 0L, title = "", description = ""))
        viewModel.wishDescriptionState = wish.value.description
        viewModel.wishTitleState = wish.value.title
    } else {
        viewModel.wishDescriptionState = ""
        viewModel.wishTitleState = ""
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarState)
        },
        topBar = {
            AppBarView(
                title = if (id != 0L) stringResource(id = R.string.update_wish) else stringResource(
                    id = R.string.add_wish
                ),
                onBackNavClicked = {
                    navController.navigateUp()
                }
            )
        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(8.dp)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Title",
                value = viewModel.wishTitleState,
                onValueChanged = {
                    viewModel.onWishTitleChanged(it)
                })

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged = {
                    viewModel.onWishDescriptionChanged(it)
                })

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if (viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()) {

                    if (id != 0L) {
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "Wish has been updated successfully!"

                    } else {
                        //add wish
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "A wish has been created successfully!"

                    }
                } else {
                    snackMessage.value = "Enter fields to create wish"
                }
                scope.launch {
                    snackBarState.showSnackbar(snackMessage.value)
                    navController.navigateUp()

                }
            }) {
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_wish) else stringResource(
                        id = R.string.add_wish
                    ),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }
}

@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
) {


    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.black),
            focusedBorderColor = colorResource(id = R.color.app_bar_color),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.app_bar_color),
            unfocusedLabelColor = colorResource(id = R.color.black),
        ),
    )

}


@Preview(showBackground = true)
@Composable
fun WIshTextFieldPrev() {
    WishTextField(label = "Name", value = "", onValueChanged = {})
}