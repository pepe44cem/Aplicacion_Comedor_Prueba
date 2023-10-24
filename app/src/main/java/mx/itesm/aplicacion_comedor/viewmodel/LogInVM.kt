package mx.itesm.aplicacion_comedor.viewmodel

import androidx.lifecycle.ViewModel
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.UsuarioContrasena
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import androidx.lifecycle.MutableLiveData
import mx.itesm.aplicacion_comedor.model.bd_global.Retro
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdComedor

/**
 * Autor : Jose Antonio Moreno :)
 * Clase que representa a la VIEW MODEL en la arquitectura MVVM
 * Se encarga de controlar la interaccion entre la VISTA y el MODELO,
 * sobretodo manda a llamar y maneja el resultado las funciones que llaman a al BD,
 * que verifican las credenciales de los comedores.
 */
class LogInVM : ViewModel() {

    val descargaAPI = Retro().createRetrofit()

    val id = MutableLiveData<Int>()
    val error = MutableLiveData<String>()

    fun descargarServicios(usuario: String, contrasena: String){
        val credencial = UsuarioContrasena(usuario, contrasena)
        val call = descargaAPI.autenticacionComedor(credencial)

        call.enqueue(object : Callback<IdComedor>{
            override fun onResponse(call: Call<IdComedor>, response: Response<IdComedor>) {
                if(response.isSuccessful){
                    id.value = response.body()?.idcomedor
                }else{
                    Log.d("TAG", "Código de respuesta: ${response.code()}")
                    Log.d("TAG", "Código de mensaje: ${response.message()}")
                    error.value = "Usuario o contraseña incorrectos, intente de nuevo."
                }
            }
            override fun onFailure(call: Call<IdComedor>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
                Log.d("TAG", "FALLO PEOR")
            }
        })
    }
}

