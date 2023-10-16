package mx.itesm.aplicacion_comedor.model.bd_global

import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdAsistencia
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdVisita
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.NuevaComidaCURP
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.UsuarioContrasena
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.idcomedor
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.idusuario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.maxIdUsuario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.nuevaComidaCodigo
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.nuevoUsuario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.nuevoVoluntario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface Llamadas {
    @Headers("Content-Type: application/json")
    @POST("/userPassword")
    fun autenticacionComedor(@Body credenciales: UsuarioContrasena): Call<idcomedor>

    @Headers("Content-Type: application/json")
    @POST("/agregarUsuario")
    fun agregarUsuario(@Body user: nuevoUsuario): Call<idusuario>

    @Headers("Content-Type: application/json")
    @POST("/agregarComidaCodigo")
    fun agregarComidaCodigo(@Body comida: nuevaComidaCodigo): Call<IdVisita>

    @Headers("Content-Type: application/json")
    @POST("/agregarComidaCURP")
    fun agregarComidaCURP(@Body comida: NuevaComidaCURP): Call<IdVisita>

    //Para agregar voluntario
    @Headers("Content-Type: application/json")
    @POST("/agregarVoluntario")
    fun agregarVoluntario(@Body user: nuevoVoluntario): Call<IdAsistencia>

    @GET
    fun obtenerMaximoCodigo(): Call<maxIdUsuario>
}