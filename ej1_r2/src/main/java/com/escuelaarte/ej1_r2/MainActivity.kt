package com.escuelaarte.ej1_r2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
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
        // TODO 2: crea las funciones pedidas en el enunciado.
        registrar.setOnClickListener {
            val nombreConv: String = nombre.text.toString().trim()
            val minutosConv: Int? = minutos.text.toString().toIntOrNull()
            val intensidadConv: Int? = intensidad.text.toString().toIntOrNull()

            if (nombreConv.isEmpty()) {
                resultado.text = "Introduce un nombre."
            } else if (minutosConv == null || minutosConv < 1 || minutosConv > 300) {
                resultado.text = "Introduce unos minutos."
            } else if (intensidadConv == null || intensidadConv < 1 || intensidadConv > 10) {
                resultado.text = "Introduce una intensidad."
            } else {
                if (intensidadConv < 4) {
                    intensidadText = "Baja"
                } else if (intensidadConv < 7) {
                    intensidadText = "Moderada"
                } else {
                    intensidadText = "Alta"
                }
            }
        }
        // TODO 3: programa los eventos de clic y las validaciones.
        // TODO 4: comprueba todos los casos de prueba del PDF.
    }
}
