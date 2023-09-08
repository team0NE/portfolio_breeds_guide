package com.tailwagging.breedsguide.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tailwagging.breedsguide.R

@Composable
fun CustomButton(background: Int,
                 foregroundStr: String,
                 modifier: Modifier, onClick: () -> Unit) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                    .paint(
                        painterResource(background),
                        contentScale = ContentScale.FillWidth)

            ) {
            Text(
                text = foregroundStr, style = TextStyle(
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF30353F),
                textAlign = TextAlign.Center,
            ),)
        }
    }
}