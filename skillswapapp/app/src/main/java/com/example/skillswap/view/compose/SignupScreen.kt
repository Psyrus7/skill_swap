package com.example.skillswap.view.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skillswap.ui.theme.BeigeBackground
import com.example.skillswap.ui.theme.BrownPrimary
import com.example.skillswap.ui.theme.TextSecondary
import com.example.skillswap.viewmodel.SignupViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.skillswap.R
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


@Composable
fun SignupScreen(viewModel: SignupViewModel = viewModel(),navController: NavController) {
    val state = viewModel.uiState.collectAsState().value
    var passwordCheck by remember { mutableStateOf(false) }
    var confirmPasswordCheck by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(BeigeBackground, CreamSurface)
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
    ) {
       Icon(
           painterResource(R.drawable.outline_arrow_back_24),
           contentDescription = "Back Button",
           modifier = Modifier.clickable(onClick = {
               navController.navigate("signupAndLoginScreen")
           })
       )
        Spacer(Modifier.height(5.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CreamSurface, RoundedCornerShape(24.dp))
                .border(1.dp, DividerBeige, RoundedCornerShape(24.dp))
                .padding(10.dp)
        ) {
            Text(
                text = "Create Account",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TitleText,
                fontFamily = Poppins
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Start your learning journey today",
                color = TextSecondary,
                fontFamily = DmSans
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Full Name",
                color = TextPrimary,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            OutlinedTextField(
                value = state.fullName,
                onValueChange = { viewModel.onFullNameChange(it) },
                label = {
                    Text(
                        "Enter Full Name",
                        fontFamily = DmSans,
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
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = BrownPrimary
                )
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Email",
                color = TextPrimary,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = {
                    Text(
                        "Enter email",
                        fontFamily = DmSans,
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
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = BrownPrimary
                )
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "I can teach a skill",
                color = TextPrimary,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            OutlinedTextField(
                value = state.teachSkill,
                onValueChange = { viewModel.onTeachSkillChange(it) },
                label = {
                    Text(
                        "Enter skill",
                        fontFamily = DmSans,
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
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = BrownPrimary
                )
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "I want to learn skill",
                color = TextPrimary,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            OutlinedTextField(
                value = state.learnSkill,
                onValueChange = { viewModel.onLearnSkillChange(it) },
                label = {
                    Text(
                        "Enter skill want to learn",
                        fontFamily = DmSans,
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
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = BrownPrimary
                )
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Password",
                color = TextPrimary,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it)
                    passwordCheck=state.password.length<5},
                isError =passwordCheck ,
                label = { if(passwordCheck){
                    Text("Password is too small")
                }
                else {
                    Text(
                        "Enter Password",
                    )
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
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Confirm password",
                color = TextPrimary,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            OutlinedTextField(
                value = state.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChange(it)
                    confirmPasswordCheck = state.confirmPassword.equals(state.password)
                },
                isError =confirmPasswordCheck ,
                label = { if(confirmPasswordCheck){
                    Text("Password is not same")
                }
                else {
                    Text(
                        "Confirm Password"
                    )
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
            Spacer(Modifier.height(22.dp))
            Button(
                onClick = { viewModel.onSignupClick()
                          },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrownPrimary,
                    contentColor = Color.White
                ),
                enabled = state.fullName.isNotEmpty() && state.email.isNotEmpty() && state.learnSkill.isNotEmpty() && state.teachSkill.isNotEmpty() && state.password.isNotEmpty()&& state.confirmPassword.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    "Sign Up",
                    color = Color.White,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold
                )
            }
            LaunchedEffect(state.isSuccess) {

                if (state.isSuccess) {

                    navController.navigate("loginScreen") {

                    }

                    Log.d("Login Success", "Navigating to Home")

                }

            }

            Spacer(Modifier.height(12.dp))
            Row {
                Text(
                    "Already have an account?",
                    color = TextSecondary,
                    fontFamily = DmSans
                )
                Text(
                    " Log in",
                    color = BrownPrimary,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate("loginScreen")
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SignupScreenPreview() {
//    val navController = rememberNavController()
//
//    SkillSwapTheme {
//        SignupScreen(navController = navController)
//    }
//}


