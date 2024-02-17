package pl.szymon.starships.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import pl.szymon.starships.api.ApiService
import pl.szymon.starships.models.StarShip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun StarshipsScreen(navController: NavHostController?) {
    Scaffold(topBar = {
        AppBar(
            title = "Starships"
        ) {}
    }) {
        Column(modifier = Modifier.padding(it)) {
            val isLoading = remember { mutableStateOf(true) }
            val itemsState = remember { mutableStateOf<List<StarShip>>(emptyList()) }

            LaunchedEffect(Unit) {
                val items = withContext(Dispatchers.IO) {
                    ApiService.getItemsFromApi()
                }
                itemsState.value = items
                isLoading.value = false
            }

            if (isLoading.value) {
                LoadingScreen()
            } else {
                LazyColumn {
                    items(itemsState.value) { starship ->
                        StarshipRow(starship) {
                            navController?.navigate("details/${it.id}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StarshipRow(starShip: StarShip, clickAction: (StarShip) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 4.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction(starShip) },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .heightIn(min = 80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            StarshipImage(70.dp)
            RowText(starShip)
        }
    }
}

@Composable
fun RowText(starShip: StarShip) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = starShip.name, style = MaterialTheme.typography.titleLarge)
        Text(text = starShip.manufacturer, style = MaterialTheme.typography.titleSmall)
    }
}