package com.shaif.screenmemory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaif.screenmemory.ui.theme.ScreenMemoryTheme
import androidx.compose.material3.ExperimentalMaterial3Api

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ScreenMemoryTheme {
                ScreenMemoryApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMemoryApp() {

    var searchText by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "ScreenMemory",
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Your screenshots, understood.",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "What are you looking for?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,

                leadingIcon = {
                    Text(
                        text = "🔍",
                        fontSize = 20.sp
                    )
                },

                placeholder = {
                    Text(
                        text = "Search your screenshots..."
                    )
                },

                shape = RoundedCornerShape(16.dp)
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = "Your Memory",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "0 screenshots processed",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                TextButton(
                    onClick = {
                        // Will be implemented later
                    }
                ) {

                    Text(
                        text = "View all"
                    )

                    Spacer(
                        modifier = Modifier.width(4.dp)
                    )

                    Text(
                        text = "→",
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            CategoryGrid()
        }
    }
}

data class Category(
    val title: String,
    val description: String,
    val symbol: String
)

@Composable
fun CategoryGrid() {

    val categories = listOf(

        Category(
            title = "Shopping",
            description = "Products & prices",
            symbol = "🛍️"
        ),

        Category(
            title = "Study",
            description = "Notes & learning",
            symbol = "📚"
        ),

        Category(
            title = "Receipts",
            description = "Bills & payments",
            symbol = "🧾"
        ),

        Category(
            title = "Travel",
            description = "Places & trips",
            symbol = "✈️"
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),

        modifier = Modifier.fillMaxSize(),

        contentPadding = PaddingValues(
            bottom = 24.dp
        ),

        horizontalArrangement = Arrangement.spacedBy(12.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(categories) { category ->

            CategoryCard(
                category = category
            )
        }
    }
}

@Composable
fun CategoryCard(
    category: Category
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),

        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = category.symbol,
                fontSize = 32.sp
            )

            Column {

                Text(
                    text = category.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}