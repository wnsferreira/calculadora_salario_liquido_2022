package com.infnet.calc_salario_app
import android.content.Intent
import java.io.File
import java.io.FileOutputStream
import java.util.*

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// --------------------------------------------------------------------------------------
//  Função para criar o arquivo na memória interna
    fun createFile(fileName: String){
        val file = File(getExternalFilesDir(null), "$fileName.crd")

        Log.i("TP1", "Arquivo $fileName carregado")

        if(file.exists()){
            file.delete()
            Log.i("TP1", "Arquivo $fileName deletado")
        }
        else{
            try {
                val os: OutputStream = FileOutputStream(file)

                os.write("Teste".toByteArray())
                os.close()

                Toast.makeText(this, "Coordenadas salvas", Toast.LENGTH_LONG).show()

                Log.i("TP1", "Arquivo criado")
            } catch (e: IOException) {
                Log.d("Permissão", "Erro na criação")
            }
        }
    }

// --------------------------------------------------------------------------------------
//      Entrada de dados
        val txtSalarioBruto = this.findViewById<EditText>(R.id.txtSalarioBruto)
        val txtQuantDependentes = this.findViewById<EditText>(R.id.txtQuantDependentes)
        val txtPensaoAlimenticia = this.findViewById<EditText>(R.id.txtPensaoAlimenticia)
        val txtPlanoSaude = this.findViewById<EditText>(R.id.txtPlanoSaude)
        val txtOutrosDescontos = this.findViewById<EditText>(R.id.txtOutrosDescontos)
        val btnCalcular = this.findViewById<Button>(R.id.btnCalcular)

// ----------------------------------------------------------------------------------------

        var salarioBruto = txtSalarioBruto.text.toString().toFloatOrNull()
        if (salarioBruto == null) {
            salarioBruto = 0.0F
        }
        var pensaoAlimenticia = txtPensaoAlimenticia.getText().toString().toFloatOrNull()
        if (pensaoAlimenticia == null) {
            pensaoAlimenticia = 0.0F
        }
        var quantDependentes = txtQuantDependentes.getText().toString().toFloatOrNull()
        if (quantDependentes == null) {
            quantDependentes = 0.0F
        }

        //calculo salário liquido
        val inss = calculaInss(salarioBruto)
        val ir = calculaIR(salarioBruto)
        val salarioLiquido =
            salarioBruto - inss - ir - pensaoAlimenticia - (quantDependentes * 189.59F)


        // Pedir permissão para gravar



        // Criar novo arquivo ou gravar em arquivo já existente se o mesmo já existir
        val fileName = "calc_salario_app_memo"
        var file = File(getExternalFilesDir(null), "$fileName.crd")
//        if (file == null) {
//            file = createFile(fileName)}


//        ----------------------------------------------------------------------------------
        // Fazer a transição para ResultActivity levando dados resultates para lá
        btnCalcular.setOnClickListener{

            val resultIntent = Intent(this,ResultActivity::class.java)
            resultIntent.putExtra("salarioLiquido", salarioLiquido)

            Log.i("TP7", "SalarioLiquido $salarioLiquido ")

            startActivity(resultIntent)
        }


            // Botão para visualizar dados gravados no passado
    }


}
