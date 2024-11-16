package com.example.app_aprendi_v2.contents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.app_aprendi_v2.R
import kotlinx.coroutines.delay

@Composable
fun Banner() {
    val images = listOf(
        painterResource(id = R.drawable.banner_de_cursos_de_tecnologia_de_nome_learnhub__descri__o_em_portugues)
    )
    var currentImageIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(4000)  // Time of execution of the banner
            currentImageIndex = (currentImageIndex + 1) % images.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(17.7f / 9f),
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(0.dp), // remove padding intern
            horizontalArrangement = Arrangement.Center
        ) {
            items(images.size) { index ->
                // check animation visibility of the banner
                AnimatedVisibility(
                    visible = currentImageIndex == index,
                    enter = fadeIn() + slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }),
                    exit = fadeOut() + slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth })
                ) {
                    Image(
                        painter = images[index],
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(30))
                            .aspectRatio(19f / 9.7f)
                            .padding(15.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}