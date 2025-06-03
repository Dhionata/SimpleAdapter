package br.com.example.meuprimeiroexemplo.activity

import PeopleAPI
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.example.meuprimeiroexemplo.R
import br.com.example.meuprimeiroexemplo.adapters.recyclerview.PeopleRecyclerAdapter
import br.com.example.meuprimeiroexemplo.debug.DebugActivity
import br.com.example.meuprimeiroexemplo.model.DefaultModel
import br.com.example.meuprimeiroexemplo.model.People
import br.com.example.meuprimeiroexemplo.resource.PeopleResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleActivity : DebugActivity() {

    private lateinit var recyclerViewPeople: RecyclerView
    private lateinit var peopleAdapter: PeopleRecyclerAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var progressBar: ProgressBar

    private val peopleDataList = mutableListOf<People>()

    private var nextPageUrl: String? = null
    private var isLoading = false
    private var isInitialLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)

        recyclerViewPeople = findViewById(R.id.peopleList)
        progressBar = findViewById(R.id.peopleProgressBar)

        setupRecyclerView()

        if (!isLoading) {

            isInitialLoad = peopleDataList.isEmpty()
            if (isInitialLoad) {
                peopleAdapter.clearData()
                nextPageUrl = null
            }
            fetchPeopleData()
        }

        if (peopleDataList.isEmpty()) {
            fetchPeopleData()
        }
    }

    private fun setupRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        recyclerViewPeople.layoutManager = layoutManager

        peopleAdapter = PeopleRecyclerAdapter(applicationContext, peopleDataList)
        recyclerViewPeople.adapter = peopleAdapter

        recyclerViewPeople.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (!isLoading && nextPageUrl != null) {

                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 5 && firstVisibleItemPosition >= 0) {
                            isInitialLoad = false
                            fetchPeopleData()
                        }
                    }
                }
            }
        })
    }

    internal fun fetchPeopleData() {
        if (isLoading) return

        isLoading = true
        progressBar.visibility = View.VISIBLE

        val retrofit = PeopleAPI.client
        val peopleResource = retrofit.create(PeopleResource::class.java)

        val call: Call<DefaultModel> = if (isInitialLoad || nextPageUrl == null) {
            Log.d("PeopleActivity", "Fetching initial page.")
            peopleResource.get()
        } else {
            Log.d("PeopleActivity", "Fetching next page: $nextPageUrl")
            peopleResource.getNextPage(nextPageUrl ?: return)
        }

        call.enqueue(object : Callback<DefaultModel?> {
            override fun onResponse(call: Call<DefaultModel?>, response: Response<DefaultModel?>) {
                isLoading = false
                progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val defaultModel = response.body()
                    defaultModel?.results?.let { newPeople ->
                        if (newPeople.isNotEmpty()) {
                            peopleAdapter.addData(newPeople)
                            Log.d("PeopleActivity", "Added ${newPeople.size} new items. Total: ${peopleDataList.size}")
                        } else if (isInitialLoad) {
                            Toast.makeText(applicationContext, "Nenhum personagem encontrado.", Toast.LENGTH_SHORT).show()
                        }
                        nextPageUrl = defaultModel.next

                        if (nextPageUrl == null && !isInitialLoad) {
                            Toast.makeText(applicationContext, "Todos os personagens carregados.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    isInitialLoad = false
                } else {
                    Toast.makeText(applicationContext, "Erro na resposta: ${response.code()} - ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("PeopleActivity", "Erro na resposta: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DefaultModel?>, throwable: Throwable) {
                isLoading = false
                progressBar.visibility = View.GONE

                if (isInitialLoad || nextPageUrl == null) {
                    isInitialLoad = true
                }

                Toast.makeText(applicationContext, "Falha na requisição: ${throwable.message}", Toast.LENGTH_LONG).show()
                Log.e("PeopleActivity", "Falha na requisição: ${throwable.message}", throwable)
            }
        })
    }
}