package br.com.example.meuprimeiroexemplo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.debug.DebugActivity

class HomeActivity : DebugActivity() {
    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_home)
    }

    fun exibir(view: View) {
        //
        val opcao = view.id
        val intent: Intent
        when (opcao) {
            R.id.btnAddres -> {
                intent = Intent(applicationContext, AddresActivity::class.java)
                startActivity(intent)
            }
            R.id.btnUse -> {
                intent = Intent(applicationContext, UserActivity::class.java)
                startActivity(intent)
            }
            R.id.btnPessoa -> {
                intent = Intent(applicationContext, PessoaActivity::class.java)
                startActivity(intent)
            }
            R.id.btnPost -> {
                intent = Intent(applicationContext, PostActivity::class.java)
                startActivity(intent)
            }
            R.id.btnComments -> {
                intent = Intent(applicationContext, CommentsActivity::class.java)
                startActivity(intent)
            }
            R.id.btnPeople -> {
                intent = Intent(applicationContext, PeopleActivity::class.java)
                startActivity(intent)
            }
            else -> Toast.makeText(applicationContext, "Opção inválida.", Toast.LENGTH_LONG).show()
        }
    }
}