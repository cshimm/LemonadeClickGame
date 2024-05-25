package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.AppTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var guess by remember { mutableIntStateOf(0) }
    var answer by remember { mutableIntStateOf(0) }
    var correct by remember { mutableStateOf(false) }
    fun handleImageClicked(id: Int) {
        guess = id
        correct = guess == answer
    }

    fun handleNewGame() {
        guess = 0
        answer = Random.nextInt(1, 5)
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .wrapContentSize()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f),
                    ) {
                        LemonImage(
                            drawableResourceId = R.drawable.lemon_tree,
                            contentDescriptionResourceId = R.string.lemon_tree_content_description,
                            modifier = Modifier.weight(1f),
                            onImageClick = { handleImageClicked(1) })
                        LemonImage(
                            drawableResourceId = R.drawable.lemon_drink,
                            contentDescriptionResourceId = R.string.lemon_tree_content_description,
                            modifier = Modifier.weight(1f),
                            onImageClick = { handleImageClicked(2) })
                    }
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f)
                    ) {
                        LemonImage(
                            drawableResourceId = R.drawable.lemon_squeeze,
                            contentDescriptionResourceId = R.string.lemon_tree_content_description,
                            modifier = Modifier.weight(1f),
                            onImageClick = { handleImageClicked(3) })
                        LemonImage(
                            drawableResourceId = R.drawable.lemon_restart,
                            contentDescriptionResourceId = R.string.lemon_tree_content_description,
                            modifier = Modifier.weight(1f),
                            onImageClick = { handleImageClicked(4) })
                    }
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .weight(1f)
                    ) {
                        val imageToClick = when (answer) {
                            1 -> "Tree"
                            2 -> "Lemonade Glass"
                            3 -> "Lemon"
                            4 -> "Empty Glass"
                            else -> ""
                        }
                        if (answer > 0) {
                            Text(
                                text = "Click the $imageToClick",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            val resultText = if (correct) "Correct!" else "Incorrect :("
                            if (guess > 0) {
                                Text(
                                    text = resultText,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }
                        }
                        Button(
                            onClick = { handleNewGame() },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "New Game")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LemonImage(
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId),
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_image_width))
                        .height(dimensionResource(R.dimen.button_image_height))
                        .padding(dimensionResource(R.dimen.button_interior_padding))
                )
            }
        }
    }
}

@Preview
@Composable
fun LemonPreview() {
    AppTheme() {
        LemonadeApp()
    }
}