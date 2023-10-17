package mx.itesm.aplicacion_comedor.model.bd_global


import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdComedor
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdVisita
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Message
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.ModificarVisita
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.NombreVoluntario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.NombresVoluntario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.NuevaComidaCURP
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.UsuarioContrasena
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.idusuario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.maxIdUsuario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.nuevaComidaCodigo
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.nuevoUsuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface Llamadas {
    @Headers("Content-Type: application/json")
    @POST("/userPassword")
    fun autenticacionComedor(@Body credenciales: UsuarioContrasena): Call<IdComedor>

    @Headers("Content-Type: application/json")
    @POST("/agregarUsuario")
    fun agregarUsuario(@Body user: nuevoUsuario): Call<idusuario>

    @Headers("Content-Type: application/json")
    @POST("/agregarComidaCodigo")
    fun agregarComidaCodigo(@Body comida: nuevaComidaCodigo): Call<IdVisita>

    @Headers("Content-Type: application/json")
    @POST("/agregarComidaCURP")
    fun agregarComidaCURP(@Body comida: NuevaComidaCURP): Call<IdVisita>

    @Headers("Content-Type: application/json")
    @POST("/listaVoluntarios")
    fun listaVoluntarios(@Body idComedor: IdComedor): Call<NombresVoluntario>

    @Headers("Content-Type: application/json")
    @PUT("/modificarVisita")
    fun modificarVisita(@Body modificarVisita: ModificarVisita) : Call<Message>



    @GET
    fun obtenerMaximoCodigo(): Call<maxIdUsuario>
}