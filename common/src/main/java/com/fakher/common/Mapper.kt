package com.fakher.common

interface Mapper<K, R> {

    fun to(input: K): R

    fun from(input: R): K
}