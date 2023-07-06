package br.com.example.meuprimeiroexemplo.model

data class Comments(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)