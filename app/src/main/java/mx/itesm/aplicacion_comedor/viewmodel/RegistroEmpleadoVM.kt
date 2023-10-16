package mx.itesm.aplicacion_comedor.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.aplicacion_comedor.model.bd_global.Llamadas
import mx.itesm.aplicacion_comedor.model.bd_global.Retro
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdAsistencia
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.idvoluntario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.nuevoVoluntario
import mx.itesm.aplicacion_comedor.model.others.QR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegistroEmpleadoVM : ViewModel() {

    val descargaAPI = Retro().createRetrofit()
    val id = MutableLiveData<Int>()
    val error = MutableLiveData<String>()

    fun descargarServicios(nombre: String, apellido: String,
                           curp : String, sexo : String,
                           fecha : String){

        val usuario = nuevoVoluntario(nombre, apellido, curp, sexo, fecha)
        val call = descargaAPI.agregarVoluntario(usuario)

        call.enqueue(object: Callback<IdAsistencia>{
            override fun onResponse(call: Call<idvoluntario>, response: Response<idvoluntario>) {
                if(response.isSuccessful){
                    id.value = response.body()?.idvoluntario
                }else{
                    Log.d("TAG", "Código de respuesta: ${response.code()}")
                    Log.d("TAG", "Código de mensaje: ${response.message()}")
                    error.value = "Error al conectar con el servidor" + response.message() + ": " + response.code()
                }
            }
            override fun onFailure(call: Call<idvoluntario>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
            }
        })
    }

}