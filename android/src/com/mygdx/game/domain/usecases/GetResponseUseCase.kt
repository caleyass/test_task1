package com.mygdx.game.domain.usecases

import com.mygdx.game.domain.repository.MyRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class GetResponseUseCase (private val myRepository: MyRepository) {
    /**

    Executes a network request to get a response and returns a boolean value based on the response.
    Uses a separate thread to avoid running in the main thread.
    */
    fun execute(): Boolean {
        var text: String = ""
        val exec: ExecutorService =
            Executors.newSingleThreadExecutor() // create new thread to not run in main thread
        exec.execute {
            text = myRepository.getResponse().execute().body()!!
        }
        exec.shutdown() // wait until code is executed because fact may be null
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)
        return text.toBoolean()
    }
}