package br.com.example.meuprimeiroexemplo.model

open class DefaultModel(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<People> = ArrayList()
)