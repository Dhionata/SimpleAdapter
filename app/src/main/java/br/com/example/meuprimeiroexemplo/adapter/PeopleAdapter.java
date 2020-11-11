package br.com.example.meuprimeiroexemplo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.model.People;

public class PeopleAdapter extends BaseAdapter {

    private final Context context;
    private final List<People> people;

    public PeopleAdapter(Context context, List<People> people) {
        this.context = context;
        this.people = people;
    }

    @Override
    public int getCount() {
        return this.people != null ? this.people.size() : 0;
    }

    @Override
    public People getItem(int i) {
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
            view = LayoutInflater.from(context).inflate(R.layout.people_item_post,
                    viewGroup, false);
        }

        //Procura o item dentro da lista para ser 'exibido' na listView
        People people = getItem(posicao);

        //Criar a referência de atributos/objeto java para ser customizar uma listView
        TextView txtItemName, txtItemHeight, txtItemMass, txtItemHairColor,
                txtItemSkinColor, txtItemEyeColor, txtItemBirthYear,
                txtItemGender, txtItemHomeWorld, txtItemFilms, txtItemSpecies,
                txtItemVehicles, txtItemStarShips, txtItemCreated, txtItemEdited, txtItemUrl;
        try {
            txtItemName = view.findViewById(R.id.textName);
            txtItemHeight = view.findViewById(R.id.textHeight);
            txtItemMass = view.findViewById(R.id.textMass);
            txtItemHairColor = view.findViewById(R.id.textHairColor);
            txtItemSkinColor = view.findViewById(R.id.textSkinColor);
            txtItemEyeColor = view.findViewById(R.id.textEyeColor);
            txtItemBirthYear = view.findViewById(R.id.textBirthYear);
            txtItemGender = view.findViewById(R.id.textGender);
            txtItemHomeWorld = view.findViewById(R.id.textHomeWorld);
            txtItemFilms = view.findViewById(R.id.textFilms);
            txtItemSpecies = view.findViewById(R.id.textSpecies);
            txtItemVehicles = view.findViewById(R.id.textVehicles);
            txtItemStarShips = view.findViewById(R.id.textStarShips);
            txtItemCreated = view.findViewById(R.id.textCreated);
            txtItemEdited = view.findViewById(R.id.textEdited);
            txtItemUrl = view.findViewById(R.id.textUrl);

            txtItemName.setText(people.getName());
            txtItemHeight.setText(people.getHeight());
            txtItemMass.setText(people.getMass());
            txtItemHairColor.setText(people.getHair_color());
            txtItemSkinColor.setText(people.getSkin_color());
            txtItemEyeColor.setText(people.getEye_color());
            txtItemBirthYear.setText(people.getBirth_year());
            txtItemGender.setText(people.getGender());
            txtItemHomeWorld.setText(people.getHomeworld());
            txtItemFilms.setText(String.valueOf(people.getFilms().size()));
            txtItemSpecies.setText(String.valueOf(people.getSpecies().size()));
            txtItemVehicles.setText(String.valueOf(people.getVehicles().size()));
            txtItemStarShips.setText(String.valueOf(people.getStarships().size()));
            txtItemCreated.setText(people.getCreated());
            txtItemEdited.setText(people.getEdited());
            txtItemUrl.setText(people.getUrl());

        } catch (Exception e) {
            Log.e("Vixi...", "Deu ruim...." + e.getMessage());
            Toast.makeText(context, "--Erro--\n" + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        return view;
    }
}
