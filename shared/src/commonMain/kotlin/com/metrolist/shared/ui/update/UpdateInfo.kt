package com.metrolist.shared.ui.update

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.metrolist.shared.generated.resources.Res
import com.metrolist.shared.generated.resources.updates_version
import org.jetbrains.compose.resources.stringResource

@Composable
fun UpdateInfo(
    version: String,
    details: List<Pair<String, String>>,
    versionLabel: String = stringResource(Res.string.updates_version),
) {
    val outer = 26.dp
    val inner = 6.dp
    val borderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        InfoTile(
            label = versionLabel,
            value = version,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            borderColor = borderColor,
            shape = RoundedCornerShape(topStart = outer, topEnd = outer, bottomStart = inner, bottomEnd = inner),
            modifier = Modifier.fillMaxWidth(),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
            details.forEachIndexed { index, (label, value) ->
                InfoTile(
                    label = label,
                    value = value,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    borderColor = borderColor,
                    shape =
                        RoundedCornerShape(
                            topStart = inner,
                            topEnd = inner,
                            bottomStart = if (index == 0) outer else inner,
                            bottomEnd = if (index == details.lastIndex) outer else inner,
                        ),
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun InfoTile(
    label: String,
    value: String,
    containerColor: Color,
    contentColor: Color,
    borderColor: Color,
    shape: RoundedCornerShape,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        border = BorderStroke(1.dp, borderColor),
    ) {
        Column(Modifier.padding(horizontal = 18.dp, vertical = 15.dp)) {
            Text(label, style = MaterialTheme.typography.labelLarge, color = contentColor.copy(alpha = 0.85f))
            Spacer(Modifier.height(2.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold),
                color = contentColor,
            )
        }
    }
}
