package com.kshitizbali.nycschools.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kshitizbali.nycschools.R
import com.kshitizbali.nycschools.domain.School
import com.kshitizbali.nycschools.domain.SchoolDetail
import com.kshitizbali.nycschools.ui.theme.NYCSchoolsTheme
import kotlinx.coroutines.delay

/**
 * School detail screen composable.
 * NOTE: Included a delay to simulate data loading. A delay of 2 sec to be exact.
 */
@Composable
fun SchoolDetailScreen(
    state: SchoolDetailState,
    navigateUp: () -> Unit,
    modifier: Modifier
) {
    val isLoading = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(isLoading) {
        delay(2000)
        isLoading.value = false
    }

    if(isLoading.value){
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }else{
        Box {
            Image(
                painter = painterResource(id = R.drawable.school_photo),
                contentDescription = stringResource(R.string.school_location),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentScale = ContentScale.Crop
            )
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                contentColor = Color.White
            ) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(R.string.school_location),
                        tint = Color.Black
                    )
                }
            }
        }
        Card(
            modifier = modifier,
            elevation = 4.dp
        ) {

            if (state.schoolDetail != null) {
                state.schoolDetail.let { schoolDetail ->

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = schoolDetail.schoolName ?: "",
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp)
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.sat),
                                    contentDescription = stringResource(id = R.string.school_location),
                                    modifier = Modifier
                                        .height(30.dp)
                                )

                                Text(
                                    text = "SAT Math Avg Score: ${schoolDetail.satMathAvgScore ?: ""}",
                                    fontStyle = FontStyle.Italic,
                                    color = Color.DarkGray,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.CenterVertically),
                                    fontSize = 14.sp,
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.sat),
                                    contentDescription = stringResource(id = R.string.school_location),
                                    modifier = Modifier
                                        .height(30.dp)
                                )

                                Text(
                                    text = "SAT Writing Avg Score: ${schoolDetail.satWritingAvgScore ?: ""}",
                                    fontStyle = FontStyle.Italic,
                                    color = Color.DarkGray,
                                    fontSize = 14.sp,
                                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.sat),
                                    contentDescription = stringResource(id = R.string.school_location),
                                    modifier = Modifier
                                        .height(30.dp)
                                )

                                Text(
                                    text = "SAT Reading Avg Score: ${schoolDetail.satReadingAvgScore ?: ""}",
                                    fontStyle = FontStyle.Italic,
                                    color = Color.DarkGray,
                                    fontSize = 14.sp,
                                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.sat),
                                    contentDescription = stringResource(id = R.string.school_location),
                                    modifier = Modifier
                                        .height(30.dp)
                                )

                                Text(
                                    text = "No of Test Takers: ${schoolDetail.noOfTestTakers ?: ""}",
                                    fontStyle = FontStyle.Italic,
                                    color = Color.DarkGray,
                                    fontSize = 14.sp,
                                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                )
                            }
                        }
                    }

                }
            } else {
                if (state.isLoading) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = state.error ?: "Loading...",
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = state.error ?: "No details found for the school.",
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                }

            }
        }

    }
}

@Preview
@Composable
fun SchoolDetailScreenPreview() {
    NYCSchoolsTheme {
        SchoolDetailScreen(
            state = SchoolDetailState(
                schoolDetail = SchoolDetail(
                    dbn = "08X282", schoolName = "NYC SCHOOL", noOfTestTakers = "123",
                    satReadingAvgScore = "777", satMathAvgScore = "777", satWritingAvgScore = "777"
                ), isLoading = false, error = null
            ), navigateUp = { }, modifier = Modifier.fillMaxWidth()
        )
    }
}