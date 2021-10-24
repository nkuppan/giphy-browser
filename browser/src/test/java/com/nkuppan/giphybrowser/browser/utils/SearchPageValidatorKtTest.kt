package com.nkuppan.giphybrowser.browser.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SearchPageValidatorKtTest {

    @Test
    fun `Sending null query`() {
        assertThat(null.isValidQueryString()).isFalse()
    }

    @Test
    fun `Sending empty query`() {
        assertThat(" ".isValidQueryString()).isFalse()
    }

    @Test
    fun `Sending less than 3 character length query string`() {
        assertThat("a".isValidQueryString()).isFalse()
        assertThat("ab".isValidQueryString()).isFalse()
    }

    @Test
    fun `Sending less than 3 character length along with empty space query string`() {
        assertThat("a  ".isValidQueryString()).isFalse()
        assertThat("ab  ".isValidQueryString()).isFalse()
    }

    @Test
    fun `Sending a valid query string`() {
        assertThat("Android".isValidQueryString()).isTrue()
    }
}