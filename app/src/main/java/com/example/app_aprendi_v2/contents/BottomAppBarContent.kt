package com.example.app_aprendi_v2.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomAppBarContent(navController: NavController) {

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(93.dp),
        containerColor = (Color(0xFF03C40A))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
        ) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(Icons.Filled.Star, contentDescription = "home")
            }
            Text(text = "Destaques",)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
        ) {
            IconButton(onClick = { navController.navigate("register") }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Register")
            }
            Text(text = "Conta",)
        }
    }
}