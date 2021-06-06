package eu.tribe.data.local

import android.content.Context
import com.google.gson.Gson
import eu.tribe.data.CitiesDataSource
import eu.tribe.data.model.CitiesJson
import eu.tribe.domain.model.CityEntity
import eu.tribe.testapp.R
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

class LocalDataSource(private val context: Context) : CitiesDataSource{
    override suspend fun getCities(): List<CityEntity> {
        val raw: InputStream = context.resources.openRawResource(R.raw.markers)
        val rd: Reader = BufferedReader(InputStreamReader(raw))
        val gson = Gson()
        val cityJson = gson.fromJson(rd, CitiesJson::class.java)
        return cityJson.cities.map {
            CityEntity(it.id, it.name, it.lat,it.lng)
        }
    }

    //todo для чего - создан класс - будет отдавать список маркеров из локального джейсона
}