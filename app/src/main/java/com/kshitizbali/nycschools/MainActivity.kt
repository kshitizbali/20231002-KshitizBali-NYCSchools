package com.kshitizbali.nycschools

import android.Manifest
import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kshitizbali.nycschools.domain.School
import com.kshitizbali.nycschools.presentation.SchoolDetailState
import com.kshitizbali.nycschools.presentation.SchoolItem
import com.kshitizbali.nycschools.presentation.SchoolScreen
import com.kshitizbali.nycschools.presentation.SchoolViewModel
import com.kshitizbali.nycschools.ui.theme.NYCSchoolsTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.RowScopeInstance.align
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.kshitizbali.nycschools.domain.util.Screen
import com.kshitizbali.nycschools.presentation.SchoolDetailScreen
import com.kshitizbali.nycschools.presentation.SchoolDetailViewModel
import com.kshitizbali.nycschools.presentation.navigationStack

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel : SchoolViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!viewModel.checkInternetAvailability(context = this)) {
            Toast.makeText(this,"Unable to connect to the internet.",Toast.LENGTH_SHORT).show()
        }

        setContent {
            NYCSchoolsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val schoolDetailViewModel: SchoolDetailViewModel by viewModels()
                    val schools = viewModel.schoolPagingFlow.collectAsLazyPagingItems()
                    navigationStack(schools = schools, schoolDetailViewModel)
                }
            }
        }
    }
}