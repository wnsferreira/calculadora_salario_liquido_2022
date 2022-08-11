package com.infnet.calc_salario_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import calculaIR
import calculaInss
import java.lang.Float.parseFloat

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

        var salarioBruto = txtSalarioBruto.getText().toString().toFloatOrNull()
        if (salarioBruto == null) {salarioBruto = 0.0F}
        var pensaoAlimenticia = txtPensaoAlimenticia.getText().toString().toFloatOrNull()
        if (pensaoAlimenticia == null) {pensaoAlimenticia = 0.0F}
        var quantDependentes = txtQuantDependentes.getText().toString().toFloatOrNull()
        if (quantDependentes == null) {quantDependentes = 0.0F}

//        val salarioBruto = parseFloat(txtSalarioBruto.toString())
//        val pensaoAlimenticia = parseFloat(txtPensaoAlimenticia.toString())
//        val quantDependentes = parseFloat(txtQuantDependentes.toString())


        //calculo salário liquido
        val inss = calculaInss(salarioBruto)
        val ir = calculaIR(salarioBruto)
        val salarioLiquido = salarioBruto - inss - ir - pensaoAlimenticia - (quantDependentes * 189.59F)


        //Fazer a transição para ResultActivity

    }
}