package mx.itesm.aplicacion_comedor.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.aplicacion_comedor.model.bd_global.Retro
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Condiciones
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdUsuario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.NuevoUsuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Autor : Jose Antonio Moreno :)
 * Clase que representa a la VIEW MODEL en la arquitectura MVVM
 * Se encarga de controlar la interaccion entre la VISTA y el MODELO,
 * sobretodo manda a llamar y maneja el resultado las funciones que llaman a al BD
 * que agregan las personas nuevas.
 */

class RegistroPersonaVM : ViewModel() {

    val descargaAPI = Retro().createRetrofit()
    val id = MutableLiveData<Int>()
    val error = MutableLiveData<String>()
    val lst = MutableLiveData<List<String>>()


    fun solicitarListaVulneravilidades(){
        val call = descargaAPI.listaCondiciones()
        call.enqueue(object : Callback<Condiciones>{
            override fun onResponse(call: Call<Condiciones>, response: Response<Condiciones>) {
                lst.value = response.body()?.user
            }

            override fun onFailure(call: Call<Condiciones>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
            }
        })
    }
    //Agrega un nuevo usuario
    fun descargarServicios(nombre: String, apellido: String,
                           curp : String, sexo : String,
                           fecha : String){

        val usuario = NuevoUsuario(nombre, apellido, curp, sexo, fecha)
        val call = descargaAPI.agregarUsuario(usuario)

        call.enqueue(object: Callback<IdUsuario>{
            override fun onResponse(call: Call<IdUsuario>, response: Response<IdUsuario>) {
                if(response.isSuccessful){
                    id.value = response.body()?.idusuario
                }else{
                    Log.d("TAG", "Código de respuesta: ${response.code()}")
                    Log.d("TAG", "Código de mensaje AAAA: ${response.message()}")
                    error.value = "Error AL conectar con el servidor" + response.message() + ": " + response.code()
                }
            }
            override fun onFailure(call: Call<IdUsuario>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
            }
        })
    }


}