package com.infnet.calc_salario_app

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import calculaIR
import calculaInss
import com.google.android.gms.ads.AdView
import java.io.File
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.round
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest

class MainActivity : AppCompatActivity() {

    private val arquivo = "texto"
    private lateinit var mAdView: AdView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        MobileAds.initialize(this)
        mAdView = this.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        val btnCalcular = this.findViewById<Button>(R.id.btnCalcular)
        btnCalcular.setOnClickListener{

            val txtSalarioBruto = this.findViewById<EditText>(R.id.txtSalarioBruto)
            if (txtSalarioBruto.text.toString().isEmpty()) {
                val msg = "Entre com o Valor do Salário Bruto"
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            }else {
                val salarioLiquido = calcularSalarioLiquido()
                val totalDescontos = calcularDescontos().toString()
                val percentualDesconto = calcularPercentualDesconto().toString()


                this.criarArquivoArmazenamento(arquivo)

                val resultIntent = Intent(this,ResultActivity::class.java)
                resultIntent.putExtra("salarioLiquido", salarioLiquido)
                resultIntent.putExtra("totalDescontos", totalDescontos)
                resultIntent.putExtra("percentualDesconto", percentualDesconto)

                Log.i("TP7", "SalarioLiquido $salarioLiquido ")

                startActivity(resultIntent)
            }
        }

