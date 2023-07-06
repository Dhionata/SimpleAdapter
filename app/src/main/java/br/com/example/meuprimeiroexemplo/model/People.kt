package br.com.example.meuprimeiroexemplo.model

data class People(
    val id: Int,
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val `home-world`: String,
    val films: List<String> = ArrayList(),
    val species: List<String> = ArrayList(),
    val vehicles: List<String> = ArrayList(),
    val starships: List<String> = ArrayList(),
    val created: String,
    val edited: String,
    val url: String
)