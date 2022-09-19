package com.infnet.calc_salario_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdView


class ResultActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        MobileAds.initialize(this)
        mAdView = this.findViewById(R.id.adView_result)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

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


