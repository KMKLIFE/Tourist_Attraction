package com.example.mapsactivity

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import android.graphics.Point
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_course.*


class CourseActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private val polylineOptions = PolylineOptions().width(5f).color(Color.RED)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tripspotList = intent.getSerializableExtra("list") as ArrayList<TripSpotVO>

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContentView(R.layout.activity_course)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        locationInit()

        val nodeList = getNodes()
        val resultList = getCourse(nodeList)
        showCourse( resultList)

        for(i in 0..tripspotList.size-1)
        {
            Log.d("CourseActivity",(tripspotList[i].Name)+"|||||"+tripspotList[i].Categoy)
        }




        //ConnectToServer().getHotelList()




    }

    private fun showCourse(resultList: ArrayList<String>) {
        //val size = resultList.size
        val size = 2

        layout1.visibility = View.INVISIBLE
        layout2.visibility = View.INVISIBLE
        layout3.visibility = View.INVISIBLE
        layout4.visibility = View.INVISIBLE
        layout5.visibility = View.INVISIBLE
        layout6.visibility = View.INVISIBLE
        layout7.visibility = View.INVISIBLE
        layout8.visibility = View.INVISIBLE
        layout9.visibility = View.INVISIBLE

        when(size){
            0->return
            1->{
                layout1.visibility = View.VISIBLE
                location1.setImageResource(R.drawable.oracai)
                locatiionText1.text = "오라카이"
            }
            2->{
                layout1.visibility = View.VISIBLE
                location1.setImageResource(R.drawable.oracai)
                locatiionText1.text = "오라카이"
                layout2.visibility = View.VISIBLE
                layout3.visibility = View.VISIBLE
                location2.setImageResource(R.drawable.oracai)
                locatiionText2.text = "오라카이2"

            }
        }


    }

    private fun getCourse(nodeList: ArrayList<String>): ArrayList<String> {
        val resultList = ArrayList<String>()




        resultList.add(nodeList[0])
        resultList.add(nodeList[2])
        resultList.add(nodeList[1])
        resultList.add(nodeList[3])
        return resultList
    }

    private fun getNodes(): ArrayList<String> {
        val nodeList = ArrayList<String>()

        nodeList.add("지역1")
        nodeList.add("지역3")
        nodeList.add("지역2")
        nodeList.add("지역4")


        return nodeList
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

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        //val sydney = LatLng(37.561542, 126.817357)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13f))
        //onResume()
    }

    private fun locationInit(){
        fusedLocationProviderClient = FusedLocationProviderClient(this)
        locationCallback = MyLocationCallback()
        locationRequest = LocationRequest()

        locationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
    }

    override fun onResume() {
        super.onResume()
        permissionCheck( cancel = {showPermissionInfoDialog()
        }, ok = {addLocationListener()})
        setLayouts()
    }

    private fun setLayouts() {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y

        var  param = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height / 10)
        linearLayout1.layoutParams = param
        param = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height * 6/ 10)
        linearLayout2.layoutParams = param
        param = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height *2/ 10)
        linearLayout3.layoutParams = param
    }


    private fun addLocationListener(){
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    inner class MyLocationCallback : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            val location = locationResult?.lastLocation
            location?.run {
                //val latLng = LatLng(latitude, longitude)
                latitude = 37.561542
                longitude = 126.817357
                val latLng = LatLng(latitude, longitude)

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
                makeMoveLine()
                //Log.d("CourseActivity", "위도: $latitude, 경도: $longitude")
            }
        }
    }

    private fun makeMoveLine(){
        var node = LatLng(0.0,0.0)

        node = LatLng( 37.561, 126.817)
        polylineOptions.add(node)
        node = LatLng( 37.581, 126.817)
        polylineOptions.add(node)
        node = LatLng( 37.581, 126.837)
        polylineOptions.add(node)
        mMap.addPolyline(polylineOptions)
    }

    private val REQUEST_ACCESS_FINE_LOCATION = 1000
    private fun permissionCheck(cancel: () -> Unit, ok: () -> Unit){
        if( ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            if( ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                cancel()
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_ACCESS_FINE_LOCATION)
            }
        }else{
            ok()
        }
    }
    private fun showPermissionInfoDialog(){
        alert ( "현재 위치 정보를 얻으려면 위치 권한이 필요합니다", "권한이 필요한 이유" ){
            yesButton {
                ActivityCompat.requestPermissions(this@CourseActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
                    , REQUEST_ACCESS_FINE_LOCATION)
            }
            noButton {  }
        }.show()
    }

    override fun onPause() {
        super.onPause()
        removeLocationListener()
    }

    private fun removeLocationListener() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_ACCESS_FINE_LOCATION->{
                if((grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED)){
                    addLocationListener()
                }else{
                    toast("권한 거부 됨")
                }
                return
            }
        }
    }
}
