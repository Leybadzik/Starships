package pl.szymon.starships.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pl.szymon.starships.R

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Text(text = "Fetching data..")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, imageVector: ImageVector? = null, iconClickAction: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            if (imageVector != null) {
                IconButton(onClick = { iconClickAction.invoke() }) {
                    Icon(imageVector = imageVector, contentDescription = "")
                }
            }
        }
    )
}

@Composable
fun StarshipImage(size: Dp) {
    Image(
        painter = painterResource(id = R.drawable.ic_starship),
        contentDescription = "",
        modifier = Modifier
            .size(size)
            .padding(10.dp),
        contentScale = ContentScale.Crop
    )
}