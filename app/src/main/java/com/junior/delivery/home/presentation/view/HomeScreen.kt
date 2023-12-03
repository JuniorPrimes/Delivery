package com.junior.delivery.home.presentation.view

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.junior.delivery.R
import com.junior.delivery.core.routes.LocalNavController
import com.junior.delivery.core.routes.Routes
import com.junior.delivery.home.data.model.RestaurantModel
import com.junior.delivery.home.presentation.view.composables.BasicTextField
import com.junior.delivery.home.presentation.viewmodel.HomeViewModel
import com.junior.delivery.ui.theme.NormalPurple
import com.junior.delivery.ui.theme.SoftPurple
import com.junior.delivery.ui.theme.TitleTextStyle
import com.junior.delivery.ui.theme.UltraPurple

@Composable
fun HomeScreen(navController: NavHostController = LocalNavController.current,homeViewModel:HomeViewModel = hiltViewModel()) {
    val restaurantList by homeViewModel.restaurants.observeAsState(emptyList())

    LaunchedEffect(restaurantList) {
        restaurantList.forEachIndexed { index, restaurant ->
            Log.i("Restaurant $index", "$restaurant")
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
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
        }, restaurantList,navController)
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
        Text(text = "Home", style = TitleTextStyle, fontSize = 30.sp)

        var textValue by remember { mutableStateOf(String()) }
        SearchProductTextField(value = textValue) { newValue -> textValue = newValue }
    }
}

@Composable
fun Body(modifier: Modifier, restaurantList: List<RestaurantModel>, navController: NavHostController) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        restaurantList.forEachIndexed { _, restaurant ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NormalPurple)
                    .padding(16.dp)
                    .clickable { navController.navigate(Routes.DetailsScreen.createRoute(restaurant.id)) }
            ) {
                Column {
                    Text(
                        text = restaurant.name,
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    //Text(text = restaurant.description, color = Color.Black, fontSize = 12.sp)
                    val imageUrl = restaurant.coverUrl
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .height(90.dp) // Adjust height as needed
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium) // Apply shape if needed
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
        placeholder = stringResource(id = R.string.search_product),
        label = stringResource(id = R.string.search_product),
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