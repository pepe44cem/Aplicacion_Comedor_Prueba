package mx.itesm.aplicacion_comedor.model.bd_global.dataclass

data class ModificarVisita(
    val nombre : String,
    val racionpagada : Boolean,
    val idvisita : Int,
    val parallevar : Boolean
)
