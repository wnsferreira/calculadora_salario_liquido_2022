package com.infnet.calc_salario_app
import android.content.Context
import android.content.ContextParams
import android.content.Intent
import java.io.File
import java.io.FileOutputStream
import java.util.*

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import calculaIR
import calculaInss
import calculaSalarioLiquido
import java.io.IOException
import java.io.OutputStream
import kotlin.math.round

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        // Criar novo arquivo ou gravar em arquivo já existente se o mesmo já existir
//        val fileName = "calc_salario_app_memo"
//        var file = File(getExternalFilesDir(null), "$fileName.crd")
////        if (file == null) {
////            file = createFile(fileName)}


        val btnCalcular = this.findViewById<Button>(R.id.btnCalcular)

        btnCalcular.setOnClickListener{

            // Pedir permissão para gravar

            val permissao = ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION



            val salarioLiquido = calcularSalarioLiquido()
            val totalDescontos = calcularDescontos().toString()
            val percentualDesconto = calcularPercentualDesconto().toString()

            val arquivo = "arquivo"
            this.validarArquivoArmazenamento(arquivo)




            val resultIntent = Intent(this,ResultActivity::class.java)
            resultIntent.putExtra("salarioLiquido", salarioLiquido)
            resultIntent.putExtra("totalDescontos", totalDescontos)
            resultIntent.putExtra("percentualDesconto", percentualDesconto)

            Log.i("TP7", "SalarioLiquido $salarioLiquido ")

            startActivity(resultIntent)
        }


            // Botão para visualizar dados gravados no passado
    }




    private fun validarArquivoArmazenamento(fileName: String){

        val file = File(filesDir, "$fileName.crd")
        val salarioLiquido = calcularSalarioLiquido()

        Log.i("TP1", "Arquivo $fileName carregado")

        if(file.exists()){
//            file.delete()
            Log.i("TP1", "Arquivo $fileName deletado")
        }
        else{
            try {
                val fos = this.openFileOutput("texto.txt", Context.MODE_APPEND)
                val bytes = salarioLiquido.toByteArray()
                val espaco = " / ".toByteArray()

                fos.write(bytes)
                fos.write(espaco)
                fos.close()


//                val os: OutputStream = FileOutputStream(file)
//                os.write("Teste".toByteArray())
//                os.close()
//                Toast.makeText(this, "Dados salvas", Toast.LENGTH_LONG).show()
//                Log.i("TP1", "Arquivo criado")
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

        //calculo salário liquido
        val planoSaude = txtPlanoSaude.text.toString().toFloat()
        val outrosDescontos = txtOutrosDescontos.text.toString().toFloat()

        val inss = calculaInss(salarioBruto)
        val ir = calculaIR(salarioBruto)
        val salarioLiquido =
            (salarioBruto - inss - ir - pensaoAlimenticia - (quantDependentes * 189.59F) - planoSaude - outrosDescontos).toString()

        return salarioLiquido

    }

    private fun calcularDescontos(): Float {

        val txtSalarioBruto = this.findViewById<EditText>(R.id.txtSalarioBruto)

        var salarioBruto =  txtSalarioBruto.text.toString().toFloatOrNull()
        if (salarioBruto == null)
            salarioBruto = 0.0F

        var salarioLiquido = calcularSalarioLiquido().toString().toFloatOrNull()
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

        var totalDesconto = calcularDescontos().toFloat()

        val calcular = round((totalDesconto /salarioBruto) *100)

        return  calcular
    }


}



