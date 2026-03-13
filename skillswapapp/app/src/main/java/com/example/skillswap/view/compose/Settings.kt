package com.example.skillswap.view.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.skillswap.ui.theme.BeigeBackground
import com.example.skillswap.ui.theme.BrownPrimary
import com.example.skillswap.ui.theme.CreamSurface
import com.example.skillswap.ui.theme.DividerBeige
import com.example.skillswap.ui.theme.DmSans
import com.example.skillswap.ui.theme.GoldAccent
import com.example.skillswap.ui.theme.Poppins
import com.example.skillswap.ui.theme.SearchBackground
import com.example.skillswap.ui.theme.SearchBorder
import com.example.skillswap.ui.theme.SkillSwapTheme
import com.example.skillswap.ui.theme.TextHint
import com.example.skillswap.ui.theme.TextPrimary
import com.example.skillswap.ui.theme.TitleText
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
                        text = "Settings",
                        fontFamily = Poppins,
                        color = TitleText
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
                                text = "Enter your name",
                                color = TextHint,
                                fontFamily = DmSans
                            )
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
                                text = "Ex: Guitar, Java",
                                color = TextHint,
                                fontFamily = DmSans
                            )
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
                                text = "Ex: Photography",
                                color = TextHint,
                                fontFamily = DmSans
                            )
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
                            viewModel.updateProfile(
                                name = name,
                                teach = teach,
                                learn = learn
                            )
                            navController.popBackStack()
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
                            text = "Save Changes",
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
    val navController = rememberNavController()
    SkillSwapTheme {
        SettingsScreen(navController = navController)
    }
}
