package com.tailwagging.breedsguide.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.tailwagging.breedsguide.R
import com.tailwagging.breedsguide.ui.widgets.CustomButton

@Composable
fun MenuScreen(navigation: NavController) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
            //        .paint(painterResource(id = R.drawable.ic_menu_background), contentScale = ContentScale.FillWidth)
        ) {
            val (titleImg, titleBCK, titleGap, btnGap, backImg1, backImg2,
                breedsBtn, dogAgeBtn,
                footageBox, ftEclipse, ftImage
            ) = createRefs()

            Image(painter = painterResource(R.drawable.ic_menu_background),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(backImg1) {
                        top.linkTo(parent.top)
                        width = Dimension.matchParent
                    })

            Image(painter = painterResource(R.drawable.ic_menu_background),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(backImg2) {
                        top.linkTo(backImg1.bottom)
                        width = Dimension.matchParent
                    })

            Text(
                text = "TAIL \nWAGGING \nBREEDS \nGUIDE",
                style = TextStyle(
                    fontSize = 48.sp,
                    fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    drawStyle = Stroke(width = 20f, join = StrokeJoin.Round)
                ),
                modifier = Modifier
                    .requiredHeight(202.dp)
                    .requiredWidth(225.dp)
                    .padding(top = 50.dp)
                    .constrainAs(titleBCK) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
            )
            Text(
                text = "TAIL \nWAGGING \nBREEDS \nGUIDE",
                style = TextStyle(
                    fontSize = 48.sp,
                    fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFE18244),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .requiredHeight(202.dp)
                    .requiredWidth(220.dp)
                    .padding(top = 50.dp)
                    .constrainAs(titleImg) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        width= Dimension.wrapContent
                    },
            )
            // maybe I  need a third one to avoid white gaps
            Box(modifier = Modifier
                .height(68.dp)
                .constrainAs(titleGap) {
                    top.linkTo(titleImg.bottom)
                })

            CustomButton(
                background = R.drawable.ic_breed_button,
                foregroundStr="Breeds",
                modifier = Modifier
                    .requiredHeight(79.dp)
                    .requiredWidth(253.dp)
                    .constrainAs(breedsBtn) {
                        start.linkTo(parent.start)
                        top.linkTo(titleGap.bottom)
                        end.linkTo(parent.end)
                    }) {
                //on breads clicked
                println("on breads clicked")
                navigation.navigate("TopBreedsScreen")
            }

            Box(modifier = Modifier
                .height(22.dp)
                .constrainAs(btnGap) {
                    top.linkTo(breedsBtn.bottom)
                })

            CustomButton(
                background = R.drawable.ic_breed_button,
                foregroundStr="Dog Age",
                modifier = Modifier
                    .requiredHeight(79.dp)
                    .requiredWidth(253.dp)
                    .constrainAs(dogAgeBtn) {
                        start.linkTo(parent.start)
                        top.linkTo(btnGap.bottom)
                        end.linkTo(parent.end)
                    }) {
                //on calculator clicked
                println("on calculator clicked")
                navigation.navigate("DogAgeScreen")
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(android.graphics.Color.parseColor("#AFCD93")))
                .requiredHeight(79.dp)
                .constrainAs(footageBox) {
                    bottom.linkTo(parent.bottom)
                    width = Dimension.matchParent
                })

            Image(painter = painterResource(R.drawable.ic_menu_footage_eclipse),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredHeight(35.dp)
                    .requiredWidth(236.dp)
                    .constrainAs(ftEclipse) {
                        start.linkTo(footageBox.start)
                        top.linkTo(footageBox.top)
                        end.linkTo(footageBox.end)
                        bottom.linkTo(footageBox.bottom)
                    })

            Image(painter = painterResource(R.drawable.ic_menu_ft_image),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .requiredHeight(260.dp)
                    .requiredWidth(187.dp)
                    .padding(bottom = 15.dp)
                    .constrainAs(ftImage) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(ftEclipse.bottom)
                    })
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TailWaggingBreedsGuideTheme {
//        MenuScreen()
//    }
//}