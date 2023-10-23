package mx.itesm.aplicacion_comedor.model.bd_global.dataclass

/**
 * Autor: Jose Antonio Moreno
 * Data class que se usa para registrar los datos de la comida diaria.
 */

data class DatosComidasHoy(
    val comidasPagadasHoy : Int,
    val comidasDonadas: Int,
    val comidasTotalesHoy: Int

)