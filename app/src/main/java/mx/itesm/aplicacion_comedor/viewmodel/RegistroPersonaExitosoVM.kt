package mx.itesm.aplicacion_comedor.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.aplicacion_comedor.model.bd_global.Retro
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Condicion
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdCondicion
import mx.itesm.aplicacion_comedor.model.others.QR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroPersonaExitosoVM : ViewModel() {
    val qrHelper = QR()
    val bit = MutableLiveData<Bitmap>()
    val id = MutableLiveData<Int>()
    val error = MutableLiveData<String>()
    val descargaAPI = Retro().createRetrofit()

    fun crearQR(codigo : Int){
        bit.value = qrHelper.generarQR(codigo)
    }

    fun insertarVul(codigo : Int, condicion: String){
        val cond = Condicion(codigo, condicion)
        val call = descargaAPI.insertarVulnerabilidades(cond)

        call.enqueue(object : Callback<IdCondicion>{
            override fun onResponse(call: Call<IdCondicion>, response: Response<IdCondicion>) {
                id.value = response.body()?.idCondicion
            }

            override fun onFailure(call: Call<IdCondicion>, t: Throwable) {
                error.value = "Error al conectar con el servidor"
            }
        })
    }


}