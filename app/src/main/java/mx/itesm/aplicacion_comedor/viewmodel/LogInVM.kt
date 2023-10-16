package mx.itesm.aplicacion_comedor.viewmodel

import androidx.lifecycle.ViewModel
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.UsuarioContrasena
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log;
import androidx.lifecycle.MutableLiveData
import mx.itesm.aplicacion_comedor.model.bd_global.Retro
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.idcomedor

class LogInVM : ViewModel() {

    val descargaAPI = Retro().createRetrofit()

    val id = MutableLiveData<Int>()
    val error = MutableLiveData<String>()

    fun descargarServicios(usuario: String, contrasena: String){
        val credencial = UsuarioContrasena(usuario, contrasena)
        val call = descargaAPI.autenticacionComedor(credencial)

        call.enqueue(object : Callback<idcomedor>{
            override fun onResponse(call: Call<idcomedor>, response: Response<idcomedor>) {
                if(response.isSuccessful){
                    id.value = response.body()?.idcomedor
                }else{
                    Log.d("TAG", "Código de respuesta: ${response.code()}")
                    Log.d("TAG", "Código de mensaje: ${response.message()}")
                    error.value = "Error al conectar con el servidor" + response.message() + ": " + response.code()
                }
            }
            override fun onFailure(call: Call<idcomedor>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
                Log.d("TAG", "FALLO PEOR")
            }
        })
    }
}

