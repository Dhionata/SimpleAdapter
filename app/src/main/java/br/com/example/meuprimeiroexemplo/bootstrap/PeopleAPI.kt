package br.com.example.meuprimeiroexemplo.bootstrap

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Passo 3 - Criar classe para mapear ENDPOINT e configurar classe para fazer o PARSER.
 * Converter JSON para objeto.
 */
object PeopleAPI {
    //No retrofit você precisa colocar o  / (slash).
    //Prezado, por gentileza, informar fim de instrução (/) no endereço informado.
    private const val ENDPOINT = "https://swapi.dev/api/"
    val client: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}