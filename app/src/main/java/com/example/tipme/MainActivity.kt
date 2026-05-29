package com.example.tipme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                TipMeApp()
            }
        }
    }
}

@Composable
fun TipMeApp() {

    // ESTADOS
    var montoCuenta by remember { mutableStateOf("") }
    var porcentajePropina by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    // LAYOUT PRINCIPAL
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.tipme_logo),
            contentDescription = "Logo TipMe",
            modifier = Modifier.size(140.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TITULO
        Text(
            text = "TipMe",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Calculadora de Propinas",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // TEXTFIELD MONTO
        OutlinedTextField(
            value = montoCuenta,
            onValueChange = {
                montoCuenta = it
            },
            label = {
                Text("Monto de la cuenta")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TEXTFIELD PROPINA
        OutlinedTextField(
            value = porcentajePropina,
            onValueChange = {
                porcentajePropina = it
            },
            label = {
                Text("Porcentaje de propina")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // BOTON
        Button(
            onClick = {

                val monto = montoCuenta.toDoubleOrNull() ?: 0.0
                val porcentaje = porcentajePropina.toDoubleOrNull() ?: 0.0

                val propina = monto * porcentaje / 100
                val total = monto + propina

                resultado =
                    "Propina: $${String.format(Locale.US, "%.2f", propina)}\n" +
                            "Total a pagar: $${String.format(Locale.US, "%.2f", total)}"
            }
        ) {

            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // RESULTADO
        Text(
            text = resultado,
            fontSize = 20.sp
        )
    }
}