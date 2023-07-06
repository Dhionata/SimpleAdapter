package br.com.example.meuprimeiroexemplo.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.adapters.recyclerview.CommentsRecyclerAdapter
import br.com.example.meuprimeiroexemplo.bootstrap.APIClient
import br.com.example.meuprimeiroexemplo.debug.DebugActivity
import br.com.example.meuprimeiroexemplo.model.Comments
import br.com.example.meuprimeiroexemplo.resource.CommentsResource
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class CommentsActivity : DebugActivity() {
    private val comments: MutableList<Comments> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        //AdMob ;D

        MobileAds.initialize(applicationContext) {}
        val mAdView = findViewById<AdView>(R.id.adView2)

        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mAdView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Toast.makeText(applicationContext, "Cara, o Ad carregou ;D", Toast.LENGTH_SHORT)
                    .show()
                println("AD Carregou")
                Log.i("AD", "AD loaded")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                Toast.makeText(
                    applicationContext,
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

    fun addComments(view: View?) {
        //Entrada
        try {
            val retrofit = APIClient.client
            val commentsResource = retrofit!!.create(CommentsResource::class.java)
            val chamada = commentsResource.get()

            chamada.enqueue(object : Callback<List<Comments>> {
                override fun onResponse(
                    call: Call<List<Comments>>,
                    response: Response<List<Comments>>
                ) {
                    Toast.makeText(applicationContext, "deu certo mano...", Toast.LENGTH_SHORT)
                        .show()
                    val postagens = response.body()
                    if (postagens != null) {
                        for (p in postagens) {
                            baseAdapter(p.postId, p.id, p.name, p.email, p.body)
                        }
                    }
                }

                override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "deu certo mano... mas tá sem net kk",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
            /*val b = findViewById<Button>(R.id.btnAddPost)
            b.isClickable = false
            if (!b.isClickable) {
                Toast.makeText(applicationContext, "Você não pode mais clicar!",
                        Toast.LENGTH_LONG).show()
            }*/
        } catch (e: RuntimeException) {
            Toast.makeText(
                applicationContext,
                "Deu ruim meu amigo...Erro-- ${e.message}",
                Toast.LENGTH_LONG
            ).show()
            println("\n\n\nLinha abaixo é de erro!!\n\n\n${e.message}")
        }
    }

    //método
    protected fun baseAdapter(postId: Int, id: Int, nome: String, email: String, body: String) {
        try {
            preencherObjetoLista(postId, id, nome, email, body)

            val recyclerViewPost = findViewById<RecyclerView>(R.id.listViewComments)
            val commentsRecyclerAdapter = CommentsRecyclerAdapter(applicationContext, comments)
            recyclerViewPost.adapter = commentsRecyclerAdapter
        } catch (e: RuntimeException) {
            Toast.makeText(
                applicationContext,
                "Não conseguiu usar o CommentsAdapter...${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun preencherObjetoLista(
        postId: Int,
        id: Int,
        name: String,
        email: String,
        body: String
    ) {
        try {
            val comment = Comments(id, postId, name, email, body)
            comments.add(comment)
        } catch (e: RuntimeException) {
            Log.i("prencherObjetoLista\n\n\"", e.message ?: return)
            Toast.makeText(
                applicationContext, " -- Erro -- ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}