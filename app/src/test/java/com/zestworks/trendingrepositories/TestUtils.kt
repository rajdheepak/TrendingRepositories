package com.zestworks.trendingrepositories

import org.mockito.ArgumentCaptor

fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

infix fun <T> T.shouldBe(any: Any?) {
    assert(any == this)
}
