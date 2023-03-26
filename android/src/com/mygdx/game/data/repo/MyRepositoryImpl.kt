package com.mygdx.game.data.repo

import com.mygdx.game.data.remote.Api
import com.mygdx.game.domain.repository.MyRepository
import retrofit2.Call

class MyRepositoryImpl(private val api : Api) : MyRepository {
    override fun getResponse(): Call<String> {
        return api.retrofitService.getResponse();
    }
}