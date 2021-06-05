package eu.tribe.ui.singleactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import eu.tribe.domain.domain.model.CityEntity
import eu.tribe.domain.domain.model.CityJson
import eu.tribe.testapp.R
import eu.tribe.ui.fragment.CitiesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader


class RootActivity : AppCompatActivity(), OnMapReadyCallback {

    private val citiesViewModel: CitiesViewModel by viewModel()
    private lateinit var map: GoogleMap
    private val arrayOfPoints = mutableListOf<CityJson>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        citiesViewModel.getCities()
    }

    private fun renderUiMarkers() {
        arrayOfPoints.map {
            val address = LatLng(it.lat, it.lng)
            map.addMarker(MarkerOptions().position(address).title(it.name))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(address, 12f))
        }
    }

    private fun getMyPoints(): List<CityJson> {
        val raw: InputStream = resources.openRawResource(R.raw.markers)
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
    }
}

