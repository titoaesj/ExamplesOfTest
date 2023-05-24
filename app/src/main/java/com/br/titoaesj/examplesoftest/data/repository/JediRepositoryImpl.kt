package com.br.titoaesj.examplesoftest.data.repository

import com.br.titoaesj.examplesoftest.data.api.StarWarsApi
import com.br.titoaesj.examplesoftest.data.model.JediResponse
import retrofit2.Response


class JediRepositoryImpl(
    private val starWarsApi: StarWarsApi
) : JediRepository {

    override suspend fun getAllCharacter() : Response<JediResponse> = starWarsApi.allCharacter()

}