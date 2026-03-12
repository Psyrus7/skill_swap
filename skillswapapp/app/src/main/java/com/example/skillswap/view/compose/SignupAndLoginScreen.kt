package com.example.skillswap.view.compose


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skillswap.R
import com.example.skillswap.ui.theme.BeigeBackground
import com.example.skillswap.ui.theme.BrownPrimary
import com.example.skillswap.ui.theme.ButtonPrimary
import com.example.skillswap.ui.theme.CreamSurface
import com.example.skillswap.ui.theme.DividerBeige
import com.example.skillswap.ui.theme.DmSans
import com.example.skillswap.ui.theme.GoldAccent
import com.example.skillswap.ui.theme.Poppins
import com.example.skillswap.ui.theme.TextPrimary
import com.example.skillswap.ui.theme.TextSecondary
import com.example.skillswap.ui.theme.TitleText


@Composable
fun SignupAndLoginScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(BeigeBackground, CreamSurface)
                )
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Welcome to Skill Swap",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            style =MaterialTheme.typography.titleLarge ,
        )
        Spacer(modifier = Modifier.height(120.dp))
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(BeigeBackground)
            ,contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.skillswaplogo),
                contentDescription = "Logo",
                modifier = Modifier.size(180.dp),
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Learn Fearlessly",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            fontFamily = Poppins
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Teach Confidently",
            fontSize = 18.sp,
            color = TextSecondary,
            fontFamily = DmSans
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                navController.navigate("signupScreen")
            },
            modifier = Modifier
                .width(180.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrownPrimary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("loginScreen")
            },
            modifier = Modifier
                .width(180.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CreamSurface,
                contentColor = BrownPrimary
            ),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, DividerBeige),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = "Log in",
                color = BrownPrimary,
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupAndLoginScreenPreview(){
//    SignupAndLoginScreen()
}

