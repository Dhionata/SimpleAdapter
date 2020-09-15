package br.com.example.meuprimeiroexemplo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.debug.DebugActivity;

public class CommentsActivity extends DebugActivity {

    EditText txtPostId, txtNome, txtEmail,txtBody;
    ListView listViewPost;
    List<HashMap<String, String>> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
    }

    public void adicionarPost(View view) {
        //Entrada
        txtPostId = findViewById(R.id.txtPostId);
        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtBody = findViewById(R.id.txtBody);

        //Processamento
        String postId, nome, email ,body;
        postId = txtPostId.getText().toString();
        nome = txtNome.getText().toString();
        email = txtEmail.getText().toString();
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

        HashMap<String, String> map = new HashMap<>();
        map.put("postId", postId);
        map.put("nome", nome);
        map.put("email",email);
        map.put("body", body);

        lista.add(map);

        //Saída

        String[] from = {"postId", "nome", "email","body"}; //chaves do map
        int[] to = {R.id.txtItemPostId, R.id.txtItemNome, R.id.txtItemEmail, R.id.txtItemBody};//ids do layout do tipo "Item"
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, lista, R.layout.comments_item_post, from, to);

        listViewPost = findViewById(R.id.listViewPost);
        listViewPost.setAdapter(simpleAdapter);
    }
}
