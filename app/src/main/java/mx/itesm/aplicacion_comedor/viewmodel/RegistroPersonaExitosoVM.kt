package mx.itesm.aplicacion_comedor.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.aplicacion_comedor.model.others.QR

class RegistroPersonaExitosoVM : ViewModel() {
    val qrHelper = QR()
    val bit = MutableLiveData<Bitmap>()

    fun crearQR(codigo : Int){
        bit.value = qrHelper.generarQR(codigo)
    }
}