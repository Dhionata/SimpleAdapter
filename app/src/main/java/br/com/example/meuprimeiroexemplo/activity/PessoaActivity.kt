package br.com.example.meuprimeiroexemplo.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.debug.DebugActivity


class PessoaActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pessoa)
    }

    fun exibir(view: View?) {
        // Entrada
        val textNome = R.id.textNome
        val textSobreNome = R.id.textSobreNome
        val textEmail = R.id.textEmail
        val textPhone = R.id.textPhone
        // Processamento
        val nome: String
        val sobrenome: String
        val email: String
        val telefone: String
        nome = textNome.toString()
        sobrenome = textSobreNome.toString()
        email = textEmail.toString()
        telefone = textPhone.toString()
        //Saída
        val dados: String
        dados = String.format("Os valores informados foram: \n%s\n%s\n%s\n%s", nome, sobrenome, email, telefone)

        //Exibir dados para o usuário
        Toast.makeText(application, dados, Toast.LENGTH_LONG).show()
    }
}