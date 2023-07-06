package br.com.example.meuprimeiroexemplo.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.adapters.recyclerview.PostRecyclerAdapter
import br.com.example.meuprimeiroexemplo.debug.DebugActivity
import br.com.example.meuprimeiroexemplo.model.Post

class PostActivity : DebugActivity() {
    private lateinit var recyclerViewPost: RecyclerView
    private val postagens = arrayListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        recyclerViewPost = findViewById(R.id.recyclerViewPost)
    }

    fun adicionarPost(view: View) {
        //entrada
        val txtUserId = findViewById<TextView>(R.id.txtUserId)
        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        val txtBody = findViewById<TextView>(R.id.txtBody)

        //Processamento
        val title: String
        val body: String
        val userId: Int
        try {
            userId = txtUserId.text.toString().toInt()
            title = txtTitle.text.toString()
            body = txtBody.text.toString()

            preencherObjetoLista(userId, title, body)
            val postRecyclerAdapter =
                PostRecyclerAdapter(applicationContext, postagens, txtUserId, txtTitle, txtBody)
            recyclerViewPost.adapter = postRecyclerAdapter
        } catch (e: NumberFormatException) {
            System.err.println("Err: ${e.message}")
            Log.e("formatação", "deu ruim...${e.message}")
            Toast.makeText(
                applicationContext, "formatação ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun preencherObjetoLista(userId: Int, title: String, body: String) {
        val post = Post(userId, title, body)
        postagens.add(post)
    }
}