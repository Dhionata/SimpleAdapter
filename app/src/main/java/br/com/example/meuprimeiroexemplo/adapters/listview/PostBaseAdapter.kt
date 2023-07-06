package br.com.example.meuprimeiroexemplo.adapters.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.model.Post

class PostBaseAdapter(private val context: Context, private val postagens: MutableList<Post>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return postagens.size ?: 0
    }

    override fun getItem(position: Int): Post {
        return postagens[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val viewHolder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
            viewHolder = ViewHolder(view)
            viewHolder.txtItemUserId = view.findViewById(R.id.txtItemUserId)
            viewHolder.txtItemTitle = view.findViewById(R.id.txtItemTitle)
            viewHolder.txtItemBody = view.findViewById(R.id.txtItemBody)
            viewHolder.btnExcluir = view.findViewById(R.id.btnExcluir)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        try {
            val post = getItem(position)
            viewHolder.txtItemUserId.text = post.userId.toString()
            viewHolder.txtItemTitle.text = post.title
            viewHolder.txtItemBody.text = post.body

            // Configure o listener de clique para o botão de exclusão
            viewHolder.btnExcluir.setOnClickListener {
                // Remova a postagem correspondente à posição atual
                postagens[position].let { post ->
                    postagens.remove(post)
                    notifyDataSetChanged()
                }
                notifyDataSetChanged()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Deu ruim, o Adapter\n\n${e.message}", Toast.LENGTH_LONG).show()
        }

        return view!!
    }

    private class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var btnExcluir: ImageButton
        lateinit var txtItemUserId: TextView
        lateinit var txtItemTitle: TextView
        lateinit var txtItemBody: TextView
    }


}