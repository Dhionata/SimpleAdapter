package br.com.example.meuprimeiroexemplo.activity

import android.os.Bundle
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.debug.DebugActivity

class AddressActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addres)
    }
}