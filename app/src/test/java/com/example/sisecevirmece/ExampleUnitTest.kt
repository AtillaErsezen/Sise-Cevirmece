package com.example.sisecevirmece

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun dogrulukCekTest() {
       assertEquals(true,SoruCek().dogrulukCek()!=null)
    }
}