package eu.tribe

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import eu.tribe.testapp.R
import eu.tribe.domain.domain.model.CityJson
import eu.tribe.domain.domain.model.CityEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

class RootActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private val arrayOfPoints = mutableListOf<CityJson>()
    var disposable = Disposables.disposed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        disposable = Single.just(getMyPoints())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                arrayOfPoints.clear()
                arrayOfPoints.addAll(it)
                if (this::map.isInitialized) {
                    renderUiMarkers()
                }
            }
        addOnClickListener {
            Log.d("UnicumTest", "sdsdsdsdsdsdsdsd")
        }
    }

    fun addOnClickListener(clickListener:()-> Unit) {
        Handler().postDelayed({ clickListener.invoke() }, 5000)
    }

    private fun renderUiMarkers() {
        arrayOfPoints.map {
            val address = LatLng(it.lat, it.lng)
            map.addMarker(MarkerOptions().position(address).title(it.name))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(address, 12f))
        }
    }

    private fun getMyPoints(): List<CityJson> {
        val raw: InputStream = resources.openRawResource(R.raw.myJsons)
        val rd: Reader = BufferedReader(InputStreamReader(raw))
        val gson = Gson()
        val test = gson.fromJson(rd, CityEntity::class.java)
        return test.cities
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        renderUiMarkers()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}

