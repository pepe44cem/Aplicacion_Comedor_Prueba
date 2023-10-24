package mx.itesm.aplicacion_comedor.model.bd_global

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Autor: Jose Antonio Moreno Tahuilan
 * Clase que represena al MODELA en la arquitectura MVVM.
 * Permite definir la conexión a un servidor y hacer llamadas.
 */
class Retro {
    fun createRetrofit(): Llamadas {
        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://ccdifatizapan.ddns.net:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val descargaAPI by lazy {
            retrofit.create(Llamadas::class.java)
        }
        return descargaAPI
    }
}