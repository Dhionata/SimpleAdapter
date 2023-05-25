package br.com.example.meuprimeiroexemplo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.debug.DebugActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration


class HomeActivity : DebugActivity() {
    private lateinit var mAdView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        MobileAds.initialize(applicationContext) {}
        mAdView = findViewById(R.id.adView)

        val adRequest = AdRequest.Builder().build()
        //configuração só precisa ser feito uma vez.
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("840B3EC26EE84AC7EC414306F395FE3E")).build()
        )
        mAdView.loadAd(adRequest)

        mAdView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Toast.makeText(applicationContext, "Cara, o Ad carregou ;D", Toast.LENGTH_LONG)
                    .show()
                println("AD Carregou??")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                Toast.makeText(
                    applicationContext,
                    "Cara, o Ad NÃO carregou ;-;\n ${adError.message}",
                    Toast.LENGTH_LONG
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

    fun exibir(view: View) {
        //
        val opcao = view.id
        val intent: Intent
        when (opcao) {
            R.id.btnAddres -> {
                intent = Intent(applicationContext, AddresActivity::class.java)
                startActivity(intent)
            }

            R.id.btnUse -> {
                intent = Intent(applicationContext, UserActivity::class.java)
                startActivity(intent)
            }

            R.id.btnPessoa -> {
                intent = Intent(applicationContext, PessoaActivity::class.java)
                startActivity(intent)
            }

            R.id.btnPost -> {
                intent = Intent(applicationContext, PostActivity::class.java)
                startActivity(intent)
            }

            R.id.btnComments -> {
                intent = Intent(applicationContext, CommentsActivity::class.java)
                startActivity(intent)
            }

            R.id.btnPeople -> {
                intent = Intent(applicationContext, PeopleActivity::class.java)
                startActivity(intent)
            }

            else -> Toast.makeText(applicationContext, "Opção inválida.", Toast.LENGTH_LONG).show()
        }
    }
}