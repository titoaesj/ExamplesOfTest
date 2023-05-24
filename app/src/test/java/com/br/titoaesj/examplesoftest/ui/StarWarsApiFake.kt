package com.br.titoaesj.examplesoftest.ui

import com.br.titoaesj.examplesoftest.data.model.Jedi
import com.br.titoaesj.examplesoftest.data.model.JediResponse
import com.br.titoaesj.examplesoftest.data.repository.JediRepository
import retrofit2.Response

class JediRepositoryFake : JediRepository {

    private val jediResponseNext = "http://next" // Dummy
    private val jediResponsePrevious = "http://previous" // Dummy
    private val jediResponseResult = listOf(Jedi(name = "Teste")) // Dummy
    private val jediResponseCount = 1 // Dummy

    override suspend fun getAllCharacter(): Response<JediResponse> = Response.success(
        JediResponse(
            next = jediResponseNext,
            previous = jediResponsePrevious,
            results = jediResponseResult,
            count = jediResponseCount
        )
    )

}