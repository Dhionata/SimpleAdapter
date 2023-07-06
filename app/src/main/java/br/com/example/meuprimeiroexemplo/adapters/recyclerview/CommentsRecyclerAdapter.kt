package br.com.example.meuprimeiroexemplo.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.model.Comments
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

class CommentsRecyclerAdapter(
    private val context: Context,
    private val comments: List<Comments>
) : RecyclerView.Adapter<CommentsRecyclerAdapter.ViewHolder>() {

    init {
        MobileAds.initialize(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comments_item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtItemPostId: TextView = itemView.findViewById(R.id.txtItemPostId)
        private val txtItemId: TextView = itemView.findViewById(R.id.txtItemId)
        private val txtItemNome: TextView = itemView.findViewById(R.id.txtItemNome)
        private val txtItemEmail: TextView = itemView.findViewById(R.id.txtItemEmail)
        private val txtItemBody: TextView = itemView.findViewById(R.id.txtItemBody)
        private val adware: AdView = itemView.findViewById(R.id.adView3)

        fun bind(comment: Comments) {
            txtItemPostId.text = comment.postId.toString()
            txtItemId.text = comment.id.toString()
            txtItemNome.text = comment.name
            txtItemEmail.text = comment.email
            txtItemBody.text = comment.body

            val adRequest = AdRequest.Builder().build()
            adware.loadAd(adRequest)

            adware.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    Toast.makeText(
                        context,
                        "Cara, o Ad carregou ;D",
                        Toast.LENGTH_SHORT
                    ).show()
                    println("AD Carregou??")
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Toast.makeText(
                        context,
                        "Cara, o Ad NÃO carregou ;-;\n${adError.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    println("AD não Carregou??\n${adError.message}")
                }

                override fun onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }

                override fun onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                override fun onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
            }
        }
    }
}
