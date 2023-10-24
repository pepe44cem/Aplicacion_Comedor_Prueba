package mx.itesm.aplicacion_comedor.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.aplicacion_comedor.model.bd_global.Retro
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdReporte
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Reporte
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Autor : Jose Antonio Moreno :)
 * Clase que representa a la VIEW MODEL en la arquitectura MVVM
 * Se encarga de controlar la interaccion entre la VISTA y el MODELO,
 * sobretodo manda a llamar y maneja el resultado las funciones que llaman a al BD
 * que agregan los reportes.
 */

class ReportesVM : ViewModel() {
    val descargaAPI = Retro().createRetrofit()

    val id = MutableLiveData<Int>()
    val error = MutableLiveData<String>()

    fun agrergarReporte(comedor : Int, descripcion : String){
        val report = Reporte(comedor, descripcion)
        val call = descargaAPI.insertarReporte(report)

        call.enqueue(object : Callback<IdReporte>{
            override fun onResponse(call: Call<IdReporte>, response: Response<IdReporte>) {
                if(response.isSuccessful){
                    id.value = response.body()?.idReporte
                }else{
                    Log.d("TAG", "Código de respuesta: ${response.code()}")
                    Log.d("TAG", "Código de mensaje: ${response.message()}")
                    error.value = "Error al conectar con el servidor" + response.message() + ": " + response.code()
                }
            }

            override fun onFailure(call: Call<IdReporte>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
                Log.d("TAG", "FALLO PEOR")
            }
        })

    }
}