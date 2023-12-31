package mx.itesm.aplicacion_comedor.model.bd_global.dataclass

/**
 * Autor: Jose Antonio Moreno
 * Data class que se usa para  registrar una comida con CURP.
 */

data class NuevaComidaCURP(
    val curp : String,
    val comedor : Int
)
