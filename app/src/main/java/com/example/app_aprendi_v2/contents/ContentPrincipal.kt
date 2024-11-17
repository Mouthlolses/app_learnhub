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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun ContentPrincipal(navController: NavController) {
    val courses = listOf(
        Pair("Disponivel", painterResource(id = R.drawable.fundamentosdeti)),
        Pair("Disponivel", painterResource(id = R.drawable.modelagemdedados)),
        Pair("Disponivel", painterResource(id = R.drawable.python)),
        Pair("Disponivel", painterResource(id = R.drawable.bancodedados)),
        Pair("Disponivel", painterResource(id = R.drawable.introdu__o___programa__o_orientada_a_objetos__poo_))
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
                                clicked = !clicked
                                when (index) {
                                    0 -> navController.navigate("details")
                                    1 -> navController.navigate("details2")
                                    2 -> navController.navigate("details3")
                                    3 -> navController.navigate("details4")
                                    4 -> navController.navigate("details5")
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
            text = "Fundamentos de TI:" +
                    " Hardware e Software",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.fundamentosdeti),
            contentDescription = "curse_TI",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(10.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "• O objetivo deste curso é apresentar os conceitos básicos da informática, " +
                        "os componentes dos computadores, os sistemas lógicos e as principais funções de armazenamento" +
                        " e processamento que envolvem o poder computacional.",
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

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Voltar",
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun CourseDetailsScreen2(navController: NavController) {
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
    )
    {
     Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Modelagem de Dados",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.padding(15.dp))

        Image(
            painter = painterResource(R.drawable.modelagemdedados),
            contentDescription = "Modelagem de Dados",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(10.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "• O assunto modelagem de dados é bem relevante, principalmente, nos dias de hoje," +
                        "pois o armazenamento e a administração de dados tornaram-se essenciais com a" +
                        " evolução tecnológica ocorrida nos últimos anos.",
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
                    annotation = "https://www.ev.org.br/cursos/modelagem-de-dados"
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

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Voltar",
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun CourseDetailsScreen3(navController: NavController) {
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
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Linguagem de Programação Python - Básico",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.padding(15.dp))

        Image(
            painter = painterResource(id = R.drawable.python),
            contentDescription = "Python",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(10.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "•  A linguagem de programação Python vem crescendo muito nos últimos anos devido à sua simplicidade e," +
                        " principalmente, à sua grande compatibilidade, pois funciona bem na maioria dos sistemas operacionais.",
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
                    annotation = "https://www.ev.org.br/cursos/linguagem-de-programacao-python-basico"
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

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Voltar",
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun CourseDetailsScreen4(navController: NavController) {
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
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Banco de Dados",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.padding(15.dp))

        Image(
            painter = painterResource(id = R.drawable.bancodedados),
            contentDescription = "BancoDeDados",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(10.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "⚠ Atenção: Trilha de Conhecimento\n" +
                        "\n• Um Banco de Dados é uma coleção organizada de informações estruturadas," +
                        " normalmente armazenadas eletronicamente em um sistema de computador." +
                        " E por falar em informação, todos sabemos que ela tem um peso essencial hoje em dia," +
                        " pois auxilia pessoas e empresas na tomada de decisões.",
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
                    annotation = "https://www.ev.org.br/trilhas-de-conhecimento/banco-de-dados"
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

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Voltar",
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun CourseDetailsScreen5(navController: NavController){
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
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Introdução à Programação Orientada a Objetos (POO)",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.padding(15.dp))

        Image(
            painter = painterResource(id = R.drawable.introdu__o___programa__o_orientada_a_objetos__poo_),
            contentDescription = "Introdução à Programação Orientada a Objetos (POO)",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(10.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "• O objetivo deste curso é apresentar o conceito da programação orientada a objetos e as diferenças entre a programação estruturada e a POO," +
                        " que surgiu da necessidade de deixar a programação mais fácil para os desenvolvedores de software.",
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
                    annotation = "https://www.ev.org.br/cursos/introducao-a-programacao-orientada-a-objetos-poo"
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

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Voltar",
                    color = Color.White,
                )
            }
        }
    }
}