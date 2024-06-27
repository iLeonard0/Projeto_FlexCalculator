package unipar.edu.utfpr.projeto_flexcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ResultadoFlexCalculator : AppCompatActivity() {

    private lateinit var resultadoTextView: TextView
    private lateinit var tiposCombustiveisRadioGroup: RadioGroup
    private lateinit var consumoEspecificoEditText: TextInputEditText
    private lateinit var calcularConsumoButton: Button
    private lateinit var valorGasolinaEditText: TextInputEditText
    private lateinit var valorAlcoolEditText: TextInputEditText

    private var consumoGasolina: Double = 0.0
    private var consumoAlcool: Double = 0.0
    private var valorGasolina: Double = 0.0
    private var valorAlcool: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_flex_calculator)

        resultadoTextView = findViewById(R.id.text_resultado)
        tiposCombustiveisRadioGroup = findViewById(R.id.tipos_de_combustiveis)
        consumoEspecificoEditText = findViewById(R.id.consumo_especifico)
        calcularConsumoButton = findViewById(R.id.calcularConsumo)
        valorGasolinaEditText = findViewById(R.id.valor_gasolina)
        valorAlcoolEditText = findViewById(R.id.valor_alcool)

        consumoGasolina = intent.getDoubleExtra("consumoGasolina", 0.0)
        consumoAlcool = intent.getDoubleExtra("consumoAlcool", 0.0)
        valorGasolina = intent.getDoubleExtra("valorGasolina", 0.0)
        valorAlcool = intent.getDoubleExtra("valorAlcool", 0.0)

        calcularConsumoButton.setOnClickListener {
            val consumoEspecifico = consumoEspecificoEditText.text.toString().toDoubleOrNull() ?: 0.0

            val radioButtonId = tiposCombustiveisRadioGroup.checkedRadioButtonId
            val tipoCombustivelSelecionado = findViewById<RadioButton>(radioButtonId)

            val resultado = calcularResultado(consumoEspecifico, tipoCombustivelSelecionado.text.toString())
            resultadoTextView.text = "Resultado: $resultado"
        }
    }

    private fun calcularResultado(consumoEspecifico: Double, tipoCombustivel: String): Double {
        return if (tipoCombustivel == "Gasolina") {
            consumoEspecifico / consumoGasolina * valorGasolina
        } else {
            consumoEspecifico / consumoAlcool * valorAlcool
        }
    }
}