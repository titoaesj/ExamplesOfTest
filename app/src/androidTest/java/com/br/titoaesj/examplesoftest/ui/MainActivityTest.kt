package com.br.titoaesj.examplesoftest.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.br.titoaesj.examplesoftest.R
import com.br.titoaesj.examplesoftest.ui.TestUtils.withRecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.AnyOf.anyOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun whenInitialState() {
        onView(withText("ExamplesOfTest")).check(matches(isDisplayed()))
    }

    @Test
    fun whenSuccessState() {

    }
}