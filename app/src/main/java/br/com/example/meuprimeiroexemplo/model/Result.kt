package br.com.example.meuprimeiroexemplo.model

data class Result(
    var name: String,
    var height: String,
    var mass: String,
    var hairColor: String,
    var skinColor: String,
    var eyeColor: String,
    var birthYear: String,
    var gender: String,
    var homeworld: String,
    var created: String,
    var edited: String,
    var url: String,
    var films: List<String> = ArrayList(),
    var species: List<String> = ArrayList(),
    var vehicles: List<String> = ArrayList(),
    var starships: List<String> = ArrayList()
)
