package mx.itesm.aplicacion_comedor.model.bd_global

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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