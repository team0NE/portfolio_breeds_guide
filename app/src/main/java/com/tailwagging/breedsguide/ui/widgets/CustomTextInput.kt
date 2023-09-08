package com.tailwagging.breedsguide.ui.widgets

import android.util.Log
import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tailwagging.breedsguide.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CustomTextInput(title: String, textWidth: Dp,
                    modifier: Modifier, onDogAgeClick: (Int) -> Unit) {
    val inputText = remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF30353F),
                textAlign = TextAlign.Center,
            ),
            modifier = modifier
                .requiredHeight(45.dp)
                .requiredWidth(textWidth)
                .padding(top = 21.dp)
        )

        Box(Modifier.requiredHeight(6.dp))

        Box {
            Image(
                painter = painterResource(R.drawable.ic_breed_button),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .requiredHeight(46.dp)
                    .requiredWidth(148.dp)
            )

            BasicTextField(
                modifier = modifier
                    .requiredHeight(46.dp)
                    .requiredWidth(148.dp)
                    .onKeyEvent {
                        if (it.key == Key.Backspace) {
                            // Action When Click on Delete
                            val str = inputText.value

                            if (str.isEmpty()) {
                                inputText.value = str
                            } else {
                                inputText.value = str.substring(0, str.length - 1)
                            }

                            true
                        } else {
                            false
                        }
                    },
                value = inputText.value,
                onValueChange = { newValue ->
                    val intVal = if (newValue.isNotEmpty() && !newValue.contains(".") && !newValue.contains(",")) {
                        Integer.parseInt(newValue)
                    } else {
                        20
                    }
                    if (newValue.length < 3 && intVal > 0 && intVal < 17) {
                        inputText.value = newValue
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                    val newValue = inputText.value
                    if (newValue.isNotEmpty()) {
                        onDogAgeClick(Integer.parseInt(newValue))
                    }
                }),
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF30353F),
                    textAlign = TextAlign.Center,
                ),
                decorationBox = @Composable {
                    TextFieldDefaults.OutlinedTextFieldDecorationBox(
                        value = inputText.value,
                        visualTransformation = VisualTransformation.None,
                        innerTextField = it,
                        singleLine = true,
                        enabled = true,
                        interactionSource = interactionSource,
                        // keep vertical paddings but change the horizontal
                        contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                            start = 8.dp,
                            top = 10.dp,
                            end = 8.dp,
                            bottom = 2.dp
                        ),
                        container = {}
                    )
                }
            )
        }

    }
}
