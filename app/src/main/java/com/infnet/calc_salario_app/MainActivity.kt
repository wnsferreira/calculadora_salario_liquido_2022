package com.infnet.calc_salario_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        --------------------------------------------------------------------------------------
//        Entrada de dados
        val txtSalarioBruto = this.findViewById<TextView>(R.id.txtSalarioBruto)
        val txtQuantDependentes = this.findViewById<TextView>(R.id.txtQuantDependentes)
        val txtPensaoAlimenticia = this.findViewById<TextView>(R.id.txtPensaoAlimenticia)
        val txtPlanoSaude = this.findViewById<TextView>(R.id.txtPlanoSaude)
        val txtOutrosDescontos = this.findViewById<TextView>(R.id.txtOutrosDescontos)
        val btnCalcular = this.findViewById<Button>(R.id.btnCalcular)

// ----------------------------------------------------------------------------------------------
//      Dados que serão exibidos na ResultActivity
        val txtSalarioLiquido = this.findViewById<Button>(R.id.txtSalarioLiquido)
        val txtTotalDesconto = this.findViewById<Button>(R.id.txtTotalDesconto)
        val txtPorcentagemDesconto = this.findViewById<Button>(R.id.txtPorcentagemDesconto)



        //       desenvolver função calcular INSS

        //       desenvolver função calcular IR

        //       Fazer a transição para ResultActivity

    }
}