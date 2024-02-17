package pl.szymon.starships.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import pl.szymon.starships.api.ApiService
import pl.szymon.starships.models.StarShip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun StarshipDetailsScreen(id: Int, navController: NavHostController?) {
    Scaffold(topBar = {
        AppBar(
            title = "Starship details",
            imageVector = Icons.Filled.ArrowBack
        ) {
            navController?.navigateUp()
        }
    }) {

        Column(modifier = Modifier.padding(it)) {
            val isLoading = remember { mutableStateOf(true) }
            val itemsState = remember { mutableStateOf<StarShip?>(null) }

            LaunchedEffect(Unit) {
                val item = withContext(Dispatchers.IO) {
                    ApiService.getItemFromApi(id)
                }
                itemsState.value = item
                isLoading.value = false
            }

            if (isLoading.value) {
                LoadingScreen()
            } else {
                if (itemsState.value == null) {
                    return@Column
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StarshipImage(125.dp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        DetailsText(itemsState.value!!)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailsText(starShip: StarShip) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 0.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Model: ")
                }
                append(starShip.name)
            },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Manufacturer: ")
                }
                append(starShip.manufacturer)
            },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Crew: ")
                }
                append(starShip.crew)
            },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Passengers: ")
                }
                append(starShip.passengers)
            },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Cargo capacity: ")
                }
                append(starShip.cargoCapacity)
            },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Max speed: ")
                }
                append(starShip.maxSpeed)
            },
            style = MaterialTheme.typography.titleMedium
        )
    }
}