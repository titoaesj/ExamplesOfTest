package com.br.titoaesj.examplesoftest.data.model

data class JediResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Jedi>? = null
)