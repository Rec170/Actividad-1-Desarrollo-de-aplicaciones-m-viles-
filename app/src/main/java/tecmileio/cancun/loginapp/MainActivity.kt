package tecmileio.cancun.loginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import tecmileio.cancun.loginapp.ui.theme.LoginAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginAppTheme {
                Principal()
            }
        }
    }
}

@Composable
fun Principal() {
    // Estado para controlar qué pantalla mostrar: "login", "registro", "home"
    var pantallaActual by remember { mutableStateOf("login") }

    when (pantallaActual) {
        "login" -> PantallaLogin(
            almostrarRegistro = { pantallaActual = "registro" },
            onLoginSuccess = { pantallaActual = "home" }
        )
        "registro" -> PantallaRegistro(
            almostrarLogin = { pantallaActual = "login" },
            onRegistroSuccess = { pantallaActual = "home" }
        )
        "home" -> PantallaHome(
            onLogout = { pantallaActual = "login" }
        )
    }
}

@Composable
fun PantallaLogin(almostrarRegistro: () -> Unit, onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo de la app",
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )

            OutlinedTextField(
                value = username,
                label = { Text("Usuario") },
                onValueChange = { textoNuevo ->
                    username = textoNuevo
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = password,
                label = { Text("Contraseña") },
                onValueChange = { textoNuevo ->
                    password = textoNuevo
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Button(
                onClick = onLoginSuccess,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar Sesión")
            }
            TextButton(
                onClick = almostrarRegistro
            ) {
                Text("¿No tienes una cuenta? Regístrate")
            }
        }
    }
}

@Composable
fun PantallaRegistro(almostrarLogin: () -> Unit, onRegistroSuccess: () -> Unit) {
    var name by remember { mutableStateOf(value = "") }
    var username by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo de la app",
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
            OutlinedTextField(
                value = name,
                label = { Text("Nombre Completo") },
                onValueChange = { textoNuevo ->
                    name = textoNuevo
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = username,
                label = { Text("Usuario") },
                onValueChange = { textoNuevo ->
                    username = textoNuevo
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = password,
                label = { Text("Contraseña") },
                onValueChange = { textoNuevo ->
                    password = textoNuevo
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Button(
                onClick = onRegistroSuccess,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarme")
            }
            TextButton(
                onClick = almostrarLogin
            ) {
                Text("¿Ya tienes una cuenta? Iniciar Sesión")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaHome(onLogout: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Inicio") },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_lock_power_off),
                            contentDescription = "Cerrar sesión"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { espacioInterior ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(espacioInterior),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "¡Bienvenido a la pantalla Home!",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}