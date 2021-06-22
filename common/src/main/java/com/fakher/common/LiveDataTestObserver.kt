package com.fakher.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Helper class for testing LiveData
 */
class LiveDataTestObserver<T : Any?> : Observer<T> {
    private val values: MutableList<T> = mutableListOf()

    override fun onChanged(t: T) {
        values.add(t)
    }

    fun assertHasAnyValue() {
        if (values.isEmpty()) {
            throw fail("LiveDataTestObserver never received any value")
        }
    }

    fun assertValue(expected: T) {
        assertHasAnyValue()
        assertValueCount(1)
        assertLastValue(expected)
    }

    fun assertLastValue(expected: T) {
        assertHasAnyValue()
        val received: T = values.last()
        if (received != expected) {
            throw fail("Expected $expected, but received $received")
        }
    }

    fun assertValueCount(count: Int) {
        if (values.size != count) {
            throw fail("Expected $count values, but received ${values.size}")
        }
    }

    private fun fail(errorMessage: String): AssertionError = AssertionError(errorMessage)
}

fun <T : Any?> LiveData<T>.test() = LiveDataTestObserver<T>().also {
    observeForever(it)
}
