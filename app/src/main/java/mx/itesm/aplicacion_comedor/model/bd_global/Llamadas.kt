package mx.itesm.aplicacion_comedor.model.bd_global


import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.ComedorIdRequest
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.DatosComidasHoy
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.VoluntariosAsistentesHoy
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Asistencia
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Condicion
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Condiciones
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdAsistencia
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdComedor
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdCondicion
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdReporte
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdVisita
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.IdVoluntario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Message
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.ModificarVisita
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.NombreVoluntario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.NombresVoluntario
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.NuevaComidaCURP
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Reporte
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.UsuarioContrasena
import mx.itesm.aplicacion_comedor.model.bd_global.dataclass.Voluntario
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
    @POST("/datosHoy")
    fun obtenerDatosComidasHoy(@Body comedor: ComedorIdRequest): Call<DatosComidasHoy>

    @Headers("Content-Type: application/json")
    @POST("/cantidadVoluntariosHoy")
    fun obtenerCantidadVoluntariosHoy(@Body comedor: ComedorIdRequest): Call<VoluntariosAsistentesHoy>

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

    @Headers("Content-Type: application/json")
    @GET("/listaCondiciones")
    fun listaCondiciones() : Call<Condiciones>

    @Headers("Content-Type: application/json")
    @POST("/insertarVulnerabilidades")
    fun insertarVulnerabilidades(@Body condicion: Condicion) : Call<IdCondicion>

    @Headers("Content-Type: application/json")
    @POST("/insertarReporte")
    fun insertarReporte(@Body reporte: Reporte) : Call<IdReporte>

    @Headers("Content-Type: application/json")
    @POST("/agregarVoluntario")
    fun agregarVoluntario(@Body voluntario: Voluntario) : Call<IdVoluntario>

    @Headers("Content-Type: application/json")
    @POST("/agregarAsistencia")
    fun agregarAsistencia(@Body asistencia: Asistencia) : Call<IdAsistencia>

    @GET
    fun obtenerMaximoCodigo(): Call<maxIdUsuario>
}