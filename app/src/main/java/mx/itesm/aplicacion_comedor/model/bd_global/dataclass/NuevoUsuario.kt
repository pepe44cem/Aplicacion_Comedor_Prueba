package mx.itesm.aplicacion_comedor.model.bd_global.dataclass

/**
 * Autor: Jose Antonio Moreno
 * Data class que se usa para registrar nuevo usuario.
 */

data class NuevoUsuario(
    val nombre: String,
    val apellido: String,
    val curp : String,
    val sexo : String,
    val fecha : String
)
