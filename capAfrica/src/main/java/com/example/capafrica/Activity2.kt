package com.example.capafrica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val bundle = intent.extras
        val puntuacion = bundle?.getInt("aciertos")
        val total=bundle?.getInt("tamaño")

        var fallos = total?.minus(puntuacion!!)
        Puntu.setText(""+total +" paises, " +puntuacion+ " aciertos " +fallos + " fallos")
        if (puntuacion != null) {
            if(puntuacion> fallos!!){
                win.setText("HAS GANADO")
            }else{
                win.setText("HAS PERDIDO")
            }
            if(puntuacion==fallos){
                win.setText("EMPATE")
            }
        }


        button2.setOnClickListener(){
            val intent =  Intent(this@Activity2,MainActivity::class.java)

            startActivity(intent)

        }





    }
}