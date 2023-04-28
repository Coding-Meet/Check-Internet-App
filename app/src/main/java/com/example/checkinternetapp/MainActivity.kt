package com.example.checkinternetapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val internetTxt = findViewById<TextView>(R.id.internetTxt)
        val internetImg = findViewById<ImageView>(R.id.internetImg)
        val rootView = findViewById<LinearLayout>(R.id.rootView)

        val snackbar = Snackbar.make(
            rootView,
            "No Internet Connection",
            Snackbar.LENGTH_INDEFINITE
        ).setAction("Setting") {
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        }
        val checkInternetBtn = findViewById<Button>(R.id.checkInternetBtn)
        checkInternetBtn.setOnClickListener {
            if (isConnected(this)) {
                internetImg.setImageResource(R.drawable.ava_signal)
                internetTxt.text = "Internet is Available"
                if (snackbar.isShown) {
                    snackbar.dismiss()
                }
            } else {
                internetImg.setImageResource(R.drawable.no_signal)
                internetTxt.text = "No Internet Connection"
                snackbar.show()
            }
        }
    }
}