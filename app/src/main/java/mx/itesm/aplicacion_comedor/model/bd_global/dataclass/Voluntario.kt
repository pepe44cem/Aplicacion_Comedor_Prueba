package mx.itesm.aplicacion_comedor.model.bd_global.dataclass

/**
 * Autor: Jose Antonio Moreno
 * Data class que se usa para registrar un nuevo voluntario.
 */

data class Voluntario (
    val curp : String,
    val nombre : String,
    val apellido : String,
    val telefono : String
)
