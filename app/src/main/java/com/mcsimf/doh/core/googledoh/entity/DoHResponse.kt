package com.mcsimf.doh.core.googledoh.entity

import com.google.gson.GsonBuilder


/**
 * @author Maksym Fedyay on 11/26/20 (mcsimf@gmail.com).
 */
data class DoHResponse(
    val Status: String,
    val TC: Boolean,
    val RD: Boolean,
    val RA: Boolean,
    val AD: Boolean,
    val CD: Boolean,
    val Question: List<Question>,
    val Answer: List<Answer>,
    val Additional: List<Additional>,
    val edns_client_subnet: String
) {
    override fun toString(): String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(this)//super.toString()
    }
}

data class Question(val name: String, val type: Int)

data class Answer(val name: String, val type: Int, val TTL: Long, val data: String)

class Additional

