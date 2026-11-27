package com.metrolist.shared.ui.icons

import androidx.compose.ui.graphics.vector.ImageVector
import com.metrolist.shared.settings.IconStyle

fun styledIcon(
    style: IconStyle,
    outlined: ImageVector,
    filled: ImageVector = outlined,
    rounded: ImageVector = outlined,
    selected: Boolean = false,
): ImageVector =
    when (style) {
        IconStyle.EXPRESSIVE -> if (selected) filled else outlined
        IconStyle.ROUNDED -> if (selected && filled !== outlined) filled else rounded
        IconStyle.OUTLINED -> outlined
        IconStyle.FILLED -> filled
    }
