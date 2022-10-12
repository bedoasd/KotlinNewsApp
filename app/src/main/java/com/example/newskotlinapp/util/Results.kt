package com.example.newskotlinapp.util


class Result<out T>(val status: Status, val data: T?, message: String?) {

    companion object {
        fun <T> success(data : T?):Result<T>{
            return Result(Status.SUCCESS,data,null)
        }
        fun <T>loading(data :T?):Result<T>{
            return Result(Status.LOADING,null, message = null)
        }
        fun <T>error(data :T?):Result<T>{
            return Result(Status.ERROR,null, message = null)
        }
    }


    enum class Status{
        SUCCESS,
        LOADING,
        ERROR
    }
}