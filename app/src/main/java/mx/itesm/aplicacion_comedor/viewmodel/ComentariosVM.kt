package mx.itesm.aplicacion_comedor.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Autor : Jose Alonso Segura
 * Clase que representa a la VIEW MODEL en la arquitectura MVVM
 * Se encarga de controlar la interaccion entre la VISTA y el MODELO,
 * sobretodo manda a llamar y maneja el resultado las funciones que llaman a al BD.
 */
class ComentariosVM : ViewModel() {

    val fechaDesdeString = MutableLiveData<String>()
    val fechaHastaString = MutableLiveData<String>()

    val yearDesde = MutableLiveData<Int>()
    val mesDesde = MutableLiveData<Int>()
    val diaDesde = MutableLiveData<Int>()

    val yearHasta = MutableLiveData<Int>()
    val mesHasta = MutableLiveData<Int>()
    val diaHasta = MutableLiveData<Int>()

    fun cambiarFechaDesde(year: Int, mes: Int, dia: Int){

        fechaDesdeString.value = String.format("%d-%02d-%02d", year, mes + 1, dia)
        yearDesde.value=year
        mesDesde.value=mes
        diaDesde.value=dia
    }

    fun cambiarFechaHasta(year: Int, mes: Int, dia: Int){

        fechaHastaString.value = String.format("%d-%02d-%02d", year, mes + 1, dia)
        yearHasta.value=year
        mesHasta.value=mes
        diaHasta.value=dia
    }
}