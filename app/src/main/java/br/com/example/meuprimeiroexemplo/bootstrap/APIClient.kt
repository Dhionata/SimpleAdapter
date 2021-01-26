package br.com.example.meuprimeiroexemplo.bootstrap;

import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Passo 3 - Criar classe para mapear ENDPOINT e configurar classe para fazer o PARSER.
 * Converter JSON para objeto.
 **/
public class APIClient {

    //No retrofit você precisa colocar o  / (slash).
    //Prezado, por gentileza, informar fim de instrução (/) no endereço informado.
    private static final String ENDPOINT = "https://jsonplaceholder.typicode.com/";

    @Nullable
    public static Retrofit getClient() {
        try {
            return new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } catch (RuntimeException e) {
            Toast.makeText(null, "deu ruimno APICLiente", Toast.LENGTH_LONG).show();
            return null;
        }
    }
}