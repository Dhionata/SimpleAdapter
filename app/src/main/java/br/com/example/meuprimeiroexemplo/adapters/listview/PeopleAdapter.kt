package br.com.example.meuprimeiroexemplo.adapters.listview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.model.People

class PeopleAdapter(private val context: Context, private val people: List<People>?) :
    BaseAdapter() {
    override fun getCount(): Int {
        return people?.size ?: 0
    }

    override fun getItem(i: Int): People {
        return people!![i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(posicao: Int, view: View?, viewGroup: ViewGroup): View {

        //Procura o item dentro da lista para ser 'exibido' na listView
        val people = getItem(posicao)

        var converterVisualizacao = view
        if (converterVisualizacao == null) {
            converterVisualizacao =
                LayoutInflater.from(context).inflate(R.layout.people_item_post, viewGroup, false)
        }
        //Criar a referência de atributos/objeto java para ser customizar uma listView
        if (converterVisualizacao != null) {
            try {
                val txtItemName: TextView = converterVisualizacao.findViewById(R.id.textName)
                val txtItemHeight: TextView = converterVisualizacao.findViewById(R.id.textHeight)
                val txtItemMass: TextView = converterVisualizacao.findViewById(R.id.textMass)
                val txtItemHairColor: TextView =
                    converterVisualizacao.findViewById(R.id.textHairColor)
                val txtItemSkinColor: TextView =
                    converterVisualizacao.findViewById(R.id.textSkinColor)
                val txtItemEyeColor: TextView =
                    converterVisualizacao.findViewById(R.id.textEyeColor)
                val txtItemBirthYear: TextView =
                    converterVisualizacao.findViewById(R.id.textBirthYear)
                val txtItemGender: TextView = converterVisualizacao.findViewById(R.id.textGender)
                val txtItemHomeWorld: TextView =
                    converterVisualizacao.findViewById(R.id.textHomeWorld)
                val txtItemFilms: TextView = converterVisualizacao.findViewById(R.id.textFilms)
                val txtItemSpecies: TextView = converterVisualizacao.findViewById(R.id.textSpecies)
                val txtItemVehicles: TextView =
                    converterVisualizacao.findViewById(R.id.textVehicles)
                val txtItemStarShips: TextView =
                    converterVisualizacao.findViewById(R.id.textStarShips)
                val txtItemCreated: TextView = converterVisualizacao.findViewById(R.id.textCreated)
                val txtItemEdited: TextView = converterVisualizacao.findViewById(R.id.textEdited)
                val txtItemUrl: TextView = converterVisualizacao.findViewById(R.id.textUrl)
                txtItemName.text = people.name
                txtItemHeight.text = people.height
                txtItemMass.text = people.mass
                txtItemHairColor.text = people.hair_color
                txtItemSkinColor.text = people.skin_color
                txtItemEyeColor.text = people.eye_color
                txtItemBirthYear.text = people.birth_year
                txtItemGender.text = people.gender
                txtItemHomeWorld.text = people.homeworld
                txtItemFilms.text = people.films.size.toString()
                txtItemSpecies.text = people.species.size.toString()
                txtItemVehicles.text = people.vehicles.size.toString()
                txtItemStarShips.text = people.starships.size.toString()
                txtItemCreated.text = people.created
                txtItemEdited.text = people.edited
                txtItemUrl.text = people.url

            } catch (e: Exception) {
                Log.e("Vixi...", "Deu ruim.... ${e.message}")
                Toast.makeText(context, "--Erro--${e.message}", Toast.LENGTH_LONG).show()
            }
        }
        return converterVisualizacao!!
    }
}