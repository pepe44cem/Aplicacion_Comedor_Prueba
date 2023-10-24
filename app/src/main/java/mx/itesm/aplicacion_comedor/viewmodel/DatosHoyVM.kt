package mx.itesm.aplicacion_comedor.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.aplicacion_comedor.model.bd_global.Retro
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.ComedorIdRequest
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.DatosComidasHoy
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.VoluntariosAsistentesHoy
import mx.itesm.aplicacion_comedor.model.others.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

/**
 * Autor : Jose Alonso Segura
 * Clase que representa a la VIEW MODEL en la arquitectura MVVM
 * Se encarga de controlar la interaccion entre la VISTA y el MODELO,
 * sobretodo manda a llamar y maneja el resultado las funciones que llaman a al BD.
 */

class DatosHoyVM : ViewModel() {
    val descargaAPI = Retro().createRetrofit()

    val comidasTotalesHoy = MutableLiveData<Int>()
    val comidasPagadasHoy = MutableLiveData<Int>()
    val comidasDonadasHoy = MutableLiveData<Int>()
    val voluntariosAsistentesHoy = MutableLiveData<Int>()


    fun descargarDatosHoy(){
        val comedorId = ComedorIdRequest(Single.idComedor)
        val callDatosComidas = descargaAPI.obtenerDatosComidasHoy(comedorId)
        val callVoluntariosAsistentes = descargaAPI.obtenerCantidadVoluntariosHoy(comedorId)


        callDatosComidas.enqueue(object : Callback<DatosComidasHoy>{
            override fun onResponse(call: Call<DatosComidasHoy>, response: Response<DatosComidasHoy>) {
                if(response.isSuccessful){

                    Log.d("TAG", "Se hizo el guiso")
                    comidasTotalesHoy.value=response.body()?.comidasTotalesHoy
                    comidasPagadasHoy.value=response.body()?.comidasPagadasHoy
                    comidasDonadasHoy.value=response.body()?.comidasDonadas

                }
            }

            override fun onFailure(call: Call<DatosComidasHoy>, t: Throwable) {
                Log.e("TAG", "Server connection failed", t)
            }
        })

        callVoluntariosAsistentes.enqueue(object : Callback<VoluntariosAsistentesHoy>{
            override fun onResponse(call: Call<VoluntariosAsistentesHoy>, response: Response<VoluntariosAsistentesHoy>) {
                if(response.isSuccessful){

                    Log.d("TAG", "Se hizo el guiso")
                    voluntariosAsistentesHoy.value = response.body()?.cantidadVoluntariosHoy

                }
            }

            override fun onFailure(call: Call<VoluntariosAsistentesHoy>, t: Throwable) {
                Log.e("TAG", "Server connection failed", t)
            }
        })
    }

}