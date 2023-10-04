package com.kshitizbali.nycschools.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.kshitizbali.nycschools.R
import com.kshitizbali.nycschools.domain.School
import com.kshitizbali.nycschools.domain.util.Screen
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kshitizbali.nycschools.ui.theme.NYCSchoolsTheme

@Composable
fun navigationStack(schools : LazyPagingItems<School>, viewModel: SchoolDetailViewModel){

    val navController = rememberNavController()
    val actions = remember(navController) { AppActions(navController) }
    //val viewModel = hiltViewModel<SchoolDetailViewModel>()
    NavHost(
        navController = navController,
        startDestination = Screen.SchoolsListScreen.route
    ) {
        composable(Screen.SchoolsListScreen.route) { entry ->
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                SchoolsList(schools = schools, selectedSchool = actions.selectedSchool)
            }
        }
        composable(Screen.SchoolDetailScreen.route+"/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
                nullable = true
            }) ) {entry ->
            val schoolId = entry.arguments?.getString("id")
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                if(schoolId != null){
                    viewModel.loadSchoolDetails(schoolId = schoolId)
                }
                SchoolDetailScreen(state = viewModel.schoolDetailState, navigateUp = actions.navigateUp ,modifier = Modifier)
            }
        }
    }
}

@Composable
fun SchoolsList(schools : LazyPagingItems<School>, selectedSchool: (String?) -> Unit) {
    Scaffold(
        topBar = {
            Row(horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_round),
                    contentDescription = stringResource(R.string.app_name),
                    modifier = Modifier
                        .size(88.dp)
                        .padding(top = 16.dp)
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    ) { innerPadding ->
        SchoolScreen(schools = schools, selectedSchool ,modifier = Modifier.padding(innerPadding) )
    }
}

@Composable
fun SchoolScreen(
    schools: LazyPagingItems<School>,
    selectedSchool: (String?) -> Unit,
    modifier: Modifier
) {
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    LaunchedEffect(key1 = schools.loadState) {
        if(schools.loadState.refresh is LoadState.Error){
            Toast.makeText(context,
            "Error: "+ "Unable to connect to internet.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        if(schools.loadState.refresh is LoadState.Loading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else{
            LazyColumn(modifier =  Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
                state = scrollState){
                items(schools){ school ->
                    if(school != null){
                        SchoolItem(school = school, selectedSchool ,modifier = Modifier
                            .fillMaxWidth())
                    }
                }
                item{
                    if(schools.loadState.append is LoadState.Loading){
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

private class AppActions(
    navController: NavController
){
    val selectedSchool: (String?) -> Unit = {Id: String? ->
        navController.navigate(Screen.SchoolDetailScreen.withArgs(Id))
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}