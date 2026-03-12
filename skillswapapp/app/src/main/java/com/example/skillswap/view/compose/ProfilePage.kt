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
import androidx.navigation.compose.rememberNavController
import com.example.skillswap.R   // <-- your app R, not android.R
import com.example.skillswap.model.ProfileUIData
import com.example.skillswap.ui.theme.BrownDark
import com.example.skillswap.ui.theme.SkillTagBackground
import com.example.skillswap.view.compose.ui.theme.SkillswapappTheme



private val PageBeige = Color(0xFFF2E7DE)         // page background
private val CardWhite = Color(0xFFFFFFFF)         // card background
private val LabelGray = Color(0xFF6B7280)         // small section labels
private val TitleBlack = Color(0xFF111827)        // main text
private val StarYellow = Color(0xFFFFC107)        // rating stars
private val RingLavender = Color(0xFFBFA1FF)      // avatar ring
private val PrimaryDark = Color(0xFF111028)       // "Edit" button fill
private val BorderGray = Color(0xFFE5E7EB)

class ProfilePage : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkillswapappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var navController= rememberNavController()
//                    val sample = ProfileUIData(
//                        name = "Alex Thompson",
//                        rating = 5,
//                        canTeach = "Guitar, Music Theory",
//                        wantToLearn = "Photography, Video Editing"
//                    )
                    ProfileScreen(

                        onBack = {navController.navigate("homeScreen")},
                        onEdit = {navController.navigate("setting")}
                    )
                }
            }
        }
    }
}


/* ---------- Screen ---------- */
@Composable
fun ProfileScreen(

    onBack: () -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sample = ProfileUIData(
        name = "Alex Thompson",
        rating = 5,
        canTeach = mutableListOf("Guitar, Music Theory"),
        wantToLearn = mutableListOf("Photography, Video Editing"),
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
        }
    ) { inner ->
        Column(
            modifier = modifier
                .padding(inner)
                .fillMaxSize()
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

/* ---------- Card ---------- */
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
            .fillMaxHeight().padding(0.dp,80.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar with lavender ring
            Box(
                modifier = Modifier
                    .shadow(6.dp, CircleShape, clip = false)
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

            // Name
            LabeledText(label = "Name")
            Text(
                text = data.name?:"",
                color = TitleBlack,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(18.dp))

            // Rating
            LabeledText(label = "Rating")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RatingStars(rating = data.rating?:0, max = 5)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "${data.rating}/5",
                    color = TitleBlack,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(Modifier.height(18.dp))

            // Skills I Can Teach
            LabeledText(label = "Skills I Can Teach")
            Text(
                text = data.canTeach?.joinToString (",")?:"",
                color = TitleBlack,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(18.dp))

            // Skills I Want to Learn
            LabeledText(label = "Skills I Want to Learn")
            Text(
                text = data.wantToLearn?.joinToString (",")?:"",
                color = TitleBlack,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(28.dp))

            // Buttons row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onBack,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, BorderGray),
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = BrownDark
                    )
                ) {
                    Text("Back", fontWeight = FontWeight.SemiBold)
                }

                Button(
                    onClick = onEdit,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrownDark,
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 2.dp,
                        pressedElevation = 4.dp
                    )
                ) {
                    Text("Edit", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}


/* ---------- Small helpers ---------- */

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
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(max) { index ->
            val tint = if (index < rating) StarYellow else BorderGray
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(25.dp)
            )
            if (index < max - 1) Spacer(Modifier.width(2.dp))
        }
    }
}

/* ---------- Preview ---------- */
//@Preview(showBackground = true, backgroundColor = 0xFFF2E7DE)
//@Composable
//private fun ProfileScreenPreview() {
//    val sample = ProfileUIData(
//        name = "Alex Thompson",
//        rating = 5,
//        canTeach = "Guitar, Music Theory",
//        wantToLearn = "Photography, Video Editing"
//    )
//    ProfileScreen(
//
//        onBack = {},
//        onEdit = {}
//    )
//}