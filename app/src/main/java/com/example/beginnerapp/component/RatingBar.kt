package com.example.beginnerapp.component

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating:Float,
    maxRating:Int=5
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text=rating.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
        Icon(
            imageVector=Icons.Default.Star,
            contentDescription = "Rating",
            tint=MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(16.dp)
        )
    }
}