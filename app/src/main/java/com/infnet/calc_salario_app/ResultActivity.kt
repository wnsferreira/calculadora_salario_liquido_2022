package com.infnet.calc_salario_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import calculaSalarioLiquido

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val txtSalarioLiquido = this.findViewById<TextView>(R.id.txtSalarioLiquido)
        val txtTotalDesconto = this.findViewById<TextView>(R.id.txtTotalDesconto)
        val txtPorcentagemDesconto = this.findViewById<TextView>(R.id.txtPorcentagemDesconto)


        val salarioLiquido = intent.getStringExtra("salarioLiquido")
        txtSalarioLiquido.text = salarioLiquido

    }
}


