package com.example.coroutinesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class AsyncSample : AppCompatActivity() {

    val TAG = "AsyncSample"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_sample)

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val answer1 = async { networkCall1() }
                val answer2 = async { networkCall2() }

                Log.d(TAG, "Answer1 : ${answer1.await()}")
                Log.d(TAG, "Answer2 : ${answer2.await()}")
            }
            Log.d(TAG, "Time to execute code = $time")
        }

        GlobalScope.launch {

        }
    }

    suspend fun networkCall1(): String {
        delay(3000L)

        return "Answer 1"
    }

    suspend fun networkCall2(): String {
        delay(3000L)

        return "Answer 2"
    }

    fun forNotes() {
        //The Deferred type is similar to CompletableFuture in the sense that it represents a value
        // that is not yet available but probably will be in the future (if no error occurs/exception is thrown)
        // from the asynchronous task. Deferred value is a non-blocking cancelable future that extends Job .
    }
}