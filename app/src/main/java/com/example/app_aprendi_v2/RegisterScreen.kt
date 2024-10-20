package com.example.app_aprendi_v2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

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
            label = { Text("Nome") }
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
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Button(onClick = {
                if (name.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                    isLoading = true
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val user = User(name = name, email = email, password = password)
                            database.userDao().insert(user)
                            navController.popBackStack()
                        } catch (e: Exception) {
                            errorMessage = "Erro ao registrar: ${e.message}"
                            isLoading = false
                        }
                    }
                } else {
                    errorMessage = "Por favor, preencha todos os campos."
                }
            }) {
                Text("Register")
            }
        }

        // Exibir mensagem de erro
        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )
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