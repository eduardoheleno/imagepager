package com.example.imagepager.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.lang.Exception
import java.net.InetAddress

class NetworkHandle {
    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
    }

    fun isInternetAvailable(): Boolean {
        return try {
            val timeoutMs = 10000
            val socket = InetAddress.getByName("www.google.com.br").isReachable(timeoutMs)
            socket
        } catch (e: Exception) {
            false
        }
    }
}