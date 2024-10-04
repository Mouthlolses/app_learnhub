package com.example.app_aprendi_v2

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(onLoginClick: (String, String) -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current  // Contexto para acessar o banco de dados
    val scope = rememberCoroutineScope()

    Column(              // Coluna para criação e existilização do layout da pagina de login

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login", style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                isLoading = true
                scope.launch {
                    val isLoggedIn = loginUser(context, email.text, password.text)  // Chama função de login
                    if (isLoggedIn) {
                        onLoginClick(email.text, password.text)  // Navega para a próxima tela
                    } else {
                        isLoading = false  // Se falhar, habilita o botão novamente
                        // Mostrar uma mensagem de erro (pode usar um Snackbar ou Toast)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(16.dp))
            } else {
                Text("Entrar")
            }
        }
    }
}


//Referente ao Banco de Dados:

@Entity(tableName = "user")       //Representa a tabela no banco de dados. criando uma classe para armazenar os dados de login do usuário (por exemplo, email e senha).
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val password: String
)

@Dao                //Define os métodos que você pode usar para interagir com o banco de dados. Ele será usado para inserir e consultar os dados de login.
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUser(email: String, password: String): User?
}

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


//Inserir novo usuário (Cadastro):

// Função de login do usuário, chamada na LoginScreen
suspend fun loginUser(context: Context, email: String, password: String): Boolean {
    val userDao = AppDatabase.getDatabase(context).userDao()
    val user = userDao.getUser(email, password)
    return user != null  // Retorna verdadeiro se o usuário for encontrado
}

// Função de registro de usuário (caso queira adicionar um botão para "Registrar")
suspend fun registerUser(context: Context, email: String, password: String) {
    val userDao = AppDatabase.getDatabase(context).userDao()
    val user = User(email = email, password = password)
    userDao.insertUser(user)
}




@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen {
            _, _ ->
    }
}