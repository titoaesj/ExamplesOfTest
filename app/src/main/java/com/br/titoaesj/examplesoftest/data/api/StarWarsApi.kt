package com.br.titoaesj.examplesoftest.data.api

import com.br.titoaesj.examplesoftest.data.model.JediResponse
import retrofit2.Response
import retrofit2.http.GET

interface StarWarsApi {

    @GET("people/")
    suspend fun allCharacter() : Response<JediResponse>

}