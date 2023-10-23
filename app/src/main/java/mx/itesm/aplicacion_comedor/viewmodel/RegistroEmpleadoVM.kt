package mx.itesm.aplicacion_comedor.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.aplicacion_comedor.model.bd_global.Retro
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Asistencia
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdAsistencia
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdVoluntario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Voluntario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Autor : Jose Antonio Moreno :)
 * Clase que representa a la VIEW MODEL en la arquitectura MVVM
 * Se encarga de controlar la interaccion entre la VISTA y el MODELO,
 * sobretodo manda a llamar y maneja el resultado las funciones que llaman a al BD
 * que registran empleados.
 */

class RegistroEmpleadoVM : ViewModel() {
    val descargaAPI = Retro().createRetrofit()
    val id = MutableLiveData<String>()
    val error = MutableLiveData<String>()
    val idasistencia = MutableLiveData<Int>()
    fun agrgarAsistencia(comedor : Int, curp : String){
        val asistencia = Asistencia(comedor, curp)
        val call = descargaAPI.agregarAsistencia(asistencia)

        call.enqueue(object : Callback<IdAsistencia>{
            override fun onResponse(call: Call<IdAsistencia>, response: Response<IdAsistencia>) {
                if(response.isSuccessful){
                    idasistencia.value = response.body()?.idasistencia
                }else{
                    Log.d("TAG", "Código de respuesta: ${response.code()}")
                    Log.d("TAG", "Código de mensaje AAAA: ${response.message()}")
                    error.value = "Error AL conectar con el servidor" + response.message() + ": " + response.code()
                }
            }

            override fun onFailure(call: Call<IdAsistencia>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
            }
        })
    }
    fun agregarEmpleado(nombre : String, apellido : String,
                        curp : String, telefono : String){
        val vol = Voluntario(curp, nombre, apellido, telefono)
        val call = descargaAPI.agregarVoluntario(vol)

        call.enqueue(object : Callback<IdVoluntario>{
            override fun onResponse(call: Call<IdVoluntario>, response: Response<IdVoluntario>) {
                if(response.isSuccessful){
                    id.value = response.body()?.idvoluntario
                }else{
                    Log.d("TAG", "Código de respuesta: ${response.code()}")
                    Log.d("TAG", "Código de mensaje: ${response.message()}")
                    error.value = "Error AL conectar con el servidor" + response.message() + ": " + response.code()
                }
            }
            override fun onFailure(call: Call<IdVoluntario>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
            }
        })

    }
}