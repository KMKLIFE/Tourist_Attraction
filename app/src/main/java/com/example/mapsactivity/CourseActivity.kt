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
    var resultCourse = ArrayList<TripSpotVO>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tripspots = intent.getSerializableExtra("list") as ArrayList<TripSpotVO>

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContentView(R.layout.activity_course)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        locationInit()


        val resultList = getCourse(tripspots)
        showCourse( resultList)

        for(i in 0 until tripspots.size)
        {
            Log.d("CourseActivity",(tripspots[i].Name)+"|||||"+tripspots[i].Category)
        }

        for( i in 0 until tripspots.size){
            Log.d("list","list index:" + i +" " + tripspots[i].isChecked.toString())
        }


        resultCourse = getCourse(tripspots)
        showCourse(resultCourse)





    }

    private fun showCourse(resultList: ArrayList<TripSpotVO>) {
        val size = resultList.size


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
            1->{
                layout1.visibility = View.VISIBLE
                location1.setImageResource(getImg(resultList[0].Name))
                locatiionText1.text = resultList[0].Name
            }
            2->{
                layout1.visibility = View.VISIBLE
                location1.setImageResource(getImg(resultList[0].Name))
                locatiionText1.text = resultList[0].Name
                layout2.visibility = View.VISIBLE
                layout3.visibility = View.VISIBLE
                location2.setImageResource(getImg(resultList[1].Name))
                locatiionText2.text = resultList[1].Name
            }
            3->{
                layout1.visibility = View.VISIBLE
                location1.setImageResource(getImg(resultList[0].Name))
                locatiionText1.text = resultList[0].Name
                layout2.visibility = View.VISIBLE
                layout3.visibility = View.VISIBLE
                location2.setImageResource(getImg(resultList[1].Name))
                locatiionText2.text = resultList[1].Name
                layout4.visibility = View.VISIBLE
                layout5.visibility = View.VISIBLE
                location3.setImageResource(getImg(resultList[2].Name))
                locatiionText3.text = resultList[2].Name
            }
            4->{
                layout1.visibility = View.VISIBLE
                location1.setImageResource(getImg(resultList[0].Name))
                locatiionText1.text = resultList[0].Name
                layout2.visibility = View.VISIBLE
                layout3.visibility = View.VISIBLE
                location2.setImageResource(getImg(resultList[1].Name))
                locatiionText2.text = resultList[1].Name
                layout4.visibility = View.VISIBLE
                layout5.visibility = View.VISIBLE
                location3.setImageResource(getImg(resultList[2].Name))
                locatiionText3.text = resultList[2].Name
                layout6.visibility = View.VISIBLE
                layout7.visibility = View.VISIBLE
                location4.setImageResource(getImg(resultList[3].Name))
                locatiionText4.text = resultList[3].Name
            }
            5->{
                layout1.visibility = View.VISIBLE
                location1.setImageResource(getImg(resultList[0].Name))
                locatiionText1.text = resultList[0].Name
                layout2.visibility = View.VISIBLE
                layout3.visibility = View.VISIBLE
                location2.setImageResource(getImg(resultList[1].Name))
                locatiionText2.text = resultList[1].Name
                layout4.visibility = View.VISIBLE
                layout5.visibility = View.VISIBLE
                location3.setImageResource(getImg(resultList[2].Name))
                locatiionText3.text = resultList[2].Name
                layout6.visibility = View.VISIBLE
                layout7.visibility = View.VISIBLE
                location4.setImageResource(getImg(resultList[3].Name))
                locatiionText4.text = resultList[3].Name
                layout8.visibility = View.VISIBLE
                layout9.visibility = View.VISIBLE
                location5.setImageResource(getImg(resultList[4].Name))
                locatiionText5.text = resultList[4].Name
            }
            else-> return
        }
    }

    private fun getImg( name: String): Int {
        when (name) {
            "경복궁" -> return R.drawable.gyeongbokgung
            "국립박물관" -> return R.drawable.folkmuseum
            "청계천" -> return R.drawable.heonggyecheon
            "낙상공원" -> return R.drawable.naksangpark
            "신라호텔" -> return R.drawable.sinrastay
            "오라카이 호텔" -> return R.drawable.oracai
            "센터마크 호텔" -> return R.drawable.centermark
            "메이플 호텔" -> return R.drawable.maple
            else->return 0
        }
    }

    private fun getCourse(nodeList: ArrayList<TripSpotVO>): ArrayList<TripSpotVO> {
        val resultList = ArrayList<TripSpotVO>()

        //알고리즘 수행
        for( i in 0 until nodeList.size ){
            if( nodeList[i].isChecked){
                resultList.add(nodeList[i])
            }
        }

        return resultList
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
                latitude = resultCourse[0].Coordinate_x / 1000000.0
                longitude = resultCourse[0].Coordinate_y / 1000000.0
                val latLng = LatLng(latitude, longitude)

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
                makeMoveLine()
                Log.d("CourseActivity", "위도: $latitude, 경도: $longitude")
            }
        }
    }

    private fun makeMoveLine(){
        var node = LatLng(0.0,0.0)

        for( i in 0 until resultCourse.size ){
            node = LatLng( resultCourse[i].Coordinate_x / 1000000.0, resultCourse[i].Coordinate_y / 1000000.0)
            polylineOptions.add(node)
            mMap.addMarker(MarkerOptions().position(node).title(resultCourse[i].Name))
        }
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
