package br.com.example.meuprimeiroexemplo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.model.People;

public class PeopleAdapter extends BaseAdapter {

    private final Context context;
    private final List<People> people;

    public PeopleAdapter(@NonNull Context context, @NonNull List<People> people) {
        this.context = context;
        this.people = people;
    }

    @Override
    public int getCount() {
        return this.people != null ? this.people.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return this.people.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        //Primeira coisa que precisamos verificar é se o layout está instanciado
        //senão, leio a referência do xml para objeto java.
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.api_post,
                    viewGroup, false);
        }

        //Procura o item dentro da lista para ser 'exibido' na listView
        People people = (People) getItem(posicao);

        //Criar a referência de atributos/objeto java para ser customizar uma listView
        TextView txtItemName, txtItemHeight, txtItemMass, txtItemHairColor,
                txtItemSkinColor, txtItemEyeColor, txtItemBirthYear, txtItemGender;

        try {
            txtItemName = view.findViewById(R.id.textName);
            txtItemHeight = view.findViewById(R.id.textHeight);
            txtItemMass = view.findViewById(R.id.textMass);
            txtItemHairColor = view.findViewById(R.id.textHairColor);
            txtItemSkinColor = view.findViewById(R.id.textSkinColor);
            txtItemEyeColor = view.findViewById(R.id.textEyeColor);
            txtItemBirthYear = view.findViewById(R.id.textBirthYear);
            txtItemGender = view.findViewById(R.id.textGender);

            txtItemName.setText(people.getName());
            txtItemHeight.setText(String.valueOf(people.getHeight()));
            txtItemMass.setText(String.valueOf(people.getMass()));
            txtItemHairColor.setText(people.getHair_color());
            txtItemSkinColor.setText(people.getSkin_color());
            txtItemEyeColor.setText(people.getEye_color());
            txtItemBirthYear.setText(String.valueOf(people.getBirth_year()));
            txtItemGender.setText(people.getGender());
        } catch (Exception e) {
            System.out.println("-- Deu ruim --");
            e.printStackTrace();
        }
        return view;
    }
}
