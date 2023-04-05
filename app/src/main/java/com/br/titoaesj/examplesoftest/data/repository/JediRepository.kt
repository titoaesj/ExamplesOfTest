package com.br.titoaesj.examplesoftest.data.repository

import com.br.titoaesj.examplesoftest.data.api.StarWarsApi
import com.br.titoaesj.examplesoftest.data.model.JediResponse
import retrofit2.Response

/// Repository with Jedi scope
class JediRepository(
    private val starWarsApi: StarWarsApi
) {

    /**
     * Get all Jedi`s.
     */
    suspend fun getAllCharacter() : Response<JediResponse> = starWarsApi.allCharacter()

}