package com.mygdx.game.presentation.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mygdx.game.data.remote.Api
import com.mygdx.game.data.repo.MyRepositoryImpl
import com.mygdx.game.domain.usecases.GetResponseUseCase

class MyViewModel() : ViewModel() {

    private val myRepositoryImpl : MyRepositoryImpl by lazy { MyRepositoryImpl(Api) }
    private val getResponseUseCase : GetResponseUseCase by lazy { GetResponseUseCase(myRepositoryImpl) }

    private fun getResponse():Boolean{
        return getResponseUseCase.execute()
    }

    /**
     * Writes a boolean value to the shared preferences if the preferences are empty.
     * If the preferences already contain a value, that value is returned instead.
     *
     * @param prefs The shared preferences instance to write to.
     * @return A boolean value indicating whether data was written to the shared preferences or not.
     */
    fun writeToSharedPreference(prefs: SharedPreferences) : Boolean {
        val isEmpty = prefs.all.isEmpty()
        val data:Boolean
        if(isEmpty) {
            data = getResponse()
            if (data) {
                val editor = prefs.edit()
                editor.putBoolean("data", data)
                editor.apply()
            }
        }
        else
            data = prefs.getBoolean("data", false)
        return data;
    }

}