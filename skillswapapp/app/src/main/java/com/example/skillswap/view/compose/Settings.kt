
package com.example.skillswap.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.skillswap.viewmodel.SettingsViewModel
import com.example.skillswap.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = viewModel()
) {

    var name by remember { mutableStateOf("") }
    var teach by remember { mutableStateOf("") }
    var learn by remember { mutableStateOf("") }

    Scaffold(

        containerColor = BeigeBackground,

        topBar = {

            CenterAlignedTopAppBar(

                title = {
                    Text(
                        "Settings",
                        fontFamily = Poppins,
                        color = TitleText
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
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

            verticalArrangement = Arrangement.spacedBy(18.dp)

        ) {

            Card(

                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(
                    containerColor = CreamSurface
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, DividerBeige, RoundedCornerShape(20.dp))

            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    Text(
                        text = "Name",
                        color = TextPrimary,
                        fontFamily = Poppins
                    )

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = {
                            Text(
                                "Enter your name",
                                color = TextHint
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SearchBackground,
                            unfocusedContainerColor = SearchBackground,
                            focusedBorderColor = GoldAccent,
                            unfocusedBorderColor = SearchBorder,
                            cursorColor = BrownPrimary
                        )
                    )

                    Text(
                        text = "Skills I Can Teach",
                        color = TextPrimary,
                        fontFamily = Poppins
                    )

                    OutlinedTextField(
                        value = teach,
                        onValueChange = { teach = it },
                        placeholder = {
                            Text(
                                "Ex: Guitar, Java",
                                color = TextHint
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SearchBackground,
                            unfocusedContainerColor = SearchBackground,
                            focusedBorderColor = GoldAccent,
                            unfocusedBorderColor = SearchBorder
                        )
                    )

                    Text(
                        text = "Skills I Want To Learn",
                        color = TextPrimary,
                        fontFamily = Poppins
                    )

                    OutlinedTextField(
                        value = learn,
                        onValueChange = { learn = it },
                        placeholder = {
                            Text(
                                "Ex: Photography",
                                color = TextHint
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SearchBackground,
                            unfocusedContainerColor = SearchBackground,
                            focusedBorderColor = GoldAccent,
                            unfocusedBorderColor = SearchBorder
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(

                        onClick = {

                            viewModel.updateProfile(
                                name,
                                teach,
                                learn
                            )

                            navController.popBackStack()

                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),

                        shape = RoundedCornerShape(16.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrownPrimary
                        )

                    ) {

                        Text(
                            "Save Changes",
                            color = androidx.compose.ui.graphics.Color.White,
                            fontFamily = Poppins
                        )

                    }

                }

            }

        }

    }

}
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    com.example.skillswap.ui.theme.SkillSwapTheme {
        SettingsScreen(
            navController = navController
        )
    }
}