package com.kshitizbali.nycschools.presentation

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.kshitizbali.nycschools.ui.theme.NYCSchoolsTheme

@Composable
fun SchoolItem(
    school: School,
    selectedSchool: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable( onClick = {selectedSchool(school.dbn)}),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = "https://www.hilliardschools.org/wp-content/uploads/school.png",
                contentDescription = school.name,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = school.name ?: "",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.school_location),
                        contentDescription = stringResource(id = R.string.school_location),
                        modifier = Modifier
                            .height(30.dp)
                    )
                    Text(
                        text = school.city ?: "",
                        fontStyle = FontStyle.Italic,
                        color = Color.DarkGray,
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically),
                        fontSize = 14.sp,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.grades),
                        contentDescription = stringResource(id = R.string.school_location),
                        modifier = Modifier
                            .height(30.dp)
                    )
                    Text(
                        text = "Grades: ${school.grades}",
                        modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically),
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp,
                    )
                }


                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.school_location),
                            contentDescription = stringResource(id = R.string.school_location),
                            modifier = Modifier
                                .height(30.dp)
                        )
                        Text(
                            text = school.phone ?: "",
                            fontStyle = FontStyle.Italic,
                            color = Color.DarkGray,
                            fontSize = 14.sp,
                            modifier = Modifier.align(alignment = Alignment.CenterVertically)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.website),
                            contentDescription = stringResource(id = R.string.school_location),
                            modifier = Modifier
                                .height(30.dp)
                        )
                        var schoolEmail = "N/A";
                        if(!school.email.isNullOrBlank()){
                            schoolEmail = school.email;
                        }
                        Text(
                            text = schoolEmail,
                            fontStyle = FontStyle.Italic,
                            color = Color.DarkGray,
                            fontSize = 14.sp,
                            modifier = Modifier.align(alignment = Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SchoolItemPreview(){
    NYCSchoolsTheme {
        SchoolItem(
            school = School(id = "1", dbn = "IX0M34", name = "School", phone = "9876543210", email = "abc@abc.com", fax = "1234567890"
            , website = "www.school.com", totalStudents = "300", overview = "THis is a decription", bus = "YES",
            subway = "YES", city = "Manhattan", location = "NYC", lon = "12.12", lat = "11.11", activities = "Many"
            , sports = "Basketball", grades = "8-11"), selectedSchool = { } ,modifier = Modifier.fillMaxWidth())
    }
}