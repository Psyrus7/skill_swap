@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.skillswap.view.compose

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skillswap.R
import com.example.skillswap.ui.theme.BrownDark
import com.example.skillswap.ui.theme.SkillTagBackground
import com.example.skillswap.view.compose.ui.theme.SkillswapappTheme
import com.example.skillswap.viewmodel.ProfileViewModel
// imports at the top of Settings.kt
import android.app.Activity
import android.content.Intent
import android.content.Context
import android.content.ContextWrapper

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController


/* reuse your color tokens */
private val PageBeige = Color(0xFFF2E7DE)
private val CardWhite = Color(0xFFFFFFFF)
private val LabelGray = Color(0xFF6B7280)
private val TitleBlack = Color(0xFF111827)
private val RingLavender = Color(0xFFBFA1FF)
private val BorderGray = Color(0xFFE5E7EB)

class Settings : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkillswapappTheme {
                var navController= rememberNavController()
                SettingsScreen(
                    onBack = {  }
                )
            }
        }
    }
}

/* -------------------- Composable Screen -------------------- */
@Composable
fun SettingsScreen(
    viewModel: ProfileViewModel = viewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Local editable state (pre-filled from ViewModel)
    var name by remember(state.name) { mutableStateOf(TextFieldValue(state.name.orEmpty())) }
    var canTeachText by remember(state.canTeach) {
        mutableStateOf(TextFieldValue(state.canTeach?.joinToString(", ") ?: ""))
    }
    var wantToLearnText by remember(state.wantToLearn) {
        mutableStateOf(TextFieldValue(state.wantToLearn?.joinToString(", ") ?: ""))
    }
    var photoUriStr by remember(state.photoUri) { mutableStateOf(state.photoUri) }

    // Photo picker launcher
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            photoUriStr = uri.toString()
        }
    }

    Scaffold(
        containerColor = PageBeige,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Settings",
                        color = TitleBlack,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
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
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(0.dp, 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    /* --- Avatar picker (circle) --- */
                    AvatarEditor(
                        photoUri = photoUriStr,
                        onChangePhoto = {
                            photoPicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                    )

                    Spacer(Modifier.height(24.dp))

                    /* --- Name --- */
                    Labeled(label = "Name")
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Enter your name") }
                    )

                    Spacer(Modifier.height(16.dp))

                    /* --- Skills I Can Teach (comma-separated) --- */
                    Labeled(label = "Skills I Can Teach")
                    OutlinedTextField(
                        value = canTeachText,
                        onValueChange = { canTeachText = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("e.g., Guitar, Music Theory") },
                        maxLines = 4
                    )

                    Spacer(Modifier.height(16.dp))

                    /* --- Skills I Want to Learn (comma-separated) --- */
                    Labeled(label = "Skills I Want to Learn")
                    OutlinedTextField(
                        value = wantToLearnText,
                        onValueChange = { wantToLearnText = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("e.g., Photography, Video Editing") },
                        maxLines = 4
                    )

                    Spacer(Modifier.height(24.dp))

                    /* --- Buttons --- */
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
                            Text("Cancel", fontWeight = FontWeight.SemiBold)
                        }

                        Button(
                            onClick = {
                                val canTeachList = canTeachText.text.split(",")
                                    .map { it.trim() }
                                    .filter { it.isNotEmpty() }

                                val wantToLearnList = wantToLearnText.text.split(",")
                                    .map { it.trim() }
                                    .filter { it.isNotEmpty() }

                                // Update VM in Settings (optional but fine)
                                viewModel.updateProfile(
                                    name = name.text.ifBlank { null },
                                    canTeach = canTeachList,
                                    wantToLearn = wantToLearnList,
                                    photoUri = photoUriStr
                                )

                                // Return result to the caller (ProfilePage) and finish
                                val activity = context.findActivity()
                                val result = Intent().apply {
                                    putExtra("name", name.text.ifBlank { null })
                                    putStringArrayListExtra("canTeach", ArrayList(canTeachList))
                                    putStringArrayListExtra("wantToLearn", ArrayList(wantToLearnList))
                                    putExtra("photoUri", photoUriStr)
                                }
                                activity?.setResult(Activity.RESULT_OK, result)
                                activity?.finish()
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.weight(1f).height(44.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = BrownDark, contentColor = Color.White)
                        ) {
                            Text("Save", fontWeight = FontWeight.SemiBold)
                        }

                    }
                }
            }
        }
    }
}

/* -------------------- Small UI Helpers -------------------- */

@Composable
private fun Labeled(label: String) {
    Text(
        text = label,
        color = LabelGray,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp)
    )
}

@Composable
private fun AvatarEditor(
    photoUri: String?,
    onChangePhoto: () -> Unit
) {
    val context = LocalContext.current
    val bitmap: Bitmap? by remember(photoUri) {
        mutableStateOf(
            photoUri?.let { uriStr ->
                runCatching {
                    val uri = Uri.parse(uriStr)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        val source = ImageDecoder.createSource(context.contentResolver, uri)
                        ImageDecoder.decodeBitmap(source)
                    } else {
                        @Suppress("DEPRECATION")
                        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                    }
                }.getOrNull()
            }
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .shadow(6.dp, CircleShape, clip = false)
                .clip(CircleShape)
                .background(Color.Transparent)
                .border(BorderStroke(3.dp, RingLavender), CircleShape)
                .clickable { onChangePhoto() }              // tap to change
        ) {
            if (bitmap != null) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = "Profile photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )
            } else {
                Image(
                    painter = androidx.compose.ui.res.painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Default profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            "Change photo",
            color = TitleBlack,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { onChangePhoto() }
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

fun Context.findActivity(): Activity? {
    var ctx = this
    while (ctx is ContextWrapper) {
        if (ctx is Activity) return ctx
        ctx = ctx.baseContext
    }
    return null
}



@Preview(showBackground = true, backgroundColor = 0xFFF2E7DE)
@Composable
private fun SettingsPreview() {
    SkillswapappTheme {
        SettingsScreen(viewModel() , onBack = {})
    }
}
