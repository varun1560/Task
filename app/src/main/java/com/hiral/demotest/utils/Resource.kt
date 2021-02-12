package com.hiral.demotest.utils

data class Resource<T> private constructor(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> loading(data: T? = null) = Resource<T>(Status.LOADING, data, message=null)

        fun <T> error(data: T? = null,message:String?) = Resource<T>(Status.ERROR, data,message)

        fun <T> success(data: T? = null) = Resource<T>(Status.SUCCESS, data, message=null)
    }
}