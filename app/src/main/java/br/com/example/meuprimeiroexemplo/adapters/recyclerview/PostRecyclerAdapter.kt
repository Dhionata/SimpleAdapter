package br.com.example.meuprimeiroexemplo.adapters.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.model.Post

class PostRecyclerAdapter(
    private val context: Context,
    private val posts: ArrayList<Post>,
    val userId: TextView,
    val title: TextView,
    val body: TextView
) : RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtItemUserId: TextView = itemView.findViewById(R.id.txtItemUserId)
        private val txtItemTitle: TextView = itemView.findViewById(R.id.txtItemTitle)
        private val txtItemBody: TextView = itemView.findViewById(R.id.txtItemBody)
        private val btnExcluir: ImageButton = itemView.findViewById(R.id.btnExcluir)
        private val btnEditar: ImageButton = itemView.findViewById(R.id.btnEditar)

        fun bind(post: Post) {
            txtItemUserId.text = post.userId.toString()
            txtItemTitle.text = post.title
            txtItemBody.text = post.body

            btnExcluir.setOnClickListener {
                posts.remove(post)
                notifyItemRemoved(posts.indexOf(post))
            }

            btnEditar.setOnClickListener {
                try {
                    post.userId = userId.text.toString().toInt()
                    post.title = title.text.toString()
                    post.body = body.text.toString()
                    notifyItemChanged(posts.indexOf(post))
                } catch (e: Exception) {
                    System.err.println("Err: ${e.message}")
                    Log.e("formatação", "deu ruim...${e.message}")
                    Toast.makeText(
                        context, "formatação ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
