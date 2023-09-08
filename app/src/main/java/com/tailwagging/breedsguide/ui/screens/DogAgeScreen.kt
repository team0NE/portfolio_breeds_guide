package com.tailwagging.breedsguide.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.tailwagging.breedsguide.R
import com.tailwagging.breedsguide.model.dummyAge
import com.tailwagging.breedsguide.ui.widgets.CustomDropdownMenu
import com.tailwagging.breedsguide.ui.widgets.CustomText
import com.tailwagging.breedsguide.ui.widgets.CustomTextInput

@Composable
fun DogAgeScreen(navigation: NavController) {
    val selectedDogSize = remember { mutableStateOf("Small") }
    val initDogAge = remember { mutableIntStateOf(-1) }
    val dogSizeYearsList = remember { mutableStateOf(dummyAge[selectedDogSize.value]!!)
    }
    val humanDogAge = remember { mutableStateOf("") }


    Column(Modifier.verticalScroll(rememberScrollState())) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (titleFwd, titleBck, titleTopGuide, backBtn, backImg1, backImg2,
                dogSizeDM, dogAgeTextField, dogYears, textTitle,
                footageBox, ftEclipse, ftImage
            ) = createRefs()

            Image(painter = painterResource(R.drawable.ic_grey_bckground),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(backImg1) {
                        top.linkTo(parent.top)
                        width = Dimension.matchParent
                    })

            Image(painter = painterResource(R.drawable.ic_grey_bckground),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(backImg2) {
                        top.linkTo(backImg1.bottom)
                        width = Dimension.matchParent
                    })
            Image(painter = painterResource(R.drawable.ic_back_arrow),
                contentDescription = "null",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .requiredHeight(48.dp)
                    .requiredWidth(48.dp)
                    .padding(start = 16.dp, top = 16.dp)
                    .clickable(true, onClick = {
                        navigation.popBackStack()
                    })
                    .constrainAs(backBtn) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    })
            // title
            Box(modifier = Modifier
                .requiredHeight(36.dp)
                .constrainAs(titleTopGuide) {
                    top.linkTo(parent.top)
                })

            Text(
                text = "Dog \nAge \nCalculator",
                style = TextStyle(
                    fontSize = 48.sp,
                    fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    drawStyle = Stroke(width = 20f, join = StrokeJoin.Round)
                ),
                modifier = Modifier
                    .requiredHeight(157.dp)
                    .requiredWidth(275.dp)
                    .constrainAs(titleBck) {
                        start.linkTo(parent.start)
                        top.linkTo(titleTopGuide.bottom)
                        end.linkTo(parent.end)
                    },
            )
            Text(
                text = "Dog \nAge \nCalculator",
                style = TextStyle(
                    fontSize = 48.sp,
                    fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFE18244),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .requiredHeight(157.dp)
                    .requiredWidth(275.dp)
                    .constrainAs(titleFwd) {
                        start.linkTo(parent.start)
                        top.linkTo(titleTopGuide.bottom)
                        end.linkTo(parent.end)
                        width = Dimension.wrapContent
                    },
            )

            //custom list
            CustomDropdownMenu(title="Dog Size", textWidth=95.dp, dogSize=selectedDogSize.value, modifier=Modifier
                .constrainAs(dogSizeDM){
                start.linkTo(titleFwd.start)
                top.linkTo(titleFwd.bottom)
                end.linkTo(dogAgeTextField.end)
                width= Dimension.fillToConstraints
            }){ dogSize ->
                selectedDogSize.value = dogSize
                dogSizeYearsList.value = dummyAge[selectedDogSize.value]!!
                if (initDogAge.intValue > 0) {
                    humanDogAge.value = validateInputs(initDogAge.intValue, dogSizeYearsList).toString()
                }
            }

            CustomTextInput(title="Dog Age", textWidth=95.dp, modifier=Modifier.constrainAs(dogAgeTextField){
                start.linkTo(dogSizeDM.start)
                top.linkTo(dogSizeDM.bottom)
            }){ dogAge ->
                initDogAge.intValue = dogAge
                humanDogAge.value = validateInputs(initDogAge.intValue, dogSizeYearsList).toString()
            }

            Text(
                text = "Human Years",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF30353F),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .requiredHeight(45.dp)
                    .requiredWidth(165.dp)
                    .padding(top = 21.dp)
                    .constrainAs(textTitle){
                        start.linkTo(dogAgeTextField.start)
                        top.linkTo(dogAgeTextField.bottom)
                    })

            CustomText(text = humanDogAge.value, modifier=Modifier.constrainAs(dogYears){
                start.linkTo(textTitle.start)
                top.linkTo(textTitle.bottom)
            })

            Box(
                modifier = Modifier
                    .requiredHeight(124.dp)
                    .requiredWidth(399.dp)
                    .background(color = Color(0xFFD9D9D9))
                    .constrainAs(footageBox) {
                        bottom.linkTo(parent.bottom)
                        width = Dimension.matchParent
                    }
            )
            Image(painter = painterResource(R.drawable.ic_grey_ellipse),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredHeight(82.dp)
                    .requiredWidth(322.dp)
                    .padding(end = 37.dp, bottom = 30.dp)
                    .constrainAs(ftEclipse) {
                        end.linkTo(footageBox.end)
                        bottom.linkTo(parent.bottom)
                    })

            Image(painter = painterResource(R.drawable.ic_dog_age_image),
                contentDescription = "null",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .requiredHeight(310.dp)
                    .requiredWidth(284.dp)
                    .padding(bottom = 35.dp)
                    .constrainAs(ftImage) {
                        top.linkTo(textTitle.bottom)
                        end.linkTo(parent.end)
                        bottom.linkTo(ftEclipse.bottom)
                        height = Dimension.fillToConstraints
                    })
        }
    }
}

fun validateInputs(dogAge: Int, dogSizeYearsList: MutableState<List<Int>>): Int {
    return dogSizeYearsList.value[dogAge - 1]
}