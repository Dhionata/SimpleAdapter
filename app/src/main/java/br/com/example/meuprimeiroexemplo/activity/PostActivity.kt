package br.com.example.meuprimeiroexemplo.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.adapter.PostAdapter
import br.com.example.meuprimeiroexemplo.debug.DebugActivity
import br.com.example.meuprimeiroexemplo.model.Post

class PostActivity : DebugActivity() {
    private val lista: MutableList<HashMap<String, String?>> = ArrayList()
    private val postagens: MutableList<Post> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
    }

    fun adicionarPost(view: View) {
        //Entrada
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
            val swithc = findViewById<SwitchCompat>(R.id.switch1)
            if (swithc.isChecked) {
                baseAdapter(userId, title, body)
            } else {
                simpleAdapter(userId, title, body)
            }
        } catch (e: NumberFormatException) {
            Log.i("formatação", "deu ruim..." + e.message)
            Toast.makeText(
                applicationContext, "formatação" + e.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun baseAdapter(userId: Int, title: String, body: String) {
        preencherObjetoLista(userId, title, body)
        val listViewPost = findViewById<ListView>(R.id.listViewPost)
        val postAdapter: ListAdapter = PostAdapter(applicationContext, postagens)
        listViewPost.adapter = postAdapter
    }

    private fun preencherObjetoLista(userId: Int, title: String, body: String) {
        try {
            val post = Post(userId, title, body)
            postagens.add(post)
        } catch (e: RuntimeException) {
            Toast.makeText(applicationContext, "-- Erro --${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun simpleAdapter(userId: Int, title: String, body: String) {
        try {
            val x = userId.toString()
            val map = HashMap<String, String?>()
            map["userId"] = x
            map["title"] = title
            map["body"] = body
            lista.add(map)

            val chaves = arrayOf("userId", "title", "body") //chaves do map
            val vaiPara = intArrayOf(
                R.id.txtItemUserId,
                R.id.txtItemTitle,
                R.id.txtItemBody
            ) //ids do layout do tipo "Item
            val simpleAdapter =
                SimpleAdapter(applicationContext, lista, R.layout.item_post, chaves, vaiPara)
            val listViewPost: ListView = findViewById(R.id.listViewPost)
            listViewPost.adapter = simpleAdapter
        } catch (e: RuntimeException) {
            Log.i("Falha SimpleAdapter", "Deu ruim...\n${e.message}", e)
            Toast.makeText(
                applicationContext,
                "Falha SimpleAdapter ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    } /*private void arrayAdapter(String userId, String title, String body) {

        preencherObjetoLista(userId, title, body);

        listViewPost = findViewById(R.id.listViewPost);

        ArrayAdapter<Post> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, postagens);
        listViewPost.setAdapter(arrayAdapter);
    }*/
}