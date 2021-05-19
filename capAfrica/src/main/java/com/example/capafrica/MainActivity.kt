package com.example.capafrica

import Data.CapAfri
import Data.DataDbHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList



class MainActivity : AppCompatActivity() {

    var mapa: HashMap<String, String> = HashMap()
    var mapa2: HashMap<String, String> = HashMap()
    private var db:DataDbHelper?=null
    var list: MutableList<CapAfri> = ArrayList()

    var tablaresp = listOf(R.id.respuesta1,R.id.respuesta2,R.id.respuesta3,R.id.respuesta4)
    var i=0
    var respuestas: ArrayList<String> = ArrayList()
    var puesta =false
    var definitiva=""
    var puntuacion=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db= DataDbHelper(this)
/*
        mapa.put("Angola", "Luanda")
        mapa.put("Argelia", "Argel")
        mapa.put("Benín", "Porto Novo")
        mapa.put("Botsuana", "Gaborone")
        mapa.put("Burkina Faso", "Uagadugú")
        mapa.put("Burundi", "Bujumbura")
        mapa.put("Cabo Verde", "Praia")
        mapa.put("Camerún", "Yaundé")
        mapa.put("Chad", "Yamena")
        mapa.put("Comoras", "Moroni")//descomentar cuando quieras meter en la bbdd


 */
        button.visibility =View.GONE
        button.setOnClickListener {addDatos()}
        btn_otropais.setOnClickListener{poner()}

        mapa= db?.GetUsers()!!//comentar para insertar datos en la tabla


        for (i in mapa)
            respuestas.add(i.value)

        comenzar()
       


    }

     fun comenzar() {
         i=0

         puesta =false
         definitiva=""
         puntuacion=0

         val listpais = arrayListOf<String>()
         var seleccionado=""
         var vuelta=0
         rdgp.clearCheck()
         respuesta1.visibility = View.GONE
         respuesta2.visibility = View.GONE
         respuesta3.visibility = View.GONE
         respuesta4.visibility = View.GONE


         btn_otropais.setOnClickListener {
             enunbot.setText("Mostrar otro País")
             respuesta1.visibility = View.VISIBLE
             respuesta2.visibility = View.VISIBLE
             respuesta3.visibility = View.VISIBLE
             respuesta4.visibility = View.VISIBLE

             if (respuesta1.isChecked==true){
                 seleccionado= respuesta1.text as String
                 if(seleccionado.equals(definitiva)){
                     Toast.makeText(applicationContext,"CORRECTO",
                             Toast.LENGTH_SHORT).show()
                     puntuacion++

                     rdgp.clearCheck()
                 }
                 rdgp.clearCheck()
             }
             if (respuesta2.isChecked==true){
                 seleccionado= respuesta2.text as String
                 if(seleccionado.equals(definitiva)){
                     Toast.makeText(applicationContext,"CORRECTO",
                             Toast.LENGTH_SHORT).show()
                     puntuacion++
                     rdgp.clearCheck()
                 }
                 rdgp.clearCheck()
             }
             if (respuesta3.isChecked==true){
                 seleccionado= respuesta3.text as String
                 if(seleccionado.equals(definitiva)){
                     Toast.makeText(applicationContext,"CORRECTO",
                             Toast.LENGTH_SHORT).show()
                     puntuacion++
                     rdgp.clearCheck()
                 }
                 rdgp.clearCheck()
             }
             if (respuesta4.isChecked==true){
                 seleccionado= respuesta4.text as String
                 if(seleccionado.equals(definitiva)){


                     Toast.makeText(applicationContext,"CORRECTO",
                             Toast.LENGTH_SHORT).show()
                     puntuacion++
                     rdgp.clearCheck()
                 }
                 rdgp.clearCheck()
             }

             puesta = false
             i=0
             val aleatorio = java.util.Random()
             var pais = mapa.entries.elementAt(aleatorio.nextInt(mapa.size))
             if (listpais.size != mapa.size) {  //cuando la lista esta esta igualde tamaño que el mapa
                 while (listpais.contains(pais.key)){
                     pais =mapa.entries.elementAt(aleatorio.nextInt(mapa.size))
                 }
                 listpais.add(pais.key)
                 paises.setText(pais.key)
             }else
             {
                 Toast.makeText(applicationContext,""+mapa.size+" paises " +puntuacion+" aciertos "+ (mapa.size-puntuacion)+" fallos",
                         Toast.LENGTH_LONG).show()

             }


             // val pais : TextView = findViewById(R.id.paises) as TextView
             // pais.setText(R.string.pregunta)

             val lista: List<String> = respuestas.toList()
             Collections.shuffle(lista)
             val respuestas2 = lista.toTypedArray()
             val aleat = java.util.Random()
             val posicion = aleat.nextInt(4)

             val verdadera = mapa.get(pais.key)
             if (verdadera != null)
             {
                 definitiva = verdadera
             }

             if(vuelta<mapa.size)
             {
                 for (num in tablaresp)
                 {

                     if (respuestas2[i] == verdadera) {
                         val resp: RadioButton = findViewById(num) as RadioButton
                         resp.setText(String.format(verdadera))
                         puesta = true

                     } else {
                         val resp: RadioButton = findViewById(num) as RadioButton
                         resp.setText(String.format(respuestas2[i]))

                     }

                     i++
                 }

                 val id = tablaresp[posicion]

                 if (puesta == false) {
                     val resp: RadioButton = findViewById(id) as RadioButton
                     resp.setText(verdadera.toString())

                 }
                 vuelta++



             }else{
                 print(listpais.size)
                 print(mapa.size)
                 val intent =  Intent(this@MainActivity,Activity2::class.java)
                 intent.putExtra("aciertos", puntuacion)
                 intent.putExtra("tamaño", mapa.size)
                 startActivity(intent)
             }
         }
    }



    fun addDatos(){

        for(i in mapa) {
            list.add(CapAfri(pais = i.key, capital = i.value))
        }
        db?.insert(list)

    }
    fun poner(){

        //val aleatorio = java.util.Random()
        //var pais=mapa2.entries.elementAt(aleatorio.nextInt(mapa2.size))
        //prueba.setText(pais.key)
        //var capital=respuestas.elementAt(aleatorio.nextInt(respuestas.size))
        //textView.setText(capital)

    }

}