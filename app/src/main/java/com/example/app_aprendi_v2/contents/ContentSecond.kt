package com.example.app_aprendi_v2.contents

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_aprendi_v2.R

@Composable
fun ContentSecond(navController: NavController) {
    val coursesTwo = listOf(
        Pair("", painterResource(id = R.drawable.logo_inform_tica_b_sica)),
        Pair("Em Breve", painterResource(id = R.drawable.none)),
        Pair("Em Breve", painterResource(id = R.drawable.none)),
        Pair("Em Breve", painterResource(id = R.drawable.none)),
        Pair("Em Breve", painterResource(id = R.drawable.none))
    )

    var clicked by remember { mutableStateOf(false) }


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            coursesTwo.forEachIndexed { index, course ->
                val (title, painter) = course

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
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
                            .size(180.dp)
                            .clip(RoundedCornerShape(70))
                            .clickable {
                                clicked = !clicked
                                when (index) {
                                    0 -> navController.navigate("details6")
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
fun SecondCoursesDetailsScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

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
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Informática Básica",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Image(
                painter = painterResource(R.drawable.logo_inform_tica_b_sica),
                contentDescription = "curse_TI",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(10.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = " • Este curso tem como foco mostrar na prática o uso das ferramentas computacionais básicas que são utilizadas nas tecnologias da informação e comunicação. " +
                        "Ao longo desta disciplina vamos utilizar ferramentas como planilhas eletrônicas, " +
                        "processadores de texto, softwares de apresentação, além de ferramentas de acesso a internet, " +
                        "como navegadores",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 15.sp
                ),
                color = Color.White,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))

            val annotatedText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.White
                    )
                ) {
                    append(" Para ter acesso ao seu curso ")
                }
                pushStringAnnotation(
                    tag = "URL",
                    annotation = "https://mundi.ifsul.edu.br/portal/informatica-basica.php"
                )
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Clique Aqui")
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
        }
    }
}