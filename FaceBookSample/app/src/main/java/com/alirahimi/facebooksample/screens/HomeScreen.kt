package com.alirahimi.facebooksample.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alirahimi.facebooksample.data.Dessert
import com.alirahimi.facebooksample.data.Fruit

@Composable
fun HomeScreen(navController: NavController) {

    val desserts = remember {
        mutableStateOf(Dessert.getAllDesserts())
    }

    val fruits = remember {
        mutableStateOf(Fruit.getAllFruits())
    }

    val pageSize = 5
    var currentPage = 0

    LazyColumn(
        modifier = Modifier
            .background(Color(0xffeeeeee))
    ) {
        val dessertSize = desserts.value.size

        while (dessertSize > currentPage * pageSize) {

            val dessertFrom = currentPage * pageSize
            var dessertTo = (currentPage + 1) * pageSize

            if (dessertSize < dessertTo) dessertTo = dessertSize
            val nextDesserts = desserts.value.subList(dessertFrom, dessertTo)

            items(nextDesserts) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(Color.White)
                        .clickable { }
                ) {

                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(4.dp)
                    )

                    Text(
                        text = it.desc,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(4.dp)
                    )

                    Image(
                        painter = painterResource(id = it.resId),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )

                }
            }

            val fruitFrom = currentPage * pageSize
            var fruitTo = (currentPage + 1) * pageSize

            if (fruits.value.size < fruitTo) fruitTo = fruits.value.size

            val nextFruits = fruits.value.subList(fruitFrom, fruitTo)

            item {
                LazyRow(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .height(250.dp)
                ) {
                    items(nextFruits) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(250.dp)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .clickable { }
                        ) {
                            Image(
                                painter = painterResource(id = it.resId),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillHeight
                            )

                            Text(
                                text = it.name,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xaaffffff))
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }

            currentPage++
        }
    }
}