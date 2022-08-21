package com.infnet.calc_salario_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val txtSalarioLiquido = this.findViewById<TextView>(R.id.txtSalarioLiquido)
        val txtTotalDesconto = this.findViewById<TextView>(R.id.txtTotalDesconto)
        val txtPorcentagemDesconto = this.findViewById<TextView>(R.id.txtPorcentagemDescontos)


        val salarioLiquido = intent.getStringExtra("salarioLiquido")
        val totalDesconto = intent.getStringExtra("totalDescontos")
        val percentualDesconto = intent.getStringExtra("percentualDesconto")
        txtSalarioLiquido.text = "R$ $salarioLiquido"
        txtTotalDesconto.text="R$ $totalDesconto"
        txtPorcentagemDesconto.text= "$percentualDesconto%"



    }

}


