package br.com.example.meuprimeiroexemplo.bootstrap

import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Passo 3 - Criar classe para mapear ENDPOINT e configurar classe para fazer o PARSER.
 * Converter JSON para objeto.
 */
object APIClient {
    //No retrofit você precisa colocar o  / (slash).
    //Prezado, por gentileza, informar fim de instrução (/) no endereço informado.
    private const val ENDPOINT = "https://jsonplaceholder.typicode.com/"
    val client: Retrofit?
        get() = try {
            Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        } catch (e: RuntimeException) {
            Toast.makeText(null, "deu ruimno APICLiente", Toast.LENGTH_LONG).show()
            null
        }
}