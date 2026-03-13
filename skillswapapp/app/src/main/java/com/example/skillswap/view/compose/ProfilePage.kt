package com.example.skillswap.view.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image

import androidx.compose.foundation.border

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Star

import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.*

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.*

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import com.example.skillswap.R

import com.example.skillswap.viewmodel.ProfileViewModel

import com.example.skillswap.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun ProfileScreen(

    navController: NavController,

    viewModel: ProfileViewModel = viewModel()

) {

    val state by viewModel.state.collectAsState()

    Scaffold(

        containerColor = BeigeBackground,

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text(

                        "Profile",

                        fontFamily = Poppins,

                        color = TitleText

                    )

                },

                navigationIcon = {

                    IconButton(

                        onClick = { navController.navigate("homeScreen") }

                    ) {

                        Icon(

                            Icons.AutoMirrored.Filled.ArrowBack,

                            contentDescription = null,

                            tint = TitleText

                        )

                    }

                },

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(

                    containerColor = CreamSurface

                )

            )

        },

        bottomBar = {

            SkillSwapBottomBar(navController)

        }

    ) { padding ->

        Column(

            modifier = Modifier

                .padding(padding)

                .verticalScroll(rememberScrollState())

                .fillMaxSize()

                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Card(

                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(

                    containerColor = CreamSurface

                ),

                elevation = CardDefaults.cardElevation(8.dp),

                modifier = Modifier

                    .fillMaxWidth()

                    .border(1.dp, DividerBeige, RoundedCornerShape(20.dp))

            ) {

                Column(

                    modifier = Modifier.padding(24.dp),

                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Image(

                        painter = painterResource(R.drawable.ic_launcher_background),

                        contentDescription = null,

                        modifier = Modifier

                            .size(140.dp)

                            .clip(CircleShape)

                            .border(3.dp, GoldAccent, CircleShape)

                    )

                    Spacer(Modifier.height(20.dp))

                    Text(

                        text = state.name ?: "",

                        fontSize = 24.sp,

                        fontFamily = Poppins,

                        fontWeight = FontWeight.Bold,

                        color = TextPrimary

                    )

                    Spacer(Modifier.height(10.dp))

                    Row {

                        repeat(5) {

                            Icon(

                                Icons.Default.Star,

                                contentDescription = null,

                                tint =

                                    if (it < (state.rating ?: 0))

                                        GoldAccent

                                    else DividerBeige

                            )

                        }

                    }

                    Spacer(Modifier.height(22.dp))

                    Text(

                        "Skills I Can Teach",

                        fontFamily = Poppins,

                        fontWeight = FontWeight.SemiBold,

                        color = TextPrimary

                    )

                    Spacer(Modifier.height(4.dp))

                    Text(

                        state.canTeach?.joinToString(", ") ?: "",

                        color = TextSecondary,

                        fontFamily = DmSans

                    )

                    Spacer(Modifier.height(18.dp))

                    Text(

                        "Skills I Want to Learn",

                        fontFamily = Poppins,

                        fontWeight = FontWeight.SemiBold,

                        color = TextPrimary

                    )

                    Spacer(Modifier.height(4.dp))

                    Text(

                        state.wantToLearn?.joinToString(", ") ?: "",

                        color = TextSecondary,

                        fontFamily = DmSans

                    )

                    Spacer(Modifier.height(28.dp))

                    Row(

                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement = Arrangement.spacedBy(12.dp)

                    ) {

                        Button(

                            onClick = {

                                navController.navigate("setting")

                            },

                            modifier = Modifier

                                .weight(1f)

                                .height(50.dp),

                            shape = RoundedCornerShape(16.dp),

                            colors = ButtonDefaults.buttonColors(

                                containerColor = BrownPrimary,

                                contentColor = Color.White

                            ),

                            elevation = ButtonDefaults.buttonElevation(

                                defaultElevation = 6.dp

                            )

                        ) {

                            Text(

                                text = "Edit",

                                fontFamily = Poppins,

                                fontWeight = FontWeight.SemiBold

                            )

                        }

                        OutlinedButton(

                            onClick = {

                                viewModel.logout()

                                navController.navigate("loginScreen") {

                                    popUpTo(0)

                                }

                            },

                            modifier = Modifier

                                .weight(1f)

                                .height(50.dp),

                            shape = RoundedCornerShape(16.dp),

                            border = BorderStroke(1.dp, DividerBeige),

                            colors = ButtonDefaults.outlinedButtonColors(

                                containerColor = CreamSurface,

                                contentColor = BrownDark

                            )

                        ) {

                            Text(

                                text = "Logout",

                                fontFamily = Poppins,

                                fontWeight = FontWeight.SemiBold

                            )

                        }

                    }


                }

            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    com.example.skillswap.ui.theme.SkillSwapTheme {
        ProfileScreen(
            navController = navController
        )
    }
}