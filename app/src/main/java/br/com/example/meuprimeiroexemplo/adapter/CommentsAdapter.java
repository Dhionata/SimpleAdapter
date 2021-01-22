package br.com.example.meuprimeiroexemplo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.model.Comments;

public class CommentsAdapter extends BaseAdapter {

    private final Context context;
    private final List<Comments> comments;

    public CommentsAdapter(Context context, List<Comments> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comments getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Primeira coisa que precisamos verificar é se o layout está instanciado
        //senão, leio a referência do xml para objeto java.
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.comments_item_post, parent, false);
        }

        //Procura o item dentro da lista para ser 'exibido' na listView
        Comments comments = getItem(position);

        //Criar a referência de atributos/objeto java para ser customizar uma listView
        TextView txtItemPostId, txtItemId, txtItemNome, txtItemEmail,
                txtItemBody;

        txtItemPostId = convertView.findViewById(R.id.txtItemPostId);
        txtItemId = convertView.findViewById(R.id.txtItemId);
        txtItemNome = convertView.findViewById(R.id.txtItemNome);
        txtItemEmail = convertView.findViewById(R.id.txtItemEmail);
        txtItemBody = convertView.findViewById(R.id.txtItemBody);

        txtItemPostId.setText(String.valueOf(comments.getPostId()));
        txtItemId.setText(String.valueOf(comments.getId()));
        txtItemNome.setText(comments.getName());
        txtItemEmail.setText(comments.getEmail());
        txtItemBody.setText(comments.getBody());

        return convertView;
    }
}
