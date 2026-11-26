/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package com.metrolist.shared.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.metrolist.shared.constants.ThumbnailCornerRadius
import com.metrolist.shared.settings.CornerStyle
import com.metrolist.shared.settings.Settings

val MetrolistShapes =
    Shapes(
        extraSmall = RoundedCornerShape(16.dp),
        small = RoundedCornerShape(20.dp),
        medium = RoundedCornerShape(24.dp),
        large = RoundedCornerShape(28.dp),
        extraLarge = RoundedCornerShape(32.dp),
    )

@Composable
fun rememberMetrolistShapes(): Shapes {
    val cornerStyle = Settings.shared.cornerStyle
    val enhanced = Settings.shared.uiEnhancements
    return remember(cornerStyle, enhanced) {
        if (!enhanced) {
            Shapes(
                extraSmall = RoundedCornerShape(8.dp),
                small = RoundedCornerShape(12.dp),
                medium = RoundedCornerShape(16.dp),
                large = RoundedCornerShape(20.dp),
                extraLarge = RoundedCornerShape(24.dp),
            )
        } else {
            when (cornerStyle) {
                CornerStyle.EXPRESSIVE -> MetrolistShapes

                CornerStyle.ROUNDED ->
                    Shapes(
                        extraSmall = RoundedCornerShape(8.dp),
                        small = RoundedCornerShape(12.dp),
                        medium = RoundedCornerShape(16.dp),
                        large = RoundedCornerShape(20.dp),
                        extraLarge = RoundedCornerShape(28.dp),
                    )

                CornerStyle.SHARP ->
                    Shapes(
                        extraSmall = RoundedCornerShape(4.dp),
                        small = RoundedCornerShape(6.dp),
                        medium = RoundedCornerShape(8.dp),
                        large = RoundedCornerShape(10.dp),
                        extraLarge = RoundedCornerShape(12.dp),
                    )

                CornerStyle.PILL ->
                    Shapes(
                        extraSmall = RoundedCornerShape(20.dp),
                        small = RoundedCornerShape(26.dp),
                        medium = RoundedCornerShape(32.dp),
                        large = RoundedCornerShape(38.dp),
                        extraLarge = RoundedCornerShape(44.dp),
                    )
            }
        }
    }
}

fun cornerScaledDp(
    legacy: Dp,
    expressive: Dp,
    rounded: Dp = (expressive * 0.65f).coerceAtLeast(legacy),
    sharp: Dp = 6.dp.coerceAtMost(expressive),
    pill: Dp = expressive * 1.4f,
): Dp {
    if (!Settings.shared.uiEnhancements) return legacy
    return when (Settings.shared.cornerStyle) {
        CornerStyle.EXPRESSIVE -> expressive
        CornerStyle.ROUNDED -> rounded
        CornerStyle.SHARP -> sharp
        CornerStyle.PILL -> pill
    }
}

object ComponentShapes {
    val CardShape get() = RoundedCornerShape(cornerScaledDp(12.dp, 24.dp, rounded = 16.dp, sharp = 6.dp, pill = 32.dp))
    val BottomSheetShape
        get() =
            cornerScaledDp(16.dp, 32.dp, rounded = 24.dp, sharp = 8.dp, pill = 36.dp).let {
                RoundedCornerShape(topStart = it, topEnd = it)
            }
    val SmallThumbnailShape get() = RoundedCornerShape(cornerScaledDp(4.dp, 10.dp, rounded = 8.dp, sharp = 3.dp, pill = 14.dp))
    val ThumbnailShape get() = RoundedCornerShape(cornerScaledDp(6.dp, 16.dp, rounded = 12.dp, sharp = 4.dp, pill = 22.dp))
    val AlbumArtShape get() = RoundedCornerShape(cornerScaledDp(16.dp, 32.dp, rounded = 20.dp, sharp = 8.dp, pill = 40.dp))
    val MenuExteriorShape get() = RoundedCornerShape(cornerScaledDp(16.dp, 28.dp, rounded = 20.dp, sharp = 8.dp, pill = 34.dp))
    val MenuGroupShape get() = RoundedCornerShape(cornerScaledDp(12.dp, 22.dp, rounded = 16.dp, sharp = 6.dp, pill = 28.dp))
    val MenuItemShape get() = RoundedCornerShape(cornerScaledDp(6.dp, 10.dp, rounded = 8.dp, sharp = 4.dp, pill = 16.dp))
    val SearchExteriorCornerRadius get() = cornerScaledDp(16.dp, 26.dp, rounded = 20.dp, sharp = 8.dp, pill = 32.dp)
    val SearchItemCornerRadius get() = cornerScaledDp(6.dp, 8.dp, rounded = 8.dp, sharp = 4.dp, pill = 14.dp)
}

fun <T> uiEnhanced(
    legacy: T,
    enhanced: T,
): T = if (Settings.shared.uiEnhancements) enhanced else legacy

fun gridCellCornerRadius(): Dp = cornerScaledDp(ThumbnailCornerRadius, 14.dp, rounded = 10.dp, sharp = 4.dp, pill = 22.dp)

fun gridCellShape(): RoundedCornerShape = RoundedCornerShape(gridCellCornerRadius())

fun gridCellInset(): Dp = uiEnhanced(1.dp, 4.dp)
