package br.com.example.meuprimeiroexemplo.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.model.Post

class PostAdapter(private val context: Context, private val postagens: List<Post>?) : BaseAdapter() {
	override fun getCount(): Int {
		return postagens?.size ?: 0
	}

	override fun getItem(position: Int): Post {
		return postagens!![position]
	}

	override fun getItemId(position: Int): Long {
		return position.toLong()
	}

	override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
		//Primeira coisa que precisamos verificar é se o layout está instanciado
		//senão, leio a referência do xml para objeto java.

		//Procura o item dentro da lista para ser 'exibido' na listView
		try {
			val post = getItem(position)

			//Criar a referência de atributos/objeto java para ser customizar uma listView
			val txtItemUserId: TextView = convertView.findViewById(R.id.txtItemUserId)
			val txtItemTitle: TextView = convertView.findViewById(R.id.txtItemTitle)
			val txtItemBody: TextView = convertView.findViewById(R.id.txtItemBody)
			txtItemUserId.text = post.userId.toString()
			txtItemTitle.text = post.title
			txtItemBody.text = post.body
		} catch (e: Exception) {
			Toast.makeText(null, "Deu ruim rapaz, o Adapter\n\n${e.message}", Toast.LENGTH_LONG).show()
		}
		return convertView
	}
}