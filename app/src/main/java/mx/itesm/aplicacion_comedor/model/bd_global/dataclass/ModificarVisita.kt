package mx.itesm.aplicacion_comedor.model.bd_global.dataclass

/**
 * Autor: Jose Antonio Moreno
 * Data class que se usa para registrar modificar si una ración fue pagada y si fue para llevar.
 */

data class ModificarVisita(
    val nombre : String,
    val racionpagada : Boolean,
    val idvisita : Int,
    val parallevar : Boolean
)
