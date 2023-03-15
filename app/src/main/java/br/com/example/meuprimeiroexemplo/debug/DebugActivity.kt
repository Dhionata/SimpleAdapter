package br.com.example.meuprimeiroexemplo.debug

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class DebugActivity : AppCompatActivity() {

    private val tag: String = "fasam-dhionata"
    private val className: String
        get() {
            // Retorna o nome da classe sem o pacote
            val s = javaClass.name
            return s.substring(s.lastIndexOf("."))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(tag, "$className.onCreate() chamado: $savedInstanceState")
    }

    override fun onStart() {
        super.onStart()
        Log.i(tag, "$className.onStart() chamado.")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(tag, "$className.onRestart() chamado.")
    }

    override fun onResume() {
        super.onResume()
        Log.i(tag, "$className.onResume() chamado.")
    }

    override fun onPause() {
        super.onPause()
        Log.i(tag, "$className.onPause() chamado.")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(tag, "$className.onSaveInstanceState() chamado.")
    }

    override fun onStop() {
        super.onStop()
        Log.i(tag, "$className.onStop() chamado.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(tag, "$className.onDestroy() chamado.")
    }

    // Retorna o nome da classe sem o pacote
}