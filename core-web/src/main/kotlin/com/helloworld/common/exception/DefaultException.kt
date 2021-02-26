package com.helloworld.common.exception

interface DefaultException {
    val code: String
    val type: String
    val message: String
}