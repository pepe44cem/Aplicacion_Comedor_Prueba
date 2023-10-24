package mx.itesm.aplicacion_comedor.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.aplicacion_comedor.model.bd_global.Retro
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdComedor
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Message
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.ModificarVisita
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.NombreVoluntario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.NombresVoluntario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Autor : Jose Antonio Moreno :)
 * Clase que representa a la VIEW MODEL en la arquitectura MVVM
 * Se encarga de controlar la interaccion entre la VISTA y el MODELO,
 * sobretodo manda a llamar y maneja el resultado las funciones que llaman a al BD
 * que agregan las vulnerabilidades a las visitas.
 */

class RegistroComidaExitosoVM : ViewModel() {
    val descargaAPI = Retro().createRetrofit()
    val lista = MutableLiveData<NombresVoluntario>()
    val error = MutableLiveData<String>()
    val flag = MutableLiveData<String>()

    fun listaVoluntarios(idComedor: Int){
        val idCom = IdComedor(idComedor)
        val call = descargaAPI.listaVoluntarios(idCom)

        call.enqueue(object: Callback<NombresVoluntario>{
            override fun onResponse(call: Call<NombresVoluntario>, response: Response<NombresVoluntario>
            ) {
                lista.value = response.body()
            }
            override fun onFailure(call: Call<NombresVoluntario>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
            }
        })
    }

    fun cambiarVisitas(nombre : String, racionpagada : Boolean, idvisita : Int, parallevar : Boolean){
        val modificarVisita = ModificarVisita(nombre, racionpagada, idvisita, parallevar)
        val call = descargaAPI.modificarVisita(modificarVisita)

        call.enqueue(object : Callback<Message>{
            override fun onResponse(call: Call<Message>, response: Response<Message>) {
                flag.value = response.body()?.message
            }
            override fun onFailure(call: Call<Message>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
            }
        })
    }

}