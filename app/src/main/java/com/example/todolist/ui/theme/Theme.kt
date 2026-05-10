@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todolist.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import com.example.todolist.R

private val googleFontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

private fun nunito(weight: FontWeight) = Font(
    googleFont = GoogleFont("Nunito"),
    fontProvider = googleFontProvider,
    weight = weight,
)

private val NunitoFamily = FontFamily(
    nunito(FontWeight.Normal),
    nunito(FontWeight.Medium),
    nunito(FontWeight.SemiBold),
    nunito(FontWeight.Bold),
)

private val MikuLightColorScheme = lightColorScheme(
    primary = Color(0xFF39C5BB),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD4F7F4),
    onPrimaryContainer = Color(0xFF005048),
    secondary = Color(0xFF42A5F5),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE3F2FD),
    onSecondaryContainer = Color(0xFF0D47A1),
    tertiary = Color(0xFF29B6F6),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFB3E5FC),
    onTertiaryContainer = Color(0xFF01579B),
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFAFCFF),
    onBackground = Color(0xFF1A1C1E),
    surface = Color.White,
    onSurface = Color(0xFF1A1C1E),
    surfaceVariant = Color(0xFFE3EEF5),
    onSurfaceVariant = Color(0xFF5C6B7A),
    outline = Color(0xFFB9D6E8),
    outlineVariant = Color(0xFFE3EEF5),
    scrim = Color(0xFF000000),
    inverseSurface = Color(0xFF2F3033),
    inverseOnSurface = Color(0xFFF1F0F4),
    inversePrimary = Color(0xFF6DD9CF),
    surfaceTint = Color(0xFF39C5BB),
    surfaceBright = Color.White,
    surfaceDim = Color(0xFFD9E3EE),
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFF5F9FF),
    surfaceContainer = Color(0xFFEEF4FB),
    surfaceContainerHigh = Color(0xFFE8F0FA),
    surfaceContainerHighest = Color(0xFFE3ECF7),
)

private fun Typography.withNunito(): Typography {
    val base = Typography()
    val ff = NunitoFamily
    return base.copy(
        displayLarge = base.displayLarge.copy(fontFamily = ff),
        displayMedium = base.displayMedium.copy(fontFamily = ff),
        displaySmall = base.displaySmall.copy(fontFamily = ff),
        headlineLarge = base.headlineLarge.copy(fontFamily = ff),
        headlineMedium = base.headlineMedium.copy(fontFamily = ff),
        headlineSmall = base.headlineSmall.copy(fontFamily = ff, fontWeight = FontWeight.SemiBold),
        titleLarge = base.titleLarge.copy(fontFamily = ff, fontWeight = FontWeight.SemiBold),
        titleMedium = base.titleMedium.copy(fontFamily = ff, fontWeight = FontWeight.Medium),
        titleSmall = base.titleSmall.copy(fontFamily = ff, fontWeight = FontWeight.Medium),
        bodyLarge = base.bodyLarge.copy(fontFamily = ff),
        bodyMedium = base.bodyMedium.copy(fontFamily = ff),
        bodySmall = base.bodySmall.copy(fontFamily = ff),
        labelLarge = base.labelLarge.copy(fontFamily = ff, fontWeight = FontWeight.Medium),
        labelMedium = base.labelMedium.copy(fontFamily = ff, fontWeight = FontWeight.Medium),
        labelSmall = base.labelSmall.copy(fontFamily = ff),
    )
}

private val TodoTypography = Typography().withNunito()

private val TodoShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(14.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp),
)

@Composable
fun TodoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MikuLightColorScheme,
        typography = TodoTypography,
        shapes = TodoShapes,
        content = content,
    )
}

@Composable
fun mikuOutlinedTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = MaterialTheme.colorScheme.onSurface,
    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
    disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    focusedBorderColor = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
    disabledBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.38f),
    cursorColor = MaterialTheme.colorScheme.primary,
    focusedLabelColor = MaterialTheme.colorScheme.primary,
    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
)

@Composable
fun mikuTopAppBarColors() = TopAppBarDefaults.topAppBarColors(
    containerColor = Color.Transparent,
    titleContentColor = MaterialTheme.colorScheme.onSurface,
    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
    actionIconContentColor = MaterialTheme.colorScheme.onSurface,
)

@Composable
fun mikuCenterTopAppBarColors() = TopAppBarDefaults.centerAlignedTopAppBarColors(
    containerColor = Color.Transparent,
    titleContentColor = MaterialTheme.colorScheme.onSurface,
    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
    actionIconContentColor = MaterialTheme.colorScheme.onSurface,
)

@Composable
fun mikuFilledButtonColors() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.onPrimary,
    disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
    disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.38f),
)

@Composable
fun mikuOutlinedPrimaryButtonColors() = ButtonDefaults.outlinedButtonColors(
    contentColor = MaterialTheme.colorScheme.primary,
)

@Composable
fun mikuOutlinedPrimaryBorderStroke(): BorderStroke {
    val c = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    return BorderStroke(1.dp, c)
}
