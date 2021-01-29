package br.com.example.meuprimeiroexemplo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.model.Comments
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

class CommentsAdapter(private val context: Context, private val comments: List<Comments>) : BaseAdapter() {

	init {
		MobileAds.initialize(context)
	}

	override fun getCount(): Int {
		return comments.size
	}

	override fun getItem(position: Int): Comments {
		return comments[position]
	}

	override fun getItemId(position: Int): Long {
		return position.toLong()
	}

	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		//Primeira coisa que precisamos verificar é se o layout está instanciado
		//senão, leio a referência do xml para objeto java.
		var converterVisualizacao = convertView
		if (null == converterVisualizacao) {
			converterVisualizacao = LayoutInflater.from(context).inflate(R.layout.comments_item_post, parent, false)
		}

		//Procura o item dentro da lista para ser 'exibido' na listView
		val comments = getItem(position)

		//Criar a referência de atributos/objeto java para ser customizar uma listView
		val txtItemPostId: TextView
		val txtItemId: TextView
		val txtItemNome: TextView
		val txtItemEmail: TextView
		val txtItemBody: TextView

		if (null != converterVisualizacao) {
			txtItemPostId = converterVisualizacao.findViewById(R.id.txtItemPostId)
			txtItemId = converterVisualizacao.findViewById(R.id.txtItemId)
			txtItemNome = converterVisualizacao.findViewById(R.id.txtItemNome)
			txtItemEmail = converterVisualizacao.findViewById(R.id.txtItemEmail)
			txtItemBody = converterVisualizacao.findViewById(R.id.txtItemBody)
			txtItemPostId.text = comments.postId.toString()
			txtItemId.text = comments.id.toString()
			txtItemNome.text = comments.name
			txtItemEmail.text = comments.email
			txtItemBody.text = comments.body

			val adware: AdView = converterVisualizacao.findViewById(R.id.adView3)
			val adRequest = AdRequest.Builder().build()
			adware.loadAd(adRequest)

			adware.adListener = object : AdListener() {
				override fun onAdLoaded() {
					Toast.makeText(null, "Cara, o Ad carregou ;D", Toast.LENGTH_SHORT).show()
					println("AD Carregou??")
				}

				override fun onAdFailedToLoad(adError: LoadAdError) {
					Toast.makeText(null, "Cara, o Ad NÃO carregou ;-;\n${adError.message}", Toast.LENGTH_SHORT).show()
					println("AD não Carregou??\n${adError.message}")
				}

				override fun onAdOpened() {
					// Code to be executed when an ad opens an overlay that
					// covers the screen.
				}

				override fun onAdClicked() {
					// Code to be executed when the user clicks on an ad.
				}

				override fun onAdLeftApplication() {
					// Code to be executed when the user has left the app.
				}

				override fun onAdClosed() {
					// Code to be executed when the user is about to return
					// to the app after tapping on an ad.
				}
			}

		}
		return converterVisualizacao!!
	}
}