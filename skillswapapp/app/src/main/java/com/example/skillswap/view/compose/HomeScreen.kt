
package com.example.skillswap.view.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skillswap.R
import com.example.skillswap.model.SkillUser
import com.example.skillswap.ui.theme.BeigeBackground
import com.example.skillswap.ui.theme.BrownPrimary
import com.example.skillswap.ui.theme.CardTitle
import com.example.skillswap.ui.theme.CreamSurface
import com.example.skillswap.ui.theme.DividerBeige
import com.example.skillswap.ui.theme.SearchBackground
import com.example.skillswap.ui.theme.SearchBorder
import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.ui.theme.SoftBeigeCard
import com.example.skillswap.ui.theme.StarRatingColor
import com.example.skillswap.ui.theme.TextHint
import com.example.skillswap.ui.theme.TextPrimary
import com.example.skillswap.ui.theme.TitleText
import com.example.skillswap.viewmodel.SkillSwapViewModel

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkillSwapTheme {
                SkillSwapHomeScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillSwapHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: SkillSwapViewModel = viewModel(),
    onProfileClick: () -> Unit = {},
    onConnectClick: (SkillUser) -> Unit = {}
) {
    val searchText by viewModel.searchText.collectAsState()
    val users = viewModel.filteredUsers()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = BeigeBackground,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Skill Swap",
                            color = TitleText,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Outlined.NotificationsNone,
                                contentDescription = "Notifications",
                                tint = TextPrimary
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = onProfileClick) {
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = "Profile",
                                tint = TextPrimary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = CreamSurface
                    )
                )
                HorizontalDivider(color = DividerBeige)
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BeigeBackground)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            OutlinedTextField(
                value = searchText,
                onValueChange = { viewModel.onSearchTextChange(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Search for a skill to learn...",
                        color = TextHint
                    )
                },
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = SearchBackground,
                    unfocusedContainerColor = SearchBackground,
                    focusedBorderColor = SearchBorder,
                    unfocusedBorderColor = SearchBorder,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = BrownPrimary
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrownPrimary,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Search", fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(users) { user ->
                    SkillUserCard(
                        user = user,
                        onConnectClick = { onConnectClick(user) }
                    )
                }
            }
        }
    }
}

@Composable
fun SkillUserCard(
    user: SkillUser,
    onConnectClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SoftBeigeCard),
        border = BorderStroke(2.dp, TextPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(84.dp)
                    .clip(CircleShape)
                    .border(3.dp, Color(0xFFE6B1D0), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = user.name,
                    modifier = Modifier
                        .size(78.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = user.name,
                    color = CardTitle,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Teaches: ${user.teaches.joinToString()}",
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Rating: ",
                        color = TextPrimary
                    )
                    RatingStars(rating = user.rating)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Want to learn: ${user.wantsToLearn.joinToString()}",
                    color = TextPrimary
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = onConnectClick,
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Connect", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun RatingStars(rating: Float) {
    Row {
        repeat(5) { index ->
            Text(
                text = if (index < rating.toInt()) "★" else "☆",
                color = StarRatingColor
            )
        }
    }
}





//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    SkillSwapTheme {
//
//    }
//}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillSwapHomeScreenPreviewContent() {
    val users = listOf(
        SkillUser(
            id = "1",
            name = "Alex Thompson",
            profileImage = "",
            teaches = listOf("Guitar", "Music Theory"),
            wantsToLearn = listOf("Photography", "Video Editing"),
            rating = 5f
        ),
        SkillUser(
            id = "2",
            name = "Sophia Lee",
            profileImage = "",
            teaches = listOf("Spanish"),
            wantsToLearn = listOf("Cooking"),
            rating = 4f
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BeigeBackground,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Skill Swap",
                            color = TitleText,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = CreamSurface
                    )
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BeigeBackground)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Search for a skill to learn...",
                        color = TextHint
                    )
                },
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = SearchBackground,
                    unfocusedContainerColor = SearchBackground,
                    focusedBorderColor = SearchBorder,
                    unfocusedBorderColor = SearchBorder,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = BrownPrimary
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrownPrimary,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Search", fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(users) { user ->
                    SkillUserCard(
                        user = user,
                        onConnectClick = {}
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SkillSwapHomeScreenPreview() {
    SkillSwapTheme {
        SkillSwapHomeScreenPreviewContent()
    }
}

