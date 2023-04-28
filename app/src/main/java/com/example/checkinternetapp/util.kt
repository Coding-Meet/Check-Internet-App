package com.example.checkinternetapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun isConnected(context: Context):Boolean{
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return when{
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val cap = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            when {
                cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
        else -> {
            // use Deprecated method only on older devices
            val activeNetwork = connectivityManager.activeNetworkInfo ?: return false
            return when (activeNetwork.type){
                ConnectivityManager.TYPE_MOBILE -> true
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_VPN -> true
                else -> false
            }
        }
    }
}