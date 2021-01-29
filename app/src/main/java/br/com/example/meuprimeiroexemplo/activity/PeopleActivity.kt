package br.com.example.meuprimeiroexemplo.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.adapter.PeopleAdapter
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

	//Métod de exemplo para listar os dados de um serviço na internet
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
			Toast.makeText(applicationContext, "Cara, o retrofit não tá pegando não kk${e.message}", Toast.LENGTH_LONG).show()
		}
		try {
			lista?.enqueue(object : Callback<DefaultModel?> {
				override fun onResponse(call: Call<DefaultModel?>, response: Response<DefaultModel?>) {
					// O método onResponse retorna os dados do recurso(resource) consumido.
					try {
						val pessoas = response.body()?.results
						val p = PeopleAdapter(applicationContext, pessoas)
						val listViewPeople: ListView = findViewById(R.id.peopleList)
						listViewPeople.adapter = p
						val b = findViewById<Button>(R.id.usarAPI)
						b.isClickable = false
					} catch (e: Throwable) {
						Toast.makeText(applicationContext, "Ocorreu um erro no processamento.${e.message}", Toast.LENGTH_LONG).show()
						Log.e("lista -- Erro $", "Deu ruim...\n\n" + e.message, e)
					}
				}

				override fun onFailure(call: Call<DefaultModel?>, t: Throwable) {
					//Método responsável pelos erros.
					Toast.makeText(applicationContext, " Ocorreu um erro no serviço.${t.message}", Toast.LENGTH_LONG).show()
					Log.e("app-people", "deu ruim dnv...\n\n" + t.message, t)
				}
			})
		} catch (e: Throwable) {
			Log.e("-- Deu ruim... --\n", "-- Erro --${e.message}")
		}
	}
}