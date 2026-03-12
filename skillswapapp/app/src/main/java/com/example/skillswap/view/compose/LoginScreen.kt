package com.example.skillswap.view.compose



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skillswap.ui.theme.ActiveIcon
import com.example.skillswap.ui.theme.BeigeBackground
import com.example.skillswap.ui.theme.BrownPrimary
import com.example.skillswap.ui.theme.CreamSurface
import com.example.skillswap.ui.theme.DmSans
import com.example.skillswap.ui.theme.GoldAccent
import com.example.skillswap.ui.theme.Poppins
import com.example.skillswap.ui.theme.SearchBackground
import com.example.skillswap.ui.theme.SearchBorder
import com.example.skillswap.ui.theme.TextHint
import com.example.skillswap.ui.theme.TextPrimary
import com.example.skillswap.ui.theme.TextSecondary
import com.example.skillswap.ui.theme.TitleText
import com.example.skillswap.viewmodel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(BeigeBackground, CreamSurface)
                )
            )
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = ActiveIcon
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Back to Home",
                color = TextPrimary,
                fontFamily = DmSans,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(BrownPrimary)
                    .border(2.dp, GoldAccent, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(34.dp),
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome Back",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = TitleText,
                fontFamily = Poppins
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Log in to your account",
                color = TextSecondary,
                fontFamily = DmSans
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Email",
            color = TextPrimary,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.updateEmail(it) },
            placeholder = {
                Text(
                    "Enter your email",
                    color = TextHint,
                    fontFamily = DmSans
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = SearchBackground,
                unfocusedContainerColor = SearchBackground,
                focusedBorderColor = GoldAccent,
                unfocusedBorderColor = SearchBorder,
                cursorColor = BrownPrimary
            )
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Password",
            color = TextPrimary,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.updatePassword(it) },
            placeholder = {
                Text(
                    "Enter your password",
                    color = TextHint,
                    fontFamily = DmSans
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = SearchBackground,
                unfocusedContainerColor = SearchBackground,
                focusedBorderColor = GoldAccent,
                unfocusedBorderColor = SearchBorder,
                cursorColor = BrownPrimary
            )
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { viewModel.login() },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrownPrimary
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp
            )
        ) {
            Text(
                text = "Log In",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account?",
                color = TextSecondary,
                fontFamily = DmSans
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Sign up",
                color = BrownPrimary,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                modifier = Modifier.clickable { }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}
