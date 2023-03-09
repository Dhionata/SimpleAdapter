package br.com.example.meuprimeiroexemplo.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.debug.DebugActivity

class PessoaActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pessoa)
    }

    fun exibirPessoa(view: View) {

        val nome = findViewById<EditText>(R.id.textNome).text
        val sobrenome = findViewById<EditText>(R.id.textSobreNome).text
        val email = findViewById<EditText>(R.id.textEmail).text
        val telefone = findViewById<EditText>(R.id.textPhone).text

        //Exibir dados para o usuário
        Toast.makeText(
            applicationContext,
            "Os valores informados foram: \n$nome\n$sobrenome\n$email\n$telefone",
            Toast.LENGTH_LONG
        ).show()
    }
}