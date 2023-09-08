package com.tailwagging.breedsguide.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.tailwagging.breedsguide.R
import com.tailwagging.breedsguide.viewmodels.BreedVM

@Composable
fun BreedDescriptionScreen(navigation: NavController, vm: BreedVM) {
    Box(modifier= Modifier.fillMaxSize()) {
        // background layer
        ConstraintLayout {
            val (dogListBckImage, dogListBckImage2, dogListBckImage3, dogListBckImage4) = createRefs()
            Image(painter = painterResource(R.drawable.ic_grey_bckground),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(dogListBckImage) {
                        top.linkTo(parent.top)
                        width = Dimension.matchParent
                    })

            Image(painter = painterResource(R.drawable.ic_grey_bckground),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(dogListBckImage2) {
                        top.linkTo(dogListBckImage.bottom)
                        width = Dimension.matchParent
                    })

            Image(painter = painterResource(R.drawable.ic_grey_bckground),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(dogListBckImage3) {
                        top.linkTo(dogListBckImage2.bottom)
                        width = Dimension.matchParent
                    })

            Image(painter = painterResource(R.drawable.ic_grey_bckground),
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(dogListBckImage4) {
                        top.linkTo(dogListBckImage3.bottom)
                        width = Dimension.matchParent
                    })
        }
        // foreground layer
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (
                    topFwd, topBck,
                    titleFwd, titleBck, titleTopGuide,
                    breedImg, descGap, descListView,
                    homeBTN, bottomGap, bottomFwd, bottomBck
                ) = createRefs()
                Box(modifier = Modifier
                    .requiredHeight(36.dp)
                    .constrainAs(titleTopGuide) {
                        top.linkTo(parent.top)
                    })

                Image(painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "null",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .requiredHeight(66.dp)
                        .requiredWidth(72.dp)
                        .padding(start = 24.dp, top = 18.dp)
                        .clickable(true, onClick = {
                            vm.decreaseIdx(vm.index.intValue)
                        })
                        .constrainAs(topBck) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        })

                Image(painter = painterResource(R.drawable.ic_fwd_arrow),
                    contentDescription = "null",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .requiredHeight(66.dp)
                        .requiredWidth(72.dp)
                        .padding(top = 18.dp, end = 24.dp)
                        .clickable(true, onClick = {
                            vm.increaseIdx(vm.index.intValue)
                        })
                        .constrainAs(topFwd) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        })

                Text(
                    text = vm.chosenBreed.value.dogBreed,
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                        fontWeight = FontWeight(400),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        drawStyle = Stroke(width = 20f, join = StrokeJoin.Round)
                    ),
                    modifier = Modifier
                        .requiredHeight(100.dp)
                        .requiredWidth(300.dp)
                        .constrainAs(titleBck) {
                            start.linkTo(parent.start)
                            top.linkTo(titleTopGuide.bottom)
                            end.linkTo(parent.end)
                            width = Dimension.wrapContent
                        },
                )
                Text(
                    text = vm.chosenBreed.value.dogBreed,
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF779E6D),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .requiredHeight(100.dp)
                        .requiredWidth(300.dp)
                        .constrainAs(titleFwd) {
                            start.linkTo(parent.start)
                            top.linkTo(titleTopGuide.bottom)
                            end.linkTo(parent.end)
                            width = Dimension.wrapContent
                        },
                )

                Image(painter = painterResource(vm.chosenBreed.value.img),
                    contentDescription = "null",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .requiredHeight(252.dp)
                        .requiredWidth(360.dp)
                        .constrainAs(breedImg) {
                            start.linkTo(parent.start)
                            top.linkTo(titleFwd.bottom)
                            end.linkTo(parent.end)
                            width = Dimension.wrapContent
                        })

                Box(modifier = Modifier
                    .height(27.dp)
                    .constrainAs(descGap) {
                        top.linkTo(breedImg.bottom)
                    })

                //list
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 28.dp, end = 22.dp)
                        .requiredHeight(1450.dp)
                        .constrainAs(descListView) {
                            start.linkTo(parent.start)
                            top.linkTo(descGap.bottom)
                            bottom.linkTo(homeBTN.top)
                            width = Dimension.matchParent
                        }
                ) {
                    itemsIndexed(
                        items = vm.chosenBreed.value.description
                    ) { idx, dogItem ->
                        if (idx % 2 == 0) {
                            // title style
                            Text(
                                text = dogItem,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight(900),
                                    color = Color(0xFF000000),
                                )
                            )
                        } else {
                            // normal style
                            Text(
                                text = dogItem,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                )
                            )
                        }
                    }
                }

                Image(painter = painterResource(R.drawable.home_btn),
                    contentDescription = "null",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .requiredHeight(52.dp)
                        .requiredWidth(51.dp)
                        .clickable(true, onClick = {
                            navigation.navigate("MenuScreen")
                        })
                        .constrainAs(homeBTN) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(bottomGap.top)
                        })

                Image(painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "null",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .requiredHeight(48.dp)
                        .requiredWidth(48.dp)
                        .padding(start = 16.dp, bottom = 16.dp)
                        .clickable(true, onClick = {
                            vm.decreaseIdx(vm.index.intValue)
                        })
                        .constrainAs(bottomBck) {
                            start.linkTo(parent.start)
                            top.linkTo(homeBTN.top)
                            bottom.linkTo(homeBTN.bottom)
                        })

                Image(painter = painterResource(R.drawable.ic_fwd_arrow),
                    contentDescription = "null",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .requiredHeight(48.dp)
                        .requiredWidth(48.dp)
                        .padding(end = 16.dp, bottom = 16.dp)
                        .clickable(true, onClick = {
                            vm.increaseIdx(vm.index.intValue)
                        })
                        .constrainAs(bottomFwd) {
                            end.linkTo(parent.end)
                            top.linkTo(homeBTN.top)
                            bottom.linkTo(homeBTN.bottom)
                        })

                Box(modifier = Modifier
                    .height(27.dp)
                    .constrainAs(bottomGap) {
                        bottom.linkTo(parent.bottom)
                    })
            }
        }
    }
}