package br.com.example.meuprimeiroexemplo.debug

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class DebugActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "$className.onCreate() chamado: $savedInstanceState")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "$className.onStart() chamado.")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "$className.onRestart() chamado.")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "$className.onResume() chamado.")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "$className.onPause() chamado.")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "$className.onSaveInstanceState() chamado.")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "$className.onStop() chamado.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "$className.onDestroy() chamado.")
    }

    // Retorna o nome da classe sem o pacote
    private val className: String
        get() {
            // Retorna o nome da classe sem o pacote
            val s = javaClass.name
            return s.substring(s.lastIndexOf("."))
        }

    companion object {
        protected const val TAG: String = "fasam-dhionata"
    }
}