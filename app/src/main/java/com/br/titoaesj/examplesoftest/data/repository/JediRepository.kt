package com.br.titoaesj.examplesoftest.data.repository

import com.br.titoaesj.examplesoftest.data.model.JediResponse
import retrofit2.Response

/// Repository with Jedi scope
interface JediRepository {

    /**
     * Get all Jedi`s.
     */
    suspend fun getAllCharacter() : Response<JediResponse>

}