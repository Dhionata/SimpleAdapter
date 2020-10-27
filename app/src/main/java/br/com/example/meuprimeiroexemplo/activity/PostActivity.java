package br.com.example.meuprimeiroexemplo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.adapter.PostAdapter;
import br.com.example.meuprimeiroexemplo.debug.DebugActivity;
import br.com.example.meuprimeiroexemplo.model.Post;

public class PostActivity extends DebugActivity {

    EditText txtUserId, txtTitle, txtBody;
    ListView listViewPost;
    final List<HashMap<String, String>> lista = new ArrayList<>(); //2 - Melancia - Melancia faz bem pro estomago.
    final List<Post> postagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void adicionarPost(View view) {
        //Entrada
        txtUserId = findViewById(R.id.txtUserId);
        txtTitle = findViewById(R.id.txtTitle);
        txtBody = findViewById(R.id.txtBody);

        //Processamento
        String title, body;
        Integer userId;
        try {
            userId = Integer.parseInt(txtUserId.getText().toString());
            title = txtTitle.getText().toString();
            body = txtBody.getText().toString();

            SwitchCompat swithc = findViewById(R.id.switch1);
            if (swithc.isChecked()) {
                baseAdapter(userId, title, body);
            } else {
                simpleAdapter(userId, title, body);
            }
        } catch (NumberFormatException e) {
            Log.i("formatação", "deu ruim..." + e.getMessage());
            Toast.makeText(getApplicationContext(), "formatação" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void baseAdapter(Integer userId, String title, String body) {

        preencherObjetoLista(userId, title, body);

        listViewPost = findViewById(R.id.listViewPost);

        PostAdapter postAdapter = new PostAdapter(getApplicationContext(), postagens);

        listViewPost.setAdapter(postAdapter);
    }

    private void preencherObjetoLista(Integer userId, String title, String body) {
        try {
            Post post =
                    Post.builder().userId(userId).title(title).body(body).build();

            postagens.add(post);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "-- Erro --\n\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void simpleAdapter(Integer userId, String title, String body) {
        try {
            String x = String.valueOf(userId);

            HashMap<String, String> map = new HashMap<>();
            map.put("userId", x);
            map.put("title", title);
            map.put("body", body);

            lista.add(map);

            //chave : valor
            //nome: Dhionatã
            //endereço: trindade

            //chave == identificar nome: endereço: pais:
            //valor == dhionatã: trindade: Brasil
            //Saída

            String[] chaves = {"userId", "title", "body"}; //chaves do map
            int[] vaiPara = {R.id.txtItemUserId, R.id.txtItemTitle, R.id.txtItemBody};//ids do layout do tipo "Item

            SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), lista, R.layout.item_post, chaves, vaiPara);

            listViewPost = findViewById(R.id.listViewPost);
            listViewPost.setAdapter(simpleAdapter);
        } catch (Exception e) {
            Log.i("Falha SimpleAdapter", "\n\n" + e.getMessage());
            Toast.makeText(getApplicationContext(),
                    "Falha SimpleAdapter\n\n" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

    }

    /*private void arrayAdapter(String userId, String title, String body) {

        preencherObjetoLista(userId, title, body);

        listViewPost = findViewById(R.id.listViewPost);

        ArrayAdapter<Post> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, postagens);
        listViewPost.setAdapter(arrayAdapter);
    }*/
}