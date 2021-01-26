package br.com.example.meuprimeiroexemplo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.adapter.PeopleAdapter;
import br.com.example.meuprimeiroexemplo.bootstrap.PeopleAPI;
import br.com.example.meuprimeiroexemplo.debug.DebugActivity;
import br.com.example.meuprimeiroexemplo.model.DefaultModel;
import br.com.example.meuprimeiroexemplo.model.People;
import br.com.example.meuprimeiroexemplo.resource.PeopleResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PeopleActivity extends DebugActivity {

    private ListView listViewPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
    }

    //Métod de exemplo para listar os dados de um serviço na internet
    //Utilizando o retrofit
    public void listarPosts(View view) {
        //Passo 6 - Criar função para trabalhar com o retrofit

        Retrofit retrofit = PeopleAPI.getClient();

        //Fazer a Inverção de Controle e injeção de dependência da interface
        //(contrato) PostResource

        PeopleResource peopleResource = retrofit.create(PeopleResource.class);

        //Fazer o método/operação pretendido.

        Call<DefaultModel> lista = peopleResource.get();

        try {
            lista.enqueue(new Callback<DefaultModel>() {
                @Override
                public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                    // O método onResponse retorna os dados do recurso(resource) consumido.
                    try {

                        List<People> pessoas = response.body().getResults();

                        PeopleAdapter p =
                                new PeopleAdapter(getApplicationContext(), pessoas);

                        listViewPeople = findViewById(R.id.peopleList);
                        listViewPeople.setAdapter(p);

                        Button b = findViewById(R.id.usarAPI);
                        b.setClickable(false);

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro " +
                                        "no processamento.\n\n" + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                        Log.e("lista -- Erro", "\n\n" + e.getMessage() + "\n" +
                                "\n");
                    }
                }

                @Override
                public void onFailure(Call<DefaultModel> call, Throwable t) {
                    //Método responsável pelos erros.
                    Toast.makeText(getApplicationContext(), "Ocorreu um erro " +
                            "no serviço.\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("app-people", "\n\n" + t.getMessage() + "\n\n");
                }
            });
        } catch (Exception e) {
            Log.e("-- Deu ruim... --\n", "-- Erro --\n\n" + e.getMessage());
        }
    }
}