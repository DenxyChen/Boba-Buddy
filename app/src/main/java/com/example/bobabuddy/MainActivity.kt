package com.example.bobabuddy

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var tvCoordinates: TextView
    private lateinit var btnFindCoordinates: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        tvCoordinates = findViewById(R.id.tvCoordinates)
        btnFindCoordinates = findViewById(R.id.btnFindCoordinates)

        btnFindCoordinates.setOnClickListener {
            tvCoordinates.setText(getLocation().toString())
        }
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(TAG, "Permission not granted")
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if(location != null) {
                Toast.makeText(this, "${location.latitude}, ${location.longitude}", Toast.LENGTH_SHORT).show()
                tvCoordinates.setText("${location.latitude}, ${location.longitude}")
            }
            else {
                Toast.makeText(this, "Location is null", Toast.LENGTH_SHORT).show()
            }

        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}