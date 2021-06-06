package eu.tribe.ui.singleactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import eu.tribe.data.model.CitiesJson
import eu.tribe.data.model.CityJson
import eu.tribe.domain.model.CityEntity
import eu.tribe.testapp.R
import eu.tribe.ui.cities.CitiesState
import eu.tribe.ui.cities.CitiesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


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
    }

    private fun renderUiMarkers(cities: List<CityEntity>) {
        cities.map {
            val address = LatLng(it.lat, it.lng)
            map.addMarker(MarkerOptions().position(address).title(it.name))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(address, 12f))
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        citiesViewModel.state.observe(this,{
            when(it) {
                is CitiesState.Success -> {
                    renderUiMarkers(it.cities)
                }
            }
        })
    }
}

