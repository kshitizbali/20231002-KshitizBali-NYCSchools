package com.kshitizbali.nycschools.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.kshitizbali.nycschools.data.local.SchoolEntity
import com.kshitizbali.nycschools.data.mappers.toSchool
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(
    pager: Pager<Int, SchoolEntity>
): ViewModel() {
    val schoolPagingFlow = pager.flow
        .map { pagingData ->
            pagingData.map { it.toSchool() }
        }.cachedIn(viewModelScope)


    /**
     * Helper method to check internet available.
     */
    fun checkInternetAvailability(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities =
            connectivityManager.activeNetwork ?: return false

        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}