package com.example.coroutinesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Simple way to start coroutine but not the best way
        //GlobalScope will run as long as our application does

        //Launch: Launch is a method in CoroutineScope which starts a coroutine without blocking
        //current thread and return a reference to the coroutine as a Job(which can be canceled to
        // stop the execution of that coroutine).

        GlobalScope.launch {
            delay(5000L)
            Log.d(TAG, "Coroutine thread ${Thread.currentThread().name}")

            /*val networkCall = doNetworkCall()
            val networkCall2 = doNetworkCall2()
            Log.d(TAG, "networkCall: $networkCall")
            Log.d(TAG, "networkCall2: $networkCall2")*/
        }

        Log.d(TAG, "Main Tread")

        //Coroutine Job
        /*val job = GlobalScope.launch(Dispatchers.Default) {
            repeat(5) {
                Log.d(TAG, "Coroutine is working...")
                delay(1000L)
            }
        }

        runBlocking {
            job.join()
            Log.d(TAG, "Main Thread is back here")
        }*/
    }

    private suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is doNetworkCall suspend function"
    }

    private suspend fun doNetworkCall2(): String {
        delay(3000L)
        return "This is doNetworkCall2 suspend function"
    }
}