package com.mygdx.game.domain.repository

import retrofit2.Call

interface MyRepository {
    fun getResponse() : Call<String>
}