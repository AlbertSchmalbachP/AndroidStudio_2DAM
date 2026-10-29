package com.escuelaarte.ej1_r2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Puntuación de la sesión: minutos × intensidad
    fun calcularPuntos(minutos: Int, intensidad: Int): Int {
        return minutos * intensidad
    }

    // Clasifica la intensidad: suave (1-3), moderada (4-6), intensa (7-10)
    fun clasificarIntensidad(intensidad: Int): String {
        return when (intensidad) {
            in 1..3 -> "Suave"
            in 4..6 -> "Moderada"
            else -> "Intensa"
        }
    }

    // Descripción específica de cada categoría
    fun describirCategoria(categoria: String): String {
        return when (categoria) {
            "Suave" -> "Sesión ligera, ideal para recuperar."
            "Moderada" -> "Buen ritmo, esfuerzo sostenido."
            else -> "Sesión exigente, recuerda descansar."
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1: identifica y conecta las vistas por su R.id.
        val nombre = findViewById<EditText>(R.id.etNombre)
        val minutos = findViewById<EditText>(R.id.etMinutos)
        val intensidad = findViewById<EditText>(R.id.etIntensidad)
        val registrar = findViewById<Button>(R.id.btnRegistrar)
        val limpiar = findViewById<Button>(R.id.btnLimpiar)
        val resultado = findViewById<TextView>(R.id.tvResultado)

        val mensajeInicial = "Introduce los datos y pulsa Registrar."

        // 3: eventos de clic y validaciones
        registrar.setOnClickListener {
            val nombreConv: String = nombre.text.toString().trim()
            val minutosConv: Int? = minutos.text.toString().toIntOrNull()
            val intensidadConv: Int? = intensidad.text.toString().toIntOrNull()

            if (nombreConv.isEmpty()) {
                resultado.text = "Nombre obligatorio."
            } else if (minutosConv == null || minutosConv < 1 || minutosConv > 300) {
                resultado.text = "Minutos no válidos (entero de 1 a 300)."
            } else if (intensidadConv == null || intensidadConv < 1 || intensidadConv > 10) {
                resultado.text = "Intensidad no válida (entero de 1 a 10)."
            } else {
                val categoria = clasificarIntensidad(intensidadConv)
                val puntos = calcularPuntos(minutosConv, intensidadConv)
                resultado.text = "Nombre: $nombreConv\n" +
                        "Minutos: $minutosConv\n" +
                        "Intensidad: $intensidadConv\n" +
                        "Categoría: $categoria\n" +
                        "Puntos: $puntos\n" +
                        describirCategoria(categoria)
            }
        }

        limpiar.setOnClickListener {
            nombre.setText("")
            minutos.setText("")
            intensidad.setText("")
            resultado.text = mensajeInicial
        }
    }
}