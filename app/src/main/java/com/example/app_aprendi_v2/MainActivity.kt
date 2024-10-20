package com.example.app_aprendi_v2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_aprendi_v2.data.local.AppDatabase
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable("details") { CourseDetailsScreen(navController) }
                composable("register") { RegisterScreen(navController) }
            }
        }
    }


    @Composable
    fun HomeScreen(navController: NavController) {
        val scrollState = rememberScrollState()

        Scaffold(
            bottomBar = { BottomAppBarContent(navController) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1F001F))
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
                    Spacer(modifier = Modifier.height(60.dp))
                    ContentPrincipal(navController)
                    Spacer(modifier = Modifier.height((40.dp)))
                    ContentSecond()
                    Spacer(modifier = Modifier.height((40.dp)))
                    ContentThird()
                    Spacer(modifier = Modifier.height((40.dp)))
                    ContentFour()
                }
            }
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(17.7f / 9f),
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
            Pair("Disponível!", painterResource(id = R.drawable.fundamentosdeti)),
            Pair("Em Breve", painterResource(id = R.drawable.modelagemdedados)),
            Pair("Em Breve", painterResource(id = R.drawable.python)),
            Pair("Em Breve", painterResource(id = R.drawable.humantech)),
            Pair("Em Breve", painterResource(id = R.drawable.sistemas))
        )
        var clicked by remember { mutableStateOf(false) }

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(0.dp), // adjust the padding
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                courses.forEachIndexed { index, course ->
                    val (title, painter) = course

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        // Adiciona a descrição acima da imagem
                        Text(
                            text = title,
                            color = Color.White, // Altere a cor para o valor desejado
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .padding(bottom = 0.dp)  // Espaçamento abaixo do texto
                        )
                        Image(
                            painter = painter,
                            contentDescription = title,
                            modifier = Modifier
                                .size(170.dp)
                                .clip(RoundedCornerShape(70))
                                .clickable {
                                    if (index == 0) {
                                        clicked = !clicked
                                        navController.navigate("details")
                                    }
                                }
                                .padding(23.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun CourseDetailsScreen(navController: NavController) {
        val context = LocalContext.current
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1F001F))
                .fillMaxWidth()
                .padding(0.dp)
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Fundamentos de TI:" +
                        " Hardware e Software",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier
                    .padding(12.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.cursos_ti),
                contentDescription = "curse_TI",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column (
                modifier = Modifier
                    .verticalScroll(scrollState)
            ){
                Text(
                    text = "O objetivo deste curso é apresentar os conceitos básicos da informática, " +
                            "os componentes dos computadores, os sistemas lógicos e as principais funções de armazenamento" +
                            " e processamento que envolvem o poder computacional.",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 18.sp
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(3.dp)
                )

                Spacer(modifier = Modifier.height(50.dp))


                    val annotatedText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White
                        )
                    ) {
                        append("Para ter acesso ao seu curso ")
                    }
                    pushStringAnnotation(
                        tag = "URL",
                        annotation = "https://www.ev.org.br/cursos/fundamentos-de-ti-hardware-e-software"
                    )
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Clique aqui")
                    }
                    pop()
                }

                ClickableText(
                    text = annotatedText,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 16.sp
                    ),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(
                            tag = "URL",
                            start = offset,
                            end = offset
                        )
                            .firstOrNull()?.let { annotation ->
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                                context.startActivity(intent)
                            }
                    },
                    modifier = Modifier
                        .padding(1.dp)
                )

                Spacer(modifier = Modifier.height(50.dp))

                Button(onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ){
                    Text(text = "Voltar",
                    color = Color.White,
                    )
                }
            }
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


    @Composable
    fun ContentThird() {
        val coursesThird = listOf(
            Pair("Em Breve", painterResource(id = R.drawable.humantech)),
            Pair("Em Breve", painterResource(id = R.drawable.modelagemdedados)),
            Pair("Em Breve", painterResource(id = R.drawable.sistemas)),
            Pair("Em Breve", painterResource(id = R.drawable.fundamentosdeti)),
            Pair("Em Breve", painterResource(id = R.drawable.python))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            coursesThird.forEach { (title, painter) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(bottom = 0.dp)
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


    @Composable
    fun ContentFour() {
        val coursesFour = listOf(
            Pair("Em Breve", painterResource(id = R.drawable.python)),
            Pair("Em Breve", painterResource(id = R.drawable.fundamentosdeti)),
            Pair("Em Breve", painterResource(id = R.drawable.humantech)),
            Pair("Em Breve", painterResource(id = R.drawable.sistemas)),
            Pair("Em Breve", painterResource(id = R.drawable.modelagemdedados))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            coursesFour.forEach { (title, painter) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                    text = title,
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(bottom = 0.dp)
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



    @Composable
    fun BottomAppBarContent(navController: NavController) {

        BottomAppBar(
            modifier = Modifier.height(89.dp),
            containerColor = (Color(0xFF01BD09))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(Icons.Filled.Star, contentDescription = "home")
                }
                Text(text = "Destaques", color = Color.Black, modifier = Modifier.padding(0.dp))
            }
            Spacer(modifier = Modifier.width(120.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                IconButton(onClick = { navController.navigate("register") }) {
                    Icon(Icons.Filled.AccountCircle, contentDescription = "Register")
                }
                Text(text = "Conta", color = Color.Black, modifier = Modifier.padding(0.dp))
            }
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun HomeScreenPreview() {
        val navController = rememberNavController() // Create NavController for Preview
        HomeScreen(navController) // Pass navController as a parameter
    }
}
