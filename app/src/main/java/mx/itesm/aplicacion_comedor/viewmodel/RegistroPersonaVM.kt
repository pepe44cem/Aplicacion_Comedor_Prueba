package mx.itesm.aplicacion_comedor.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.aplicacion_comedor.model.bd_global.Llamadas
import mx.itesm.aplicacion_comedor.model.bd_global.Retro
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.idusuario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.nuevoUsuario
import mx.itesm.aplicacion_comedor.model.others.QR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroPersonaVM : ViewModel() {

    val descargaAPI = Retro().createRetrofit()
    val id = MutableLiveData<Int>()
    val error = MutableLiveData<String>()

    fun descargarServicios(nombre: String, apellido: String,
                           curp : String, sexo : String,
                           fecha : String){

        val usuario = nuevoUsuario(nombre, apellido, curp, sexo, fecha)
        val call = descargaAPI.agregarUsuario(usuario)

        call.enqueue(object: Callback<idusuario>{
            override fun onResponse(call: Call<idusuario>, response: Response<idusuario>) {
                if(response.isSuccessful){
                    id.value = response.body()?.idusuario
                }else{
                    Log.d("TAG", "Código de respuesta: ${response.code()}")
                    Log.d("TAG", "Código de mensaje: ${response.message()}")
                    error.value = "Error al conectar con el servidor" + response.message() + ": " + response.code()
                }
            }
            override fun onFailure(call: Call<idusuario>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
            }
        })
    }


}