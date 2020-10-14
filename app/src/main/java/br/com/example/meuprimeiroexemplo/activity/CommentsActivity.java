package br.com.example.meuprimeiroexemplo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.adapter.CommentsAdapter;
import br.com.example.meuprimeiroexemplo.debug.DebugActivity;
import br.com.example.meuprimeiroexemplo.model.Comments;
import br.com.example.meuprimeiroexemplo.model.Post;

public class CommentsActivity extends DebugActivity {

    EditText txtPostId, txtNome, txtEmail, txtBody;
    ListView listViewPost;
    final List<HashMap<String, String>> lista = new ArrayList<>();
    final List<Comments> comments = new ArrayList<>();
    final List<Post> postagens = new ArrayList<>();

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
        String postId, nome, email, body;

        postId = txtPostId.getText().toString();
        nome = txtNome.getText().toString();
        email = txtEmail.getText().toString();
        body = txtBody.getText().toString();

        Switch swithc = findViewById(R.id.switch2);
        if (swithc.isChecked()) {
            System.out.println("base sendo usado");
            baseAdapter(postId, nome, email, body);
        } else {
            System.out.println("simple sendo usado");
            simpleAdapter(postId, nome, email, body);
        }
    }

    //método
    private void baseAdapter(String postId, String nome, String email, String body) {

        preencherObjetoLista(postId, nome, email, body);

        listViewPost = findViewById(R.id.listViewPost2);

        CommentsAdapter postAdapter = new CommentsAdapter(this, comments);

        listViewPost.setAdapter(postAdapter);
    }

    private void preencherObjetoLista(String postId, String nome, String email, String body) {
        try {
            Integer idConvertido = Integer.parseInt(postId);
            Comments post = Comments.builder().postId(idConvertido).nome(nome).email(email).body(body).build();

            comments.add(post);
        } catch (Exception e) {
            Toast.makeText(this, "-- Erro --" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void simpleAdapter(String postId, String nome, String email, String body) {
        //postId,nome, email, body
        HashMap<String, String> map = new HashMap<>();
        map.put("postId", postId);
        map.put("nome", nome);
        map.put("email", email);
        map.put("body", body);

        lista.add(map);

        String[] chaves = {"postID", "nome", "email", "body"}; //chaves do map
        int[] vaiPara = {R.id.txtItemPostId, R.id.txtItemNome, R.id.txtItemEmail, R.id.txtItemBody2};//ids do layout do tipo "Item

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, lista, R.layout.comments_item_post, chaves, vaiPara);

        listViewPost = findViewById(R.id.listViewPost2);
        listViewPost.setAdapter(simpleAdapter);
    }

    @Override
    public String toString() {
        return "CommentsActivity{" +
                "txtPostId=" + txtPostId +
                ", txtNome=" + txtNome +
                ", txtEmail=" + txtEmail +
                ", txtBody=" + txtBody +
                ", listViewPost=" + listViewPost +
                ", lista=" + lista +
                ", comments=" + comments +
                ", postagens=" + postagens +
                '}';
    }
}
