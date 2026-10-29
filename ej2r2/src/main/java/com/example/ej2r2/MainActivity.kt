package com.example.ej2r2

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private fun costeEnvio(peso: Double, importe: Double, urgente: Boolean): Double {
        val tarifaPeso = when {
            peso <= 1.0 -> 3.50
            peso <= 5.0 -> 6.50
            else -> 9.50
        }
        val envioOrdinario = if (importe >= 60.0) 0.0 else tarifaPeso
        val recargo = if (urgente) 4.0 else 0.0
        return envioOrdinario + recargo
    }

    private fun euros(valor: Double): String {
        return String.format(Locale("es", "ES"), "%.2f €", valor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etImporte = findViewById<EditText>(R.id.etImporte)
        val etPeso = findViewById<EditText>(R.id.etPeso)
        val cbUrgente = findViewById<CheckBox>(R.id.cbUrgente)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        findViewById<Button>(R.id.btnCalcular).setOnClickListener {
            val importe = etImporte.text.toString().replace(',', '.').toDoubleOrNull()
            val peso = etPeso.text.toString().replace(',', '.').toDoubleOrNull()
            if (importe == null || !importe.isFinite() || importe < 0.0 ||
                peso == null || !peso.isFinite() || peso <= 0.0 || peso > 30.0) {
                tvResultado.text = "Introduce un importe válido y un peso entre 0 y 30 kg (sin incluir 0)."
            } else {
                val envio = costeEnvio(peso, importe, cbUrgente.isChecked)
                val total = importe + envio
                tvResultado.text = "Artículos: ${euros(importe)}\n" +
                        "Peso: $peso kg\nEnvío: ${euros(envio)}\nTOTAL: ${euros(total)}"
            }
        }

        findViewById<Button>(R.id.btnLimpiar).setOnClickListener {
            etImporte.setText("")
            etPeso.setText("")
            cbUrgente.isChecked = false
            tvResultado.text = "Introduce los datos del envío."
        }
    }
}
