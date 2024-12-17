package com.example.app_aprendi_v2.contents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.app_aprendi_v2.R

@Composable
fun ContentFour() {
    val coursesFour = listOf(
        Pair("Em Breve", painterResource(id = R.drawable.none)),
        Pair("Em Breve", painterResource(id = R.drawable.none)),
        Pair("Em Breve", painterResource(id = R.drawable.none)),
        Pair("Em Breve", painterResource(id = R.drawable.none)),
        Pair("Em Breve", painterResource(id = R.drawable.none))
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