        val btnApagar = this.findViewById<Button>(R.id.btnApagar)
        btnApagar.setOnClickListener {

            deletaArquivo(arquivo)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun criarArquivoArmazenamento(fileName: String){

        val file = File(filesDir, "$fileName.crd")
        val salarioLiquido = calcularSalarioLiquido()

        val dataConsulta = LocalDate.now()
        val formatacaoData: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dataFormatado: String = dataConsulta.format(formatacaoData)

        var hora = LocalTime.now()
        val formatacaoHora = DateTimeFormatter.ofPattern ("HH:mm:ss")
        val horaFormatada: String = hora.format(formatacaoHora)

        val txtQuantDependentes = this.findViewById<EditText>(R.id.txtQuantDependentes)
        val txtPensaoAlimenticia = this.findViewById<EditText>(R.id.txtPensaoAlimenticia)
        val txtPlanoSaude = this.findViewById<EditText>(R.id.txtPlanoSaude)
        val txtOutrosDescontos = this.findViewById<EditText>(R.id.txtOutrosDescontos)

        var pensaoAlimenticia = txtPensaoAlimenticia.text.toString().toFloatOrNull()
        if (pensaoAlimenticia == null) {
            pensaoAlimenticia = 0.0F
        }
        var quantDependentes = txtQuantDependentes.text.toString().toFloatOrNull()
        if (quantDependentes == null) {
            quantDependentes = 0.0F
        }

        val totalDescontos = calcularDescontos()
        val porcentagemDescontos = calcularPercentualDesconto()

        val planoSaude = txtPlanoSaude.text.toString().toFloat()
        val outrosDescontos = txtOutrosDescontos.text.toString().toFloat()


        if(file.exists()){
//            file.delete()
            Log.i("TP7", "Arquivo $fileName deletado")
        }
        else{
            try {
                val fos = this.openFileOutput("texto.txt", Context.MODE_APPEND)

                val dataConsultaBytes = "Data: $dataFormatado \n".toByteArray()
                val horaConsultaBytes = "Hora da Consulta: $horaFormatada \n".toByteArray()
                val salarioLiquidoBytes = "Salário Liquido: R$:$salarioLiquido \n".toByteArray()
                val quantDependentesBytes = "Quantidade de dependentes: $quantDependentes \n".toByteArray()
                val pensaoAlimenticiaBytes = "Pensão Alimenticia: R$:$pensaoAlimenticia \n".toByteArray()
                val planoSaudeBytes = "Plano de saúde: R$:$planoSaude \n".toByteArray()
                val outrosDescontosBytes = "Outros descontos: R$:$outrosDescontos \n".toByteArray()
                val porcentagemDescontosBytes = "Porcentagem de descontos: $porcentagemDescontos% \n".toByteArray()
                val totalDescontosBytes = "Total de Descontos: R$: $totalDescontos \n".toByteArray()
                val pularLinha = "\n".toByteArray()

                fos.write(dataConsultaBytes)
                fos.write(horaConsultaBytes)
                fos.write(salarioLiquidoBytes)
                fos.write(quantDependentesBytes)
                fos.write(pensaoAlimenticiaBytes)
                fos.write(planoSaudeBytes)
                fos.write(outrosDescontosBytes)
                fos.write(porcentagemDescontosBytes)
                fos.write(totalDescontosBytes)
                fos.write(pularLinha)
                fos.close()

            } catch (e: IOException) {
                Log.d("Permissão", "Erro na criação")
            }


        }
    }


    private  fun calcularSalarioLiquido(): String {

        val txtSalarioBruto = this.findViewById<EditText>(R.id.txtSalarioBruto)
        val txtQuantDependentes = this.findViewById<EditText>(R.id.txtQuantDependentes)
        val txtPensaoAlimenticia = this.findViewById<EditText>(R.id.txtPensaoAlimenticia)
        val txtPlanoSaude = this.findViewById<EditText>(R.id.txtPlanoSaude)
        val txtOutrosDescontos = this.findViewById<EditText>(R.id.txtOutrosDescontos)


        var salarioBruto = txtSalarioBruto.text.toString().toFloatOrNull()
        if (salarioBruto == null) {
            salarioBruto = 0.0F
        }
        var pensaoAlimenticia = txtPensaoAlimenticia.text.toString().toFloatOrNull()
        if (pensaoAlimenticia == null) {
            pensaoAlimenticia = 0.0F
        }
        var quantDependentes = txtQuantDependentes.text.toString().toFloatOrNull()
        if (quantDependentes == null) {
            quantDependentes = 0.0F
        }

        var planoSaude = txtPlanoSaude.text.toString().toFloatOrNull()
        if (planoSaude == null) {
            planoSaude = 0.0F
        }
        var outrosDescontos = txtOutrosDescontos.text.toString().toFloatOrNull()
        if (outrosDescontos == null) {
            outrosDescontos = 0.0F
        }

        val inss = calculaInss(salarioBruto)
        val ir = calculaIR(salarioBruto)
        val salarioLiquido =
            round(salarioBruto - inss - ir - pensaoAlimenticia - (quantDependentes * 189.59F) - planoSaude - outrosDescontos).toString()

        return salarioLiquido

    }

    private fun calcularDescontos(): Float {

        val txtSalarioBruto = this.findViewById<EditText>(R.id.txtSalarioBruto)

        var salarioBruto =  txtSalarioBruto.text.toString().toFloatOrNull()
        if (salarioBruto == null)
            salarioBruto = 0.0F

        var salarioLiquido = calcularSalarioLiquido().toFloatOrNull()
        if (salarioLiquido == null)
            salarioLiquido = 0.0F

        println(salarioBruto)
        println(salarioLiquido)

        Log.i("TP7", "SalarioLiquido $salarioLiquido ")
        Log.i("TP7", "SalarioLiquido $salarioBruto ")

        val totalDescontos = round(salarioBruto - salarioLiquido)

        return totalDescontos
    }

    private fun calcularPercentualDesconto(): Float{

        val txtSalarioBruto = this.findViewById<EditText>(R.id.txtSalarioBruto)
        var salarioBruto =  txtSalarioBruto.text.toString().toFloatOrNull()
        if (salarioBruto == null)
            salarioBruto = 0.0F

        val totalDesconto = calcularDescontos()

        val calcular = round((totalDesconto /salarioBruto) *100)

        return  calcular
    }

    private fun deletaArquivo(fileName: String){

        val file = File(filesDir, "$fileName.txt")
        if(file.exists()){
            file.delete()
            Toast.makeText(this, "Arquivo deletado.", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this, "Arquivo não existe.", Toast.LENGTH_SHORT).show()
        }
    }

}



