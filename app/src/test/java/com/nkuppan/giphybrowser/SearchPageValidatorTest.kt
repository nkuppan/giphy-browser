package com.nkuppan.giphybrowser

import com.nkuppan.giphybrowser.browser.utils.isValidQueryString
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

class SearchPageValidatorTest {

    @Test
    fun `Sending null query`() {
        assertFalse(null.isValidQueryString())
    }

    @Test
    fun `Sending empty query`() {
        assertFalse(" ".isValidQueryString())
    }

    @Test
    fun `Sending less than 3 character length query string`() {
        assertFalse("a".isValidQueryString())
    }

    @Test
    fun `Sending less than 3 character length along with empty space query string`() {
        assertFalse("a  ".isValidQueryString())
    }

    @Test
    fun `Sending a valid query string`() {
        assertTrue("Android".isValidQueryString())
    }
}
