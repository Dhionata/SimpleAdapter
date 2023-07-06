package br.com.example.meuprimeiroexemplo.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.adapters.recyclerview.PeopleRecyclerAdapter
import br.com.example.meuprimeiroexemplo.bootstrap.PeopleAPI
import br.com.example.meuprimeiroexemplo.debug.DebugActivity
import br.com.example.meuprimeiroexemplo.model.DefaultModel
import br.com.example.meuprimeiroexemplo.resource.PeopleResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PeopleActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)
    }

    //Método de exemplo para listar os dados de um serviço na internet
    //Utilizando o retrofit
    fun listarPosts(view: View) {
        //Passo 6 - Criar função para trabalhar com o retrofit
        var lista: Call<DefaultModel>? = null
        try {
            val retrofit = PeopleAPI.client

            //Fazer a Inverção de Controle e injeção de dependência da interface
            //(contrato) PostResource
            val peopleResource = retrofit.create(PeopleResource::class.java)

            //Fazer o método/operação pretendido.
            lista = peopleResource.get()
        } catch (e: Throwable) {
            Toast.makeText(
                applicationContext,
                "Cara, o retrofit não tá pegando não kk${e.message}",
                Toast.LENGTH_LONG
            ).show()
            Log.e(PeopleActivity::listarPosts.toString(), "${e.message}")
        }
        try {
            lista?.enqueue(object : Callback<DefaultModel?> {
                override fun onResponse(
                    call: Call<DefaultModel?>,
                    response: Response<DefaultModel?>
                ) {
                    // O método onResponse retorna os dados do recurso(resource) consumido.
                    try {
                        val pessoas = response.body()?.results
                        val peopleRecyclerAdapter =
                            PeopleRecyclerAdapter(applicationContext, pessoas)
                        val recyclerViewPeople: RecyclerView = findViewById(R.id.peopleList)
                        recyclerViewPeople.adapter = peopleRecyclerAdapter
                        val button = findViewById<Button>(R.id.usarAPI)
                        button.isClickable = false
                    } catch (throwable: Throwable) {
                        Toast.makeText(
                            applicationContext,
                            "Ocorreu um erro no processamento.${throwable.message}",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.e(
                            PeopleActivity::listarPosts.toString(), "Deu ruim...${
                                throwable.message
                            }",
                            throwable
                        )
                    }
                }

                override fun onFailure(call: Call<DefaultModel?>, throwable: Throwable) {
                    //Método responsável pelos erros.
                    Toast.makeText(
                        applicationContext,
                        " Ocorreu um erro no serviço.${throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e(
                        PeopleActivity::listarPosts.toString(),
                        "deu ruim dnv...${throwable.message}"
                    )
                }
            })
        } catch (throwable: Throwable) {
            Log.e(PeopleActivity::listarPosts.toString(), "-- Erro --${throwable.message}")
        }
    }
}