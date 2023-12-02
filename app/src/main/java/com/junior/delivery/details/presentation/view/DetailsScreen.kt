package com.junior.delivery.details.presentation.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.junior.delivery.R
import com.junior.delivery.details.data.model.FoodModel
import com.junior.delivery.details.presentation.composables.BasicTextField
import com.junior.delivery.details.presentation.viewmodel.DetailsViewModel
import com.junior.delivery.ui.theme.NormalPurple
import com.junior.delivery.ui.theme.SoftPurple
import com.junior.delivery.ui.theme.TitleTextStyle
import com.junior.delivery.ui.theme.UltraPurple

@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel = hiltViewModel()) {
    val foodList by detailsViewModel.foods.observeAsState(emptyList())

    LaunchedEffect(foodList) {
        foodList.forEachIndexed { index, food ->
            Log.i("Food $index", "$food")
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            //.padding(horizontal = 30.dp, vertical = 15.dp)
            .background(SoftPurple)
            .verticalScroll(rememberScrollState())
    ) {
        val (header, body, footer) = createRefs()

        Header(modifier = Modifier.constrainAs(header) {
            top.linkTo(parent.top)
            bottom.linkTo(body.top)
        })
        Body(modifier = Modifier.constrainAs(body) {
            top.linkTo(header.bottom)
            bottom.linkTo(footer.top)
        }, foodList)
        Footer(modifier = Modifier.constrainAs(footer) {
            top.linkTo(body.bottom)
            bottom.linkTo(parent.bottom)
        })

        createVerticalChain(header, body, footer, chainStyle = ChainStyle.SpreadInside)
    }
}

@Composable
fun Header(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 50.dp, bottom = 20.dp)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Foods", style = TitleTextStyle, fontSize = 30.sp)
        SearchProductTextField("") { }
    }
}

@Composable
fun Body(modifier: Modifier, foodList: List<FoodModel>) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        foodList.forEachIndexed { _, food ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NormalPurple)
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = food.name,
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    //Text(text = restaurant.description, color = Color.Black, fontSize = 12.sp)
                    val imageUrl = food.coverURL
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .height(90.dp) // Adjust height as needed
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium) // Apply shape if needed
                            .clickable { /* Handle click event */ } // Optional: Add click behavior
                    )
                }
            }
        }
    }
}

@Composable
fun SearchProductTextField(value: String, onTextChanged: (String) -> Unit) {
    BasicTextField(
        value = value,
        placeholder = "Buscar producto",
        label = "Buscar producto",
        onTextChanged = onTextChanged,
        imageVector = Icons.Default.Search
    )
}

@Composable
fun Footer(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.copyright),
                fontSize = 16.sp,
                color = UltraPurple
            )
        }
    }
}