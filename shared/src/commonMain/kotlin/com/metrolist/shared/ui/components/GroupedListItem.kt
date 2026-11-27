package com.metrolist.shared.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.metrolist.shared.settings.CornerStyle
import com.metrolist.shared.settings.CurrentSongHighlightStyle
import com.metrolist.shared.settings.Settings
import com.metrolist.shared.settings.SurfaceCardStyle
import com.metrolist.shared.ui.theme.cornerScaledDp

@Composable
fun GroupedListItem(
    index: Int,
    count: Int,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isCurrent: Boolean = false,
    content: @Composable () -> Unit,
) {
    val cardStyle = Settings.shared.surfaceCardStyle
    val outer = cornerScaledDp(12.dp, 20.dp, rounded = 15.dp, sharp = 6.dp, pill = 28.dp)
    val inner =
        when {
            cardStyle != SurfaceCardStyle.EXPRESSIVE -> outer
            Settings.shared.cornerStyle == CornerStyle.SHARP -> 3.dp
            else -> 5.dp
        }
    val topRadius = if (index == 0) outer else inner
    val bottomRadius = if (index == count - 1) outer else inner

    val baseContainer =
        when {
            isSelected -> MaterialTheme.colorScheme.primaryContainer
            cardStyle == SurfaceCardStyle.OUTLINED || cardStyle == SurfaceCardStyle.FLAT -> Color.Transparent
            else -> MaterialTheme.colorScheme.surfaceContainer
        }

    val colors =
        animateCurrentSongColors(
            isCurrent = isCurrent,
            container = baseContainer,
        )

    val rowShape =
        if (isCurrent && Settings.shared.currentSongHighlightStyle == CurrentSongHighlightStyle.EXPRESSIVE_PILL) {
            CircleShape
        } else {
            RoundedCornerShape(
                topStart = topRadius,
                topEnd = topRadius,
                bottomStart = bottomRadius,
                bottomEnd = bottomRadius,
            )
        }

    val spacing = if (cardStyle == SurfaceCardStyle.EXPRESSIVE) 3.dp else 6.dp

    Box(modifier.padding(bottom = if (index < count - 1) spacing else 0.dp)) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = rowShape,
            color = colors.container,
            shadowElevation = if (cardStyle == SurfaceCardStyle.ELEVATED) 2.dp else 0.dp,
            border =
                if (cardStyle == SurfaceCardStyle.OUTLINED && !isCurrent) {
                    BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f))
                } else {
                    null
                },
            content = content,
        )
    }
}
