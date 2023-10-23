package mx.itesm.aplicacion_comedor.model.others

import java.util.regex.Pattern

/**
 * Autor : José Antonio Moreno Tahuilan
 * Clase que define una gramatica regular que permite saber si el input proporcionado
 * tiene el formato de una CURP o no.
 */
class Validacion {
    fun esCURPValida (texto: String) : Boolean {
        val expresionRegular = "^[A-Z]{4}[0-9]{6}[HM][A-Z]{5}[0-9]{2}$"
        val patron = Pattern.compile(expresionRegular)
        return patron.matcher(texto).matches()
    }

    fun esFechaValida (fecha: String) : Boolean {
        if (fecha.length != 8) {
            return false
        }

        val dia = fecha.substring(0, 2).toIntOrNull()
        val mes = fecha.substring(2, 4).toIntOrNull()
        val anio = fecha.substring(4, 8).toIntOrNull()

        if (dia != null && mes != null && anio != null) {
            return dia in 1..31 && mes in 1..12 && anio >= 1900 && anio <= 2100
        }

        return false
    }

}