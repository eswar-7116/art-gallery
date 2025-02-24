package com.eswar.artgallery.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
  primary = LightThemeColors.Primary,
  onPrimary = LightThemeColors.OnPrimary,
  primaryContainer = LightThemeColors.PrimaryContainer,
  onPrimaryContainer = LightThemeColors.OnPrimaryContainer,

  secondary = LightThemeColors.Secondary,
  onSecondary = LightThemeColors.OnSecondary,
  secondaryContainer = LightThemeColors.SecondaryContainer,
  onSecondaryContainer = LightThemeColors.OnSecondaryContainer,

  background = LightThemeColors.Background,
  onBackground = LightThemeColors.OnBackground,

  surface = LightThemeColors.Surface,
  onSurface = LightThemeColors.OnSurface,

  surfaceVariant = LightThemeColors.SurfaceVariant,
  onSurfaceVariant = LightThemeColors.OnSurfaceVariant,
)

private val DarkColorScheme = darkColorScheme(
  primary = DarkThemeColors.Primary,
  onPrimary = DarkThemeColors.OnPrimary,
  primaryContainer = DarkThemeColors.PrimaryContainer,
  onPrimaryContainer = DarkThemeColors.OnPrimaryContainer,

  secondary = DarkThemeColors.Secondary,
  onSecondary = DarkThemeColors.OnSecondary,
  secondaryContainer = DarkThemeColors.SecondaryContainer,
  onSecondaryContainer = DarkThemeColors.OnSecondaryContainer,

  background = DarkThemeColors.Background,
  onBackground = DarkThemeColors.OnBackground,

  surface = DarkThemeColors.Surface,
  onSurface = DarkThemeColors.OnSurface,

  surfaceVariant = DarkThemeColors.SurfaceVariant,
  onSurfaceVariant = DarkThemeColors.OnSurfaceVariant,
)

@Composable
fun ArtGalleryTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}