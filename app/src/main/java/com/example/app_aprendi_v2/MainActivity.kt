package com.example.app_aprendi_v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_aprendi_v2.data.local.AppDatabase
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private lateinit var database: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()         //Add navController for navigation in app
             NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable("details") { CourseDetailsScreen() }
                 composable("register") { RegisterScreen() }
                 }
            }
        }
    }


    @Composable
    fun HomeScreen(navController: NavController) {
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
                    .padding(start = 0.dp, end = 4.dp) // Adjustment padding
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
            ContentPrincipal(navController)
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
                delay(4000)  // Time of execution of the banner
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
                contentPadding = PaddingValues(0.dp), // remove padding intern if necessary
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


    @Composable
    fun ContentPrincipal(navController: NavController) {
        val courses = listOf(
            painterResource(id = R.drawable.fundamentosdeti),
            painterResource(id = R.drawable.modelagemdedados),
            painterResource(id = R.drawable.python),
            painterResource(id = R.drawable.humantech),
            painterResource(id = R.drawable.sistemas)
        )
        var clicked by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(0.dp), // adjust the padding
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            courses.forEachIndexed { index, painter ->
                val imageSize by animateDpAsState(
                    targetValue = if (clicked && index == 0) 170.dp else 170.dp,
                    label = "Image Size Animation"
                )

                val backgroundColor by animateColorAsState(
                    targetValue = if (clicked && index == 0) Color(0xFF1F001F) else Color.Transparent,
                    label = "Image backgroundColor Animation"
                )

                Box(
                    modifier = Modifier
                        .size(imageSize)
                        .clip(RoundedCornerShape(70))
                        .background(backgroundColor)
                        .clickable {
                            if (index == 0) {
                                clicked = !clicked
                                navController.navigate("details")
                            }
                        }
                        .padding(23.dp)
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }

    @Composable
    fun CourseDetailsScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1F001F))
                .fillMaxWidth()
                .padding(0.dp)
        ) {
            Text(text = "Detalhes do Curso", style = MaterialTheme.typography.headlineMedium)
        }
    }


    @Composable
    fun ContentSecond() {
        val coursesTwo = listOf(
            Pair("Em Breve", painterResource(id = R.drawable.sistemas)),
            Pair("Em Breve", painterResource(id = R.drawable.python)),
            Pair("Em Breve", painterResource(id = R.drawable.fundamentosdeti)),
            Pair("Em Breve", painterResource(id = R.drawable.modelagemdedados)),
            Pair("Em Breve", painterResource(id = R.drawable.humantech))
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
                        style = MaterialTheme.typography.bodySmall, // style of text
                        modifier = Modifier
                            .padding(bottom = 0.dp) // spacing below text
                    )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(170.dp)
                            .clip(RoundedCornerShape(70))
                            .clickable { println("Image2 Click") }
                            .padding(23.dp)
                    )
                }
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController() // Create NavController for Preview
    HomeScreen(navController) // Pass navController as a parameter
}

