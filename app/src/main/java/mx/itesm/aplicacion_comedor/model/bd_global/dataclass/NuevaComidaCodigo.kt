package mx.itesm.aplicacion_comedor.model.bd_global.dataclass

/**
 * Autor: Jose Antonio Moreno
 * Data class que se usa para  registrar una comida con codigo personal.
 */

data class NuevaComidaCodigo(
    val codigo: Int,
    val comedor : Int
)
