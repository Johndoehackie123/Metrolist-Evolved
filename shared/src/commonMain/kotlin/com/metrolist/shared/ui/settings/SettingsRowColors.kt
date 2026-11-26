package com.metrolist.shared.ui.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.metrolist.shared.settings.Settings
import com.metrolist.shared.settings.SurfaceCardStyle

fun ColorScheme.settingsRowContainerColor(pressed: Boolean = false): Color {
    val style = Settings.shared.surfaceCardStyle
    if (style == SurfaceCardStyle.OUTLINED || style == SurfaceCardStyle.FLAT) {
        return if (pressed) surfaceContainerHigh else Color.Transparent
    }
    if (style == SurfaceCardStyle.ELEVATED) {
        val elevated = if (pressed) surfaceContainerHighest else surfaceContainer
        return if (elevated == background) surfaceColorAtElevation(if (pressed) 14.dp else 8.dp) else elevated
    }
    val container = if (pressed) surfaceContainerHighest else surfaceContainerHigh
    // Pure black collapses container tokens; tonal elevation retains the theme's surface tint.
    return if (container == background) surfaceColorAtElevation(if (pressed) 12.dp else 6.dp) else container
}

fun ColorScheme.settingsRowBorder(): BorderStroke? =
    when (Settings.shared.surfaceCardStyle) {
        SurfaceCardStyle.OUTLINED -> BorderStroke(1.dp, outlineVariant.copy(alpha = 0.65f))
        else -> null
    }

fun ColorScheme.settingsRowElevation(): Dp =
    when (Settings.shared.surfaceCardStyle) {
        SurfaceCardStyle.ELEVATED -> 3.dp
        else -> 0.dp
    }
