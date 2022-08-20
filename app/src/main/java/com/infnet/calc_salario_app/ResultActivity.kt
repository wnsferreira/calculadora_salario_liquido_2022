package com.infnet.calc_salario_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import calculaSalarioLiquido
import java.io.File

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val txtSalarioLiquido = this.findViewById<TextView>(R.id.txtSalarioLiquido)
        val txtTotalDesconto = this.findViewById<TextView>(R.id.txtTotalDesconto)
        val txtPorcentagemDesconto = this.findViewById<TextView>(R.id.txtPorcentagemDesconto)


        val salarioLiquido = intent.getStringExtra("salarioLiquido")
        val totalDesconto = intent.getStringExtra("totalDescontos")
        val percentualDesconto = intent.getStringExtra("percentualDesconto")
        txtSalarioLiquido.text = salarioLiquido
        txtTotalDesconto.text=totalDesconto
        txtPorcentagemDesconto.text= "$percentualDesconto%"



    }

//   chamar função no botão deletar arquivo
    private fun deletaArquivo(fileName: String){

        val file = File(filesDir, "$fileName.crd")

        if(file.exists()){
            file.delete()
        }
    }
}


