package com.tailwagging.breedsguide.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.tailwagging.breedsguide.model.dummyList
import com.tailwagging.breedsguide.viewmodels.BreedVM

@Composable
fun TopBreedsScreen(navigation: NavController, vm: BreedVM) {
    val dogList = dummyList

    Box(modifier = Modifier.fillMaxSize()){
        // background layer
        ConstraintLayout {
            val (dogListBckImage, dogListBckImage2, dogListBckImage3) = createRefs()
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
        }
        // content layer
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (
                    titleTopGuide,
                    dogListTitle, dogListTitleBck, dogListView,
                    bottomBckArrow, listBottomGap, bottomFwdArrow,
                ) = createRefs()

                Box(modifier = Modifier
                    .requiredHeight(36.dp)
                    .constrainAs(titleTopGuide) {
                        top.linkTo(parent.top)
                    })

                Text(
                    text = "TOP \nBREEDS",
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
                        .requiredWidth(225.dp)
                        .constrainAs(dogListTitleBck) {
                            start.linkTo(parent.start)
                            top.linkTo(titleTopGuide.bottom)
                            end.linkTo(parent.end)
                        },
                )
                Text(
                    text = "TOP \nBREEDS",
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFE18244),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .requiredHeight(157.dp)
                        .requiredWidth(220.dp)
                        .constrainAs(dogListTitle) {
                            start.linkTo(parent.start)
                            top.linkTo(titleTopGuide.bottom)
                            end.linkTo(parent.end)
                            width = Dimension.wrapContent
                        },
                )
                //list
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 39.dp)
                        .requiredHeight(500.dp)
                        .constrainAs(dogListView) {
                            start.linkTo(parent.start)
                            top.linkTo(dogListTitle.bottom)
                            height = Dimension.wrapContent
                        }
                ) {
                    itemsIndexed(
                        items = dogList
                    ) { idx, dogItem ->
                        val title = vm.createTitle(idx, dogItem.dogBreed)
                        Text(
                            text = title,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.luckiest_guy_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF30353F),
                                textAlign = TextAlign.Center,
                            ), modifier = Modifier.clickable(true, onClick = {
                                vm.index.intValue = idx
                                vm.refreshBreed()
                                navigation.navigate("BreedDescriptionScreen")
                            })
                        )
                    }
                }

                Box(modifier = Modifier
                    .requiredHeight(46.dp)
                    .constrainAs(listBottomGap) {
                        top.linkTo(dogListView.bottom)
                    })

                Image(painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "null",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .requiredHeight(48.dp)
                        .requiredWidth(48.dp)
                        .padding(start = 16.dp, bottom = 16.dp)
                        .clickable(true, onClick = {
                            navigation.popBackStack()
                        })
                        .constrainAs(bottomBckArrow) {
                            start.linkTo(parent.start)
                            top.linkTo(listBottomGap.bottom)
                        })

                Image(painter = painterResource(R.drawable.ic_fwd_arrow),
                    contentDescription = "null",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .requiredHeight(48.dp)
                        .requiredWidth(48.dp)
                        .padding(end = 16.dp, bottom = 16.dp).clickable(true, onClick = {
                            vm.index.intValue = 0
                            navigation.navigate("BreedDescriptionScreen")
                        })
                        .constrainAs(bottomFwdArrow) {
                            end.linkTo(parent.end)
                            top.linkTo(listBottomGap.bottom)
                            height = Dimension.preferredWrapContent
                        })
            }
        }
    }
}