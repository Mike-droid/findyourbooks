package com.itpn.findyourbooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val intentLocation = intent.getStringExtra("localizacion")

        when(intentLocation){
            "Quis Company"->{
                val monterrey = LatLng(25.6714,-100.309)
                mMap.addMarker(MarkerOptions().position(monterrey).title("Monterrey"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(monterrey))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(monterrey, 12f))
            }
            "Dictum Cursus Industries"->{
                val piedrasNegras = LatLng(28.7,-100.523)
                mMap.addMarker(MarkerOptions().position(piedrasNegras).title("Piedras Negras"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(piedrasNegras))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(piedrasNegras, 12f))
            }
            "Aliquet Lobortis Nisi"->{
                val saltillo = LatLng(25.433333,-101.0)
                mMap.addMarker(MarkerOptions().position(saltillo).title("Saltillo"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(saltillo))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(saltillo, 12f))
            }
            "Nunc In At LLP"->{
                val guadalajara = LatLng(20.67,-103.34)
                mMap.addMarker(MarkerOptions().position(guadalajara).title("Guadalajara"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(guadalajara))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(guadalajara, 12f))
            }
            "laculis Enim Sit"->{
                val cdmx = LatLng(19.49,-99.12)
                mMap.addMarker(MarkerOptions().position(cdmx).title("Ciudad de México"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(cdmx))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cdmx, 12f))
            }
            "Pede Ultrices A LLP"->{
                val chihuahua = LatLng(28.63,-106.08)
                mMap.addMarker(MarkerOptions().position(chihuahua).title("Chihuahua"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(chihuahua))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(chihuahua, 12f))
            }
            "Proin Inc."->{
                val london = LatLng(51.50,-0.12)
                mMap.addMarker(MarkerOptions().position(london).title("London"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(london))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(london, 12f))
            }
            "Sociis Natoque Inc."->{
                val madrid = LatLng(40.41,-3.70)
                mMap.addMarker(MarkerOptions().position(madrid).title("Madrid"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(madrid))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(madrid, 12f))
            }
            "Elit PC"->{
                val birmingham = LatLng(52.48,-1.89)
                mMap.addMarker(MarkerOptions().position(birmingham).title("Birmingham"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(birmingham))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(birmingham, 12f))
            }
        }

    }
}