package com.br.titoaesj.examplesoftest.common

import org.junit.Assert.assertEquals
import org.junit.Test

class ConstantsTest {

    private val baseUrl = "https://swapi.dev/api/" // Dummy

    @Test
    fun `when BASE_URL constant`() {

        //Then or Assert
        assertEquals(baseUrl, Constants.BASE_URL)
    }
}