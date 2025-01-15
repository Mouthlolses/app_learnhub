package com.example.app_aprendi_v2

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_aprendi_v2.contents.Banner
import com.example.app_aprendi_v2.contents.ContentFour
import com.example.app_aprendi_v2.contents.ContentPrincipal
import com.example.app_aprendi_v2.contents.ContentSecond
import com.example.app_aprendi_v2.contents.ContentThird
import com.example.app_aprendi_v2.contents.CourseDetailsScreen
import com.example.app_aprendi_v2.contents.CourseDetailsScreen2
import com.example.app_aprendi_v2.contents.CourseDetailsScreen3
import com.example.app_aprendi_v2.contents.CourseDetailsScreen4
import com.example.app_aprendi_v2.contents.CourseDetailsScreen5
import com.example.app_aprendi_v2.contents.SecondCoursesDetailsScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable("details") { CourseDetailsScreen(navController) }
                composable("details2") { CourseDetailsScreen2(navController) }
                composable("details3") { CourseDetailsScreen3(navController) }
                composable("details4") { CourseDetailsScreen4(navController) }
                composable("details5") { CourseDetailsScreen5(navController) }
                composable("details6") { SecondCoursesDetailsScreen(navController) }
            }
        }
    }


    @Composable
    fun HomeScreen(navController: NavController) {
        val scrollState = rememberScrollState()

        Scaffold()
        { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF1B001B), Color(0xFF410566)),
                            start = Offset(0f, 0f),
                            end = Offset(1000f, 1000f)
                        )
                    )
                    .padding(paddingValues)
                    .padding(0.dp),
            ) {
                Row(
                    modifier = Modifier
                        .width(160.dp)
                        .height(50.dp)
                        .padding(start = 0.dp, end = 10.dp) // Adjustment padding
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clipToBounds()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logomarcagrand),
                            contentDescription = null,
                            modifier = Modifier
                                .size(500.dp)
                                .align(Alignment.Center),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(0.dp)
                ) {
                    Banner()
                    Spacer(modifier = Modifier.height(40.dp))
                    ContentPrincipal(navController)
                    Spacer(modifier = Modifier.height(40.dp))
                    ContentSecond(navController)
                    Spacer(modifier = Modifier.height(40.dp))
                    ContentThird()
                    Spacer(modifier = Modifier.height(40.dp))
                    ContentFour()
                }
            }
        }
    }




    @Preview("default")
    @Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Preview("large font", fontScale = 2f)
    @Preview(showBackground = true)
    @Composable
    fun HomeScreenPreview() {
        val navController = rememberNavController() // Create NavController for Preview
        HomeScreen(navController) // Pass navController as a parameter
    }
}


