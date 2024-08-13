package com.dummy.jetrivia.network

data class DataOrException<Trivia,Boolean,exp : Exception>(
    var data : Trivia?=null,
    var loading : Boolean?=null,
    var exception : exp?=null
)
