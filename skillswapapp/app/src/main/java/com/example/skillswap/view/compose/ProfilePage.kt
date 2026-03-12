
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.skillswap.view.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.skillswap.R
import com.example.skillswap.model.ProfileUIData
import com.example.skillswap.ui.theme.BrownDark
import com.example.skillswap.ui.theme.SkillTagBackground
import com.example.skillswap.view.compose.ui.theme.SkillswapappTheme

private val PageBeige = Color(0xFFF2E7DE)
private val CardWhite = Color(0xFFFFFFFF)
private val LabelGray = Color(0xFF6B7280)
private val TitleBlack = Color(0xFF111827)
private val StarYellow = Color(0xFFFFC107)
private val RingLavender = Color(0xFFBFA1FF)
private val BorderGray = Color(0xFFE5E7EB)

class ProfilePage : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            SkillswapappTheme {

                val navController = rememberNavController()

                ProfileScreen(
                    navController = navController,
                    onBack = { navController.navigate("homeScreen") },
                    onEdit = { navController.navigate("setting") }
                )
            }
        }
    }
}

@Composable
fun ProfileScreen(
    navController: NavController,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {

    val sample = ProfileUIData(
        name = "Alex Thompson",
        rating = 5,
        canTeach = mutableListOf("Guitar", "Music Theory"),
        wantToLearn = mutableListOf("Photography", "Video Editing"),
        photoUri = ""
    )

    Scaffold(
        containerColor = PageBeige,

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        color = TitleBlack,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TitleBlack
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SkillTagBackground
                )
            )
        },

        bottomBar = {
            SkillSwapBottomBar(navController = navController)
        }

    ) { inner ->

        Column(
            modifier = modifier
                .padding(inner)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ProfileCard(
                data = sample,
                onBack = onBack,
                onEdit = onEdit
            )
        }
    }
}

@Composable
private fun ProfileCard(
    data: ProfileUIData,
    onBack: () -> Unit,
    onEdit: () -> Unit,
) {

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 40.dp)
    ) {

        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .shadow(6.dp, CircleShape)
                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .border(BorderStroke(3.dp, RingLavender), CircleShape)
            ) {

                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(Modifier.height(25.dp))

            LabeledText("Name")

            Text(
                text = data.name ?: "",
                color = TitleBlack,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(18.dp))

            LabeledText("Rating")

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                RatingStars(data.rating ?: 0, 5)

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "${data.rating}/5",
                    color = TitleBlack,
                    fontSize = 18.sp
                )
            }

            Spacer(Modifier.height(18.dp))

            LabeledText("Skills I Can Teach")

            Text(
                text = data.canTeach?.joinToString(", ") ?: "",
                color = TitleBlack,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(18.dp))

            LabeledText("Skills I Want to Learn")

            Text(
                text = data.wantToLearn?.joinToString(", ") ?: "",
                color = TitleBlack,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(28.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = BrownDark,
                        contentColor = Color.White
                    )
                ) {
                    Text("Back")
                }

                Button(
                    onClick = onEdit,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrownDark,
                        contentColor = Color.White
                    )
                ) {
                    Text("Edit")
                }
            }
        }
    }
}

@Composable
private fun LabeledText(label: String) {

    Text(
        text = label,
        color = LabelGray,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 2.dp)
    )
}

@Composable
private fun RatingStars(rating: Int, max: Int) {

    Row {

        repeat(max) { index ->

            val tint = if (index < rating) StarYellow else BorderGray

            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {

    val navController = rememberNavController()

    SkillswapappTheme {

        ProfileScreen(
            navController = navController,
            onBack = {},
            onEdit = {}
        )
    }
}
