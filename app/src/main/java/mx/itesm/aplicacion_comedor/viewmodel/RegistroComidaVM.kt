package mx.itesm.aplicacion_comedor.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.zxing.integration.android.IntentIntegrator
import mx.itesm.aplicacion_comedor.model.bd_global.Llamadas
import mx.itesm.aplicacion_comedor.model.bd_global.Retro
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdVisita
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.NuevaComidaCURP
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.nuevaComidaCodigo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroComidaVM : ViewModel() {

    val descargaAPI = Retro().createRetrofit()
    val id = MutableLiveData<Int>()
    val error = MutableLiveData<String>()
    fun agregarConCodigo(codigo : Int, comedor : Int){
        Log.d("TAG", "SI ENTRO")
        val nuevaComidaCodigo = nuevaComidaCodigo(codigo, comedor)
        val call = descargaAPI.agregarComidaCodigo(nuevaComidaCodigo)

        call.enqueue(object : Callback<IdVisita>{
            override fun onResponse(call: Call<IdVisita>, response: Response<IdVisita>) {
                if(response.isSuccessful){
                    id.value = response.body()?.idVisita
                }else{
                    Log.d("TAG", "Código de respuesta: ${response.code()}")
                    Log.d("TAG", "Código de mensaje: ${response.message()}")
                    error.value = "Error " + response.message() + ": " + response.code()
                }
            }
            override fun onFailure(call: Call<IdVisita>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
            }
        })
    }

    fun agregarConCURP(curp : String, comedor : Int){
        val nuevaComidaCURP = NuevaComidaCURP(curp, comedor)
        val call = descargaAPI.agregarComidaCURP(nuevaComidaCURP)

        call.enqueue(object : Callback<IdVisita>{
            override fun onResponse(call: Call<IdVisita>, response: Response<IdVisita>) {
                if(response.isSuccessful){
                    id.value = response.body()?.idVisita
                }else{
                    Log.d("TAG", "Código de respuesta: ${response.code()}")
                    Log.d("TAG", "Código de mensaje: ${response.message()}")
                    error.value = "Error " + response.message() + ": " + response.code()
                }
            }
            override fun onFailure(call: Call<IdVisita>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
            }
        })
    }


}