package br.com.example.meuprimeiroexemplo.adapters.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.example.meuprimeiroexemplo.R

class PostSimpleAdapter(
    private val context: Context?,
    private val data: MutableList<Map<String, String?>>,
    resource: Int,
    from: Array<out String>?,
    to: IntArray?
) : SimpleAdapter(context, data, resource, from, to) {

    private class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var btnExcluir: ImageButton
        lateinit var txtItemUserId: TextView
        lateinit var txtItemTitle: TextView
        lateinit var txtItemBody: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
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
            val item = getItem(position) as MutableMap<*, *>
            val userId = item["userId"].toString()
            val title = item["title"].toString()
            val body = item["body"].toString()

            viewHolder.txtItemUserId.text = userId
            viewHolder.txtItemTitle.text = title
            viewHolder.txtItemBody.text = body

            viewHolder.btnExcluir.setOnClickListener {
                data.removeAt(position)
                notifyDataSetChanged()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Deu ruim, o Adapter\n\n${e.message}", Toast.LENGTH_LONG)
                .show()
        }

        return view!!
    }
}
