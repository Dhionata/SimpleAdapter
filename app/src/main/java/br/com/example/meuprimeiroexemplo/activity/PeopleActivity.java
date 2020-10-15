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

        PeopleResource postResource = retrofit.create(PeopleResource.class);

        //Fazer o método/operação pretendido.

        Call<List<People>> lista = postResource.get();

        // Utilizar a estrutura de dados FILA (FIFO) para trabalhar
        //com chamadas assíncronas.

        try {
            lista.enqueue(new Callback<List<People>>() {
                @Override
                public void onResponse(Call<List<People>> call, Response<List<People>> response) {
                    // O método onResponse retorna os dados do recurso(resource) consumido.
                    try {
                        List<People> pessoas = response.body();
                        
                        for (int i = 0; i < pessoas.size(); i++) {
                            Log.i("post", String.format("%d %s", i,
                                    pessoas.get(i).toString()));

                            baseAdapter(pessoas.get(i).getName(), pessoas.get(i).getHair_color(),
                                    pessoas.get(i).getSkin_color(), pessoas.get(i).getEye_color(),
                                    pessoas.get(i).getBirth_year(), pessoas.get(i).getGender(),
                                    pessoas.get(i).getMass(), pessoas.get(i).getHeight());
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro " +
                                        "no serviço.\n" + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                        System.out.println("\n\n" + e.getMessage() + "\n\n");
                    }
                }

                @Override
                public void onFailure(Call<List<People>> call, Throwable t) {
                    //Método responsável pelos erros.
                    Toast.makeText(getApplicationContext(), "Ocorreu um erro " +
                            "no serviço.\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    System.out.println("\n\n" + t.getMessage() + "\n\n");
                }
            });
        } catch (Exception e) {
            System.out.println("--Deu ruim...--\n" + e.getMessage());
        }
    }

    private void baseAdapter(String nome, String corDoCabelo, String corDePele,
                             String corDosOlhos, Integer anoDeNascimento,
                             String genero, Integer massa, Integer altura) {

        preencherObjetoLista(nome, corDoCabelo, altura, corDePele, corDosOlhos,
                anoDeNascimento, genero, massa);

        listViewPeople = findViewById(R.id.peopleList);

        PeopleAdapter peopleAdapter = new PeopleAdapter(this, pessoas);

        listViewPeople.setAdapter(peopleAdapter);
    }

    private void preencherObjetoLista(String nome, String corDoCabelo,
                                      Integer altura,
                                      String corDePele, String corDosOlhos,
                                      Integer anoDeNascimento, String genero,
                                      Integer massa) {
        try {
            People people =
                    People.builder().name(nome).mass(massa).birth_year(anoDeNascimento).gender(genero).eye_color(corDosOlhos).
                            hair_color(corDoCabelo).height(altura).skin_color(corDePele).build();

            pessoas.add(people);
        } catch (Exception e) {
            Toast.makeText(this, "-- Erro --" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}