package br.com.example.meuprimeiroexemplo.bootstrap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Passo 3 - Criar classe para mapear ENDPOINT e configurar classe para fazer o PARSER.
 * Converter JSON para objeto.
 **/
public class PeopleAPI {

    //No retrofit você precisa colocar o  / (slash).
    //Prezado, por gentileza, informar fim de instrução (/) no endereço informado.
    public static final String ENDPOINT = "https://swapi.dev/api/people/";

    public static Retrofit getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
