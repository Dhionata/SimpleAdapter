package br.com.example.meuprimeiroexemplo.adapters.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.model.Post

class PostArrayAdapter(
    private val context: Context,
    resource: Int,
    textViewResourceId: Int,
    private val objects: MutableList<Post>
) : ArrayAdapter<Post>(context, resource, textViewResourceId, objects) {

    override fun getCount(): Int {
        return objects.size
    }

    override fun getItem(position: Int): Post {
        return objects[position]
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

            viewHolder.btnExcluir.setOnClickListener {
                objects[position].let { post ->
                    objects.remove(post)
                    notifyDataSetChanged()
                }
            }

        } catch (e: Exception) {
            Toast.makeText(context, "Deu ruim, o Adapter\n\n${e.message}", Toast.LENGTH_LONG).show()
        }

        return view!!
    }

    private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var btnExcluir: ImageButton
        lateinit var txtItemUserId: TextView
        lateinit var txtItemTitle: TextView
        lateinit var txtItemBody: TextView
    }
}