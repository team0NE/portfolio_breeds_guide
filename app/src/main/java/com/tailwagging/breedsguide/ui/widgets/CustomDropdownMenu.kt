package com.tailwagging.breedsguide.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tailwagging.breedsguide.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenu(
    title: String,
    textWidth: Dp,
    dogSize: String,
    modifier: Modifier, onBreedClick: (String) -> Unit
) {
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
            val interactionSource = remember { MutableInteractionSource() }
            val parentOptions = listOf("Small", "Medium", "Large", "Giant")
            val expandedState = remember { mutableStateOf(false) }
            val selectedOption = remember {
                mutableStateOf(
                    when (dogSize) {
                        "Small" -> parentOptions[0]
                        "Medium" -> parentOptions[1]
                        "Large" -> parentOptions[2]
                        "Giant" -> parentOptions[3]
                        else -> parentOptions[0]
                    }
                )
            }
            ExposedDropdownMenuBox(
                expanded = expandedState.value,
                onExpandedChange = {
                    expandedState.value = !expandedState.value
                },
                modifier = modifier
            ) {

                Box(
                    modifier = modifier
                        .requiredHeight(46.dp)
                        .requiredWidth(148.dp)
                        .paint(
                            painterResource(id = R.drawable.ic_breed_button),
                            contentScale = ContentScale.FillBounds
                        )

                ) {
                    BasicTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .focusProperties {
                                canFocus = false
                            }.menuAnchor(),
                        value = selectedOption.value,
                        onValueChange = { chosenValue ->
                            selectedOption.value = chosenValue
                        },
                        textStyle = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF30353F),
                            textAlign = TextAlign.Center,
                        ),
                        decorationBox = @Composable {
                            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                                value = selectedOption.value,
                                visualTransformation = VisualTransformation.None,
                                innerTextField = it,
                                singleLine = true,
                                enabled = true,
                                interactionSource = interactionSource,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState.value) },
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
                    ExposedDropdownMenu(
                        modifier=modifier.background(color = Color(0xFFE7A453),
                            shape = RoundedCornerShape(5.dp)),
                        expanded = expandedState.value,
                        onDismissRequest = { expandedState.value = false }) {
                        parentOptions.forEach { option ->
                            DropdownMenuItem(text = {
                                Text(
                                    text = option, style = TextStyle(
                                        fontSize = 24.sp,
                                        fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF30353F),
                                        textAlign = TextAlign.Center,
                                    )
                                )
                            }, onClick = {
                                selectedOption.value = option
                                expandedState.value = false
                                onBreedClick(option)
                            }, )
                        }
                    }
                }
            }
        }
    }
}