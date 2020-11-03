package br.com.example.meuprimeiroexemplo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
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

    ListView listViewPeople;
    List<People> pessoas = new ArrayList<>();

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
//psé... tentei.. kkk
        // Utilizar a estrutura de dados FILA (FIFO) para trabalhar
        //com chamadas assíncronas.
        try {
            lista.enqueue(new Callback<DefaultModel>() {
                @Override
                public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                    // O método onResponse retorna os dados do recurso(resource) consumido.
                    try {
                        //recebe os dados da lista (paginada) 10 em 10
                        DefaultModel resposta = response.body();

                        //Chamar o adapter para imprimir os dados na listView
                        PeopleAdapter peopleAdapter = new PeopleAdapter(getApplicationContext(),
                                resposta.getResults());

                        //Chamar a listView para fazer o bind de informações.
                        listViewPeople = findViewById(R.id.peopleList);
                        listViewPeople.setAdapter(peopleAdapter);

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
            Log.i("-- Deu ruim... --\n", "-- Erro --\n\n" + e.getMessage());
        }
    }

    private void preencherObjetoLista(String nome, String corDoCabelo,
                                      String altura,
                                      String corDePele, String corDosOlhos,
                                      String anoDeNascimento, String genero,
                                      String massa) {
        try {
            People people =
                    People.builder().name(nome).mass(massa).birth_year(anoDeNascimento).gender(genero).eye_color(corDosOlhos).
                            hair_color(corDoCabelo).height(altura).skin_color(corDePele).build();

            pessoas.add(people);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "-- Erro --" + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.i("app-peole", e.getMessage());
        }
    }

    private void baseAdapter(String nome, String corDoCabelo, String corDePele,
                             String corDosOlhos, String anoDeNascimento,
                             String genero, String massa, String altura) {

        preencherObjetoLista(nome, corDoCabelo, altura, corDePele, corDosOlhos,
                anoDeNascimento, genero, massa);

        listViewPeople = findViewById(R.id.peopleList);

        PeopleAdapter peopleAdapter = new PeopleAdapter(getApplicationContext(), pessoas);

        listViewPeople.setAdapter(peopleAdapter);
    }
}