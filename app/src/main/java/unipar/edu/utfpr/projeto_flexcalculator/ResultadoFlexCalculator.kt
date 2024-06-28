package unipar.edu.utfpr.projeto_flexcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ResultadoFlexCalculator : AppCompatActivity() {

    private lateinit var resultadoTextView: TextView
    private lateinit var tiposCombustiveisRadioGroup: RadioGroup
    private lateinit var consumoEspecificoEditText: TextInputEditText
    private lateinit var calcularConsumoButton: Button

    private var consumoGasolina: Double = 0.0
    private var consumoAlcool: Double = 0.0
    private var valorGasolina: Double = 0.0
    private var valorAlcool: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_flex_calculator)

        // Inicialização dos componentes da interface
        resultadoTextView = findViewById(R.id.text_resultado)
        tiposCombustiveisRadioGroup = findViewById(R.id.tipos_de_combustiveis)
        consumoEspecificoEditText = findViewById(R.id.consumo_especifico)
        calcularConsumoButton = findViewById(R.id.calcularConsumo)

        // Obter os valores passados pela Intent
        consumoGasolina = intent.getDoubleExtra("consumoGasolina", 0.0)
        consumoAlcool = intent.getDoubleExtra("consumoAlcool", 0.0)
        valorGasolina = intent.getDoubleExtra("valorGasolina", 0.0)
        valorAlcool = intent.getDoubleExtra("valorAlcool", 0.0)

        // Atualizar o campo de consumo específico com o valor de gasolina inicialmente
        consumoEspecificoEditText.setText(consumoGasolina.toString())

        // Configurar o listener do botão de cálculo
        calcularConsumoButton.setOnClickListener {
            calcularConsumo()
        }

        // Configurar o listener para o RadioGroup
        tiposCombustiveisRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.Gasolina -> consumoEspecificoEditText.setText(consumoGasolina.toString())
                R.id.Alcool -> consumoEspecificoEditText.setText(consumoAlcool.toString())
            }
        }
    }

    // função para botão de calcular o consumo
    private fun calcularConsumo() {
        val consumoEspecifico = consumoEspecificoEditText.text.toString().toDoubleOrNull() ?: 0.0
        var custoPorKm = 0.0
        var combustivelSelecionado = ""

        if (tiposCombustiveisRadioGroup.checkedRadioButtonId == R.id.Gasolina && consumoGasolina > 0 && valorGasolina > 0) {
            custoPorKm = consumoEspecifico / valorGasolina
            combustivelSelecionado = "Gasolina"
        } else if (tiposCombustiveisRadioGroup.checkedRadioButtonId == R.id.Alcool && consumoAlcool > 0 && valorAlcool > 0) {
            custoPorKm = consumoAlcool / valorAlcool
            combustivelSelecionado = "Álcool"
        }

        // Formatar o resultado para duas casas decimais
        val custoFormatado = String.format("%.2f", custoPorKm)

        // Exibir o resultado na TextView
        resultadoTextView.text = "Custo por KM rodado:\n$combustivelSelecionado: R$ $custoFormatado L/KM"
    }
}