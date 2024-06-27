package unipar.edu.utfpr.projeto_flexcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    private lateinit var consumoGasolinaEditText: TextInputEditText
    private lateinit var consumoAlcoolEditText: TextInputEditText
    private lateinit var valorGasolinaEditText: TextInputEditText
    private lateinit var valorAlcoolEditText: TextInputEditText
    private lateinit var buscarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        consumoGasolinaEditText = findViewById(R.id.consumo_gas_lt)
        consumoAlcoolEditText = findViewById(R.id.consumo_oil_lt)
        valorGasolinaEditText = findViewById(R.id.valor_gasolina)
        valorAlcoolEditText = findViewById(R.id.valor_alcool)
        buscarButton = findViewById(R.id.botao_busca)

        buscarButton.setOnClickListener {
            val consumoGasolina = consumoGasolinaEditText.text.toString().toDoubleOrNull() ?: 0.0
            val consumoAlcool = consumoAlcoolEditText.text.toString().toDoubleOrNull() ?: 0.0
            val valorGasolina = valorGasolinaEditText.text.toString().toDoubleOrNull() ?: 0.0
            val valorAlcool = valorAlcoolEditText.text.toString().toDoubleOrNull() ?: 0.0

            val intent = Intent(this, ResultadoFlexCalculator::class.java)
            intent.putExtra("consumoGasolina", consumoGasolina)
            intent.putExtra("consumoAlcool", consumoAlcool)
            intent.putExtra("valorGasolina", valorGasolina)
            intent.putExtra("valorAlcool", valorAlcool)
            startActivity(intent)
        }
    }
}