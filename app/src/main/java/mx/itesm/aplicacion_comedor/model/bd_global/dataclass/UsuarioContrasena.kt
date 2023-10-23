package mx.itesm.aplicacion_comedor.model.bd_global.dataclass

/**
 * Autor: Jose Antonio Moreno
 * Data class que se usa para verificar el usuario y la contraseña en el Log IN
 */

data class UsuarioContrasena(
    val usuario: String,
    val contrasena: String
)
