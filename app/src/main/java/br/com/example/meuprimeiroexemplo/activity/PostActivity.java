package br.com.example.meuprimeiroexemplo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

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
        String userId, title, body;
        userId = txtUserId.getText().toString();
        title = txtTitle.getText().toString();
        body = txtBody.getText().toString();

        //Agora vamos inicar os trabalhos para o SimpleAdapter


        //SimpleAdapter precisa de um List<?> extends Map<Spring. ?>

        /*List<String> bla = new ArrayList<>();
        bla.add("");
        bla.add("");
        bla.add("");
        bla.add("");
        bla.add("");

        HashMap<String,String> map = new HashMap<>();

        map.put("index1","valor1");
        map.put("index2","valor2");
        map.put("index3","valor3");
        map.put("index4","valor4");*/
        Switch swithc = findViewById(R.id.switch1);
        if(swithc.isChecked()){
            baseAdapter(userId,title,body);
        }else{
        simpleAdapter(userId, title, body);
    }}
//método
    private void baseAdapter(String userId,String title, String body){

        preencherObjetoLista(userId,title,body);

        listViewPost = findViewById(R.id.listViewPost);

        PostAdapter postAdapter = new PostAdapter(this,postagens);

        listViewPost.setAdapter(postAdapter);
    }

    private void preencherObjetoLista(String userId, String title, String body) {
        try {
            Integer idConvertido = Integer.parseInt(userId);
            Post post = Post.builder().userId(idConvertido).title(title).body(body).build();

            postagens.add(post);
        }catch (Exception e){
            Toast.makeText(this, "-- Erro --"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void simpleAdapter(String userId, String title, String body) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId);
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

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, lista, R.layout.item_post, chaves, vaiPara);

        listViewPost = findViewById(R.id.listViewPost);
        listViewPost.setAdapter(simpleAdapter);
    }

    private void arrayAdapter(String userId, String title,String body){

        preencherObjetoLista(userId, title, body);

        listViewPost = findViewById(R.id.listViewPost);

        ArrayAdapter<Post> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postagens);
        listViewPost.setAdapter(arrayAdapter);
    }
}