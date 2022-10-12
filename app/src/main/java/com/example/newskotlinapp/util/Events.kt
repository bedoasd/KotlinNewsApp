package com.example.newskotlinapp.util
class Events<out T>(private val content: T) {


    var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (!hasBeenHandled) {
            hasBeenHandled = true
            content

        } else {
            null
        }
    }

    fun peekContent(): T = content
}
