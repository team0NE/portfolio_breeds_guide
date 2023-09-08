package com.tailwagging.breedsguide.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun CustomText(text: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Box(Modifier.requiredHeight(6.dp))

        Box {
            Image(painter = painterResource(R.drawable.ic_breed_button),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .requiredHeight(46.dp)
                    .requiredWidth(148.dp))

            Text(text = text,
                modifier = modifier
                    .requiredHeight(46.dp)
                    .requiredWidth(148.dp)
                    .padding(top = 6.dp),
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF30353F),
                textAlign = TextAlign.Center,
            )
        }

    }
}