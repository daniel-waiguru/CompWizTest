/*
 * MIT License
 *
 * Copyright (c) 2021 Daniel Waiguru
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.compwiz.countrylister.presentation.views.list

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import io.compwiz.countrylister.R
import io.compwiz.countrylister.data.dummyData
import io.compwiz.countrylister.domain.repository.CountryRepository
import io.compwiz.countrylister.domain.use_case.FetchCountriesUseCase
import io.compwiz.countrylister.presentation.viewmodels.CountryListViewModel
import io.compwiz.countrylister.presentation.views.adapter.CountryViewHolder
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.mock.declare

@LargeTest
@RunWith(AndroidJUnit4::class)
class CountryListFragmentTest: KoinTest {
    private val countryRepository = mockk<CountryRepository>(relaxUnitFun = true)
    private lateinit var useCase: FetchCountriesUseCase

    @Before
    fun setup() {
        useCase = FetchCountriesUseCase(countryRepository)
    }
    @Test
    fun shouldRenderCorrectNameAndAlphaCode() {
        coEvery {
            countryRepository.getAllCountries()
        } returns flowOf(dummyData)
        declare {
            CountryListViewModel(useCase)
        }
        launchFragmentInContainer<CountryListFragment>()
        with(onView(withId(R.id.countryRv))) {
            perform(RecyclerViewActions.scrollToPosition<CountryViewHolder>(0))
            onView(withText("Afghanistan")).check(matches(isDisplayed()))
        }
        /*screen {
            recycler {
                scrollTo(0)
                childAt<Item>(0) {
                    name.hasText("Afghanistan")
                    alphaCode.hasText("AF")
                }
            }
        }*/
    }
    private fun childCountIs(expectedChildCount: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView with item count $expectedChildCount")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                return item?.adapter?.itemCount == expectedChildCount
            }
        }
    }
}

/*
class Item(parent: Matcher<View>): KRecyclerItem<Item>(parent) {
    val name = KTextView(parent) { withId(R.id.countryName)}
    val alphaCode = KTextView(parent) { withId(R.id.alphaCode) }
}
class CountryListScreen: Screen<CountryListScreen>() {
    val recycler: KRecyclerView = KRecyclerView(
        { withId(R.id.countryRv) }, itemTypeBuilder = {
            itemType(::Item)
        }
    )
}*/
