package com.example.app_aprendi_v2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_aprendi_v2.data.User
import com.example.app_aprendi_v2.data.local.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome")}
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password")},
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick =  {
            val user = User(name = name, email = email, password = password)
            CoroutineScope(Dispatchers.IO).launch {
               try{
                   database.userDao().insert(user)
               } catch (e: Exception) {
                   e.printStackTrace()
               }
            }
            navController.popBackStack()
        }) {
            Text("Register")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    // Criando uma versão mockada do NavController
    val mockNavController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Nome") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* No-op */ }) {
            Text("Register")
        }
    }
}