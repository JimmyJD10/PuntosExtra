package com.example.jetpackcomposestate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposestate.ui.theme.JetpackComposeStateTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStateTheme {
                // Puedes establecer el contenido aquí si lo necesitas
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCounterApp() {
    CounterApp()
}

@Preview(showBackground = true)
@Composable
fun PreviewCounterAppWithMessage() {
    CounterAppWithMessage()
}

@Preview(showBackground = true)
@Composable
fun PreviewCounterAppSaveable() {
    CounterAppSaveable()
}

@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    Greeting(name = "Usuario")
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieList() {
    MovieList()
}

@Preview(showBackground = true)
@Composable
fun PreviewCounter() {
    Counter()
}

@Preview(showBackground = true)
@Composable
fun PreviewNavigationExample() {
    NavigationExample()
}

@Preview(showBackground = true)
@Composable
fun PreviewCompleteApp() {
    CompleteApp()
}

@Preview(showBackground = true)
@Composable
fun PreviewAdvancedStateExample() {
    AdvancedStateExample()
}

@Preview(showBackground = true)
@Composable
fun PreviewAsyncOperationExample() {
    AsyncOperationExample()
}

@Composable
fun CounterApp(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Has clickeado el botón $count veces",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("Haz clic en mí")
        }
    }
}

@Composable
fun CounterAppWithMessage(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    var showMessage by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = "Has clickeado el botón $count veces",
            style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            count++
            showMessage = true
        }) {
            Text("Haz clic en mí")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (showMessage) {
            Text(
                text = "¡Botón clickeado!",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun CounterAppSaveable(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    var showMessage by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Has clickeado el botón $count veces",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            count++
            showMessage = true
        }) {
            Text("Haz clic en mí")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (showMessage) {
            Text(
                text = "¡Botón clickeado!",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var greetingMessage by rememberSaveable { mutableStateOf("Hola, $name!") }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = greetingMessage,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { greetingMessage = "¡Bienvenido, $name!" }) {
            Text("Cambiar saludo")
        }
    }
}

@Composable
fun MovieList() {
    val movies = listOf("Película 1", "Película 2", "Película 3")
    var selectedMovie by rememberSaveable { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        movies.forEach { movie ->
            Text(
                text = movie,
                modifier = Modifier
                    .clickable { selectedMovie = movie }
                    .padding(8.dp),
                style = if (selectedMovie == movie)
                    MaterialTheme.typography.bodyLarge.copy
                        (fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun Counter() {
    var count by rememberSaveable { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Contador: $count")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("Incrementar")
        }
        Button(onClick = { count-- }) {
            Text("Decrementar")
        }
    }
}

@Composable
fun NavigationExample() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details") { DetailScreen() }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    var count by rememberSaveable { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Pantalla de Inicio")
        Text(text = "Contador: $count")
        Button(onClick = { navController.navigate("details") }) {
            Text("Ir a Detalles")
        }
    }
}

@Composable
fun DetailScreen() {
    Text(text = "Pantalla de Detalles")
}

@Composable
fun CompleteApp() {
    var count by rememberSaveable { mutableStateOf(0) }
    var movieName by rememberSaveable { mutableStateOf("") }
    var movies by rememberSaveable { mutableStateOf(listOf<String>()) }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Contador: $count")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = movieName,
            onValueChange = { movieName = it },
            label = { Text("Nombre de la película") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (movieName.isNotBlank()) {
                movies = movies + movieName
                movieName = ""
                count++ } }) {
            Text("Añadir película") }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(movies) { movie ->
                Text(text = movie)
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun AdvancedStateExample() {
    var count by rememberSaveable { mutableStateOf(0) }
    val doubleCount by derivedStateOf { count * 2 }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Contador: $count")
        Text(text = "Contador Doble: $doubleCount")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("Incrementar")
        }
    }
}

@Composable
fun AsyncOperationExample() {
    var data by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        loading = true
        data = fetchData()
        loading = false
    }

    Column(modifier = Modifier.padding(16.dp)) {
        if (loading) {
            CircularProgressIndicator()
        } else {
            Text(text = data ?: "No se ha cargado ningún dato.")
        }
    }
}

suspend fun fetchData(): String {
    delay(2000) // Simula una operación de red
    return "Datos cargados"
}
