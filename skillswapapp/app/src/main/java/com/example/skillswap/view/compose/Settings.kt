package com.example.skillswap.view.compose

import androidx.compose.foundation.border

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController

import com.example.skillswap.ui.theme.*

import com.example.skillswap.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    var name by remember { mutableStateOf("") }
    var teach by remember { mutableStateOf("") }
    var learn by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var teachError by remember { mutableStateOf(false) }
    var learnError by remember { mutableStateOf(false) }
    LaunchedEffect(state) {
        name = state.name ?: ""
        teach = state.canTeach?.joinToString(", ") ?: ""
        learn = state.wantToLearn?.joinToString(", ") ?: ""
    }

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
                            contentDescription = "Back",
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
                    Text("Name", color = TextPrimary, fontFamily = Poppins)
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                            nameError = false
                        },
                        isError = nameError,
                        placeholder = {
                            Text("Enter your name", color = TextHint)
                        },
                        supportingText = {
                            if (nameError) {
                                Text("Name cannot be empty", color = Color.Red)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SearchBackground,
                            unfocusedContainerColor = SearchBackground,
                            focusedBorderColor = GoldAccent,
                            unfocusedBorderColor = SearchBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            cursorColor = BrownPrimary
                        )
                    )
                    Text("Skills I Can Teach", color = TextPrimary, fontFamily = Poppins)
                    OutlinedTextField(
                        value = teach,
                        onValueChange = {
                            teach = it
                            teachError = false
                        },
                        isError = teachError,
                        placeholder = {
                            Text("Ex: Guitar, Java", color = TextHint)
                        },
                        supportingText = {
                            if (teachError) {
                                Text("Enter at least one skill", color = Color.Red)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SearchBackground,
                            unfocusedContainerColor = SearchBackground,
                            focusedBorderColor = GoldAccent,
                            unfocusedBorderColor = SearchBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            cursorColor = BrownPrimary
                        )
                    )

                    Text("Skills I Want To Learn", color = TextPrimary, fontFamily = Poppins)
                    OutlinedTextField(
                        value = learn,
                        onValueChange = {
                            learn = it
                            learnError = false
                        },
                        isError = learnError,
                        placeholder = {
                            Text("Ex: Photography", color = TextHint)
                        },
                        supportingText = {
                            if (learnError) {
                                Text("Enter at least one skill", color = Color.Red)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SearchBackground,
                            unfocusedContainerColor = SearchBackground,
                            focusedBorderColor = GoldAccent,
                            unfocusedBorderColor = SearchBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            cursorColor = BrownPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            nameError = name.isBlank()
                            teachError = teach.isBlank()
                            learnError = learn.isBlank()
                            if (!nameError && !teachError && !learnError) {
                                viewModel.updateProfile(
                                    name = name.trim(),
                                    teach = teach,
                                    learn = learn
                                )
                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrownPrimary,
                            contentColor = Color.White
                        )
                    ) {

                        Text(
                            "Save Changes",
                            fontFamily = Poppins
                        )
                    }
                }
            }
        }
    }
}
