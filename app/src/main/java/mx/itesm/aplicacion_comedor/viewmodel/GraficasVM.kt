package mx.itesm.aplicacion_comedor.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import mx.itesm.aplicacion_comedor.model.others.Graficas
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class GraficasVM : ViewModel() {

    private val graficasModel = Graficas()

    val fechaDesdeString = MutableLiveData<String>()
    val fechaHastaString = MutableLiveData<String>()

    val yearDesde = MutableLiveData<Int>()
    val mesDesde = MutableLiveData<Int>()
    val diaDesde = MutableLiveData<Int>()

    val yearHasta = MutableLiveData<Int>()
    val mesHasta = MutableLiveData<Int>()
    val diaHasta = MutableLiveData<Int>()

    val entriesLcServicio = MutableLiveData<ArrayList<Entry>>()
    val entriesBcServicio = MutableLiveData<ArrayList<BarEntry>>()


    val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun cambiarFechaDesde(year: Int, mes: Int, dia: Int){

        fechaDesdeString.value = String.format("%d-%02d-%02d", year, mes + 1, dia)
        yearDesde.value=year
        mesDesde.value=mes
        diaDesde.value=dia

        actualizarDatosGraficas()
    }

    fun cambiarFechaHasta(year: Int, mes: Int, dia: Int){

        fechaHastaString.value = String.format("%d-%02d-%02d", year, mes + 1, dia)
        yearHasta.value=year
        mesHasta.value=mes
        diaHasta.value=dia

        actualizarDatosGraficas()
    }

    fun actualizarDatosGraficas(){
        actualizarLineServicio()
        actualizarBarServicio()
    }

    private fun actualizarBarServicio() {
        val barValues = listOf(18f, 20f, 30f, 28f, 31f)

        val entries: ArrayList<BarEntry> = ArrayList()

        for (i in barValues.indices) {
            entries.add(BarEntry(i.toFloat(), barValues[i]))
        }

        entriesBcServicio.value=entries
    }

    private fun actualizarLineServicio() {
        val servicioAverage = List(26) { (0..500).random().toFloat() / 100 }
        val dates = arrayListOf(
            "2023-01-01",
            "2023-02-01",
            "2023-03-01",
            "2023-04-01",
            "2023-05-01",
            "2023-06-01",
            "2023-07-01",
            "2023-08-01",
            "2023-09-01",
            "2023-10-01",
            "2023-11-01",
            "2023-12-01",
            "2024-01-01",
            "2024-02-01",
            "2024-03-01",
            "2024-04-01",
            "2024-05-01",
            "2024-06-01",
            "2024-07-01",
            "2024-08-01",
            "2024-09-01",
            "2024-10-01",
            "2024-11-01",
            "2024-12-01",
            "2025-01-01",
            "2025-02-01"
        )

        val startDate = format.parse(dates[0])
        val entries = ArrayList<Entry>()
        for (i in servicioAverage.indices) {
            val currentDate = format.parse(dates[i])
            val diff = currentDate.time - startDate.time
            val daysPassed = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
            Log.e("Days Passed",daysPassed.toString())
            entries.add(Entry(daysPassed.toFloat(), servicioAverage[i]))
        }

        entriesLcServicio.value=entries
    }


}