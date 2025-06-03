package br.com.example.meuprimeiroexemplo.adapters.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.model.People // Certifique-se que o import do modelo People está correto

class PeopleRecyclerAdapter(
    private val context: Context, private val peopleList: MutableList<People>
) : RecyclerView.Adapter<PeopleRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.people_item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < peopleList.size) {
            val person = peopleList[position]
            holder.bind(person)
        }
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtItemName: TextView = itemView.findViewById(R.id.textName)
        private val txtItemHeight: TextView = itemView.findViewById(R.id.textHeight)
        private val txtItemMass: TextView = itemView.findViewById(R.id.textMass)
        private val txtItemHairColor: TextView = itemView.findViewById(R.id.textHairColor)
        private val txtItemSkinColor: TextView = itemView.findViewById(R.id.textSkinColor)
        private val txtItemEyeColor: TextView = itemView.findViewById(R.id.textEyeColor)
        private val txtItemBirthYear: TextView = itemView.findViewById(R.id.textBirthYear)
        private val txtItemGender: TextView = itemView.findViewById(R.id.textGender)
        private val txtItemHomeWorld: TextView = itemView.findViewById(R.id.textHomeWorld)
        private val txtItemFilms: TextView = itemView.findViewById(R.id.textFilms)
        private val txtItemSpecies: TextView = itemView.findViewById(R.id.textSpecies)
        private val txtItemVehicles: TextView = itemView.findViewById(R.id.textVehicles)
        private val txtItemStarShips: TextView = itemView.findViewById(R.id.textStarShips)
        private val txtItemCreated: TextView = itemView.findViewById(R.id.textCreated)
        private val txtItemEdited: TextView = itemView.findViewById(R.id.textEdited)
        private val txtItemUrl: TextView = itemView.findViewById(R.id.textUrl)

        fun bind(person: People) {
            try {
                txtItemName.text = person.name
                txtItemHeight.text = person.height
                txtItemMass.text = person.mass
                txtItemHairColor.text = person.hair_color
                txtItemSkinColor.text = person.skin_color
                txtItemEyeColor.text = person.eye_color
                txtItemBirthYear.text = person.birth_year
                txtItemGender.text = person.gender
                txtItemHomeWorld.text = person.homeworld
                txtItemFilms.text = person.films.size.toString()
                txtItemSpecies.text = person.species.size.toString()
                txtItemVehicles.text = person.vehicles.size.toString()
                txtItemStarShips.text = person.starships.size.toString()
                txtItemCreated.text = person.created
                txtItemEdited.text = person.edited
                txtItemUrl.text = person.url

                // Log
                Log.i("PeopleAdapterVH", "Binding data for: ${person.name}")

            } catch (e: Exception) {
                Log.e("PeopleAdapterVH", "Error binding data for ${person.name}: ${e.message}", e)
                Toast.makeText(context, "Erro ao exibir dados: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Adiciona uma nova lista de pessoas aos dados existentes e notifica o adapter.
     * Este método será chamado pela Activity quando novos dados de paginação forem carregados.
     */
    fun addData(newPeople: List<People>) {
        val previousSize = peopleList.size
        peopleList.addAll(newPeople)
        notifyItemRangeInserted(previousSize, newPeople.size)
    }

    /**
     * Limpa todos os dados do adapter e notifica.
     * Útil para uma nova busca ou "pull-to-refresh".
     */
    fun clearData() {
        val previousSize = peopleList.size
        peopleList.clear()
        notifyItemRangeRemoved(0, previousSize) // Ou notifyDataSetChanged() se preferir uma notificação global
    }
}