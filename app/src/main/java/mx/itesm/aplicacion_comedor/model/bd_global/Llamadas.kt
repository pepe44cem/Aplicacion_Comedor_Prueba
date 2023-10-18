package mx.itesm.aplicacion_comedor.model.bd_global

import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.ComedorIdRequest
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.DatosComidasHoy
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.VoluntariosAsistentesHoy
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface Llamadas {

    @Headers("Content-Type: application/json")
    @POST("/datosHoy")
    fun obtenerDatosComidasHoy(@Body comedor: ComedorIdRequest): Call<DatosComidasHoy>

    @Headers("Content-Type: application/json")
    @POST("/cantidadVoluntariosHoy")
    fun obtenerCantidadVoluntariosHoy(@Body comedor: ComedorIdRequest): Call<VoluntariosAsistentesHoy>
}