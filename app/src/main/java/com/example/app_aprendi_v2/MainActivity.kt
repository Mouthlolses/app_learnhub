package com.example.app_aprendi_v2

import android.os.Bundle
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeScreen()
        }
    }


    @Composable
    fun HomeScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1F001F))
                .padding(0.dp),
        ) {

            Spacer(modifier = Modifier.height(0.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, end = 4.dp) // Ajuste o padding se necessário
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logomarcagrand),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Crop
                )
            }
            Banner()
            Spacer(modifier = Modifier.height(60.dp))
            ContentPrincipal()
            Spacer(modifier = Modifier.height((40.dp)))
            ContentSecond()
        }
    }


    @Composable
    fun Banner() {
        val images = listOf(
            painterResource(id = R.drawable.bannerone),
            painterResource(id = R.drawable.bannertree),
            painterResource(id = R.drawable.bannerfour)
        )
        var currentImageIndex by remember { mutableIntStateOf(0) }

        LaunchedEffect(Unit) {
            while (true) {
                delay(4000)  // Tempo de exibição de cada imagem em milissegundos
                currentImageIndex = (currentImageIndex + 1) % images.size
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(17.7f / 9f),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(0.dp), // Remove o padding interno se necessário
                horizontalArrangement = Arrangement.Center
            ) {
                items(images.size) { index ->
                    // Verifica se o índice corresponde à imagem atual
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


    @Composable
    fun ContentPrincipal() {
        val courses = listOf(
            painterResource(id = R.drawable.fundamentosdeti),
            painterResource(id = R.drawable.modelagemdedados),
            painterResource(id = R.drawable.python),
            painterResource(id = R.drawable.humantech),
            painterResource(id = R.drawable.sistemas)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(0.dp), // Ajuste o padding conforme necessário
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            courses.forEach { painter ->
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(170.dp)
                        .clip(RoundedCornerShape(70))
                        .clickable { println("Imagem Clicada") }
                        .padding(23.dp)
                )
            }
        }
    }


    @Composable
    fun ContentSecond() {
        val coursesTwo = listOf(
            Pair("Em Breve", painterResource(id = R.drawable.sistemas)),
            Pair("Em Breve",painterResource(id = R.drawable.python)),
            Pair("Em Breve",painterResource(id = R.drawable.fundamentosdeti)),
            Pair("Em Breve",painterResource(id = R.drawable.modelagemdedados)),
            Pair("Em Breve",painterResource(id = R.drawable.humantech))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            coursesTwo.forEach { (title, painter) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall, // Estilo do texto
                        modifier = Modifier
                            .padding(bottom = 0.dp) // Espaçamento abaixo do texto
                    )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(170.dp)
                            .clip(RoundedCornerShape(70))
                            .clickable { println("Image2 Clicada") }
                            .padding(23.dp)
                    )
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun HomeScreenPreview() {
        HomeScreen()
    }
}