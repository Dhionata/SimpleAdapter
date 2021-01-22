package br.com.example.meuprimeiroexemplo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.model.Post;

public class PostAdapter extends BaseAdapter {

    private final Context context;
    private final List<Post> postagens;

    public PostAdapter(Context context, List<Post> postagens) {
        this.context = context;
        this.postagens = postagens;
    }

    @Override
    public int getCount() {
        return null != postagens ? postagens.size() : 0;
    }

    @Override
    public Post getItem(int position) {
        return postagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Primeira coisa que precisamos verificar é se o layout está instanciado
        //senão, leio a referência do xml para objeto java.
        View view = convertView;
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        }

        //Procura o item dentro da lista para ser 'exibido' na listView
        Post post = getItem(position);

        //Criar a referência de atributos/objeto java para ser customizar uma listView
        TextView txtItemUserId, txtItemTitle, txtItemBody;

        txtItemUserId = view.findViewById(R.id.txtItemUserId);
        txtItemTitle = view.findViewById(R.id.txtItemTitle);
        txtItemBody = view.findViewById(R.id.txtItemBody);

        txtItemUserId.setText(String.valueOf(post.getUserId()));
        txtItemTitle.setText(post.getTitle());
        txtItemBody.setText(post.getBody());

        return view;
    }
}
