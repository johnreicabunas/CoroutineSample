package com.example.coroutinesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class RunBlocking : AppCompatActivity() {

    val TAG = "RunBlocking"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_blocking)

        /*Log.d(TAG, "Before runBlocking")

        //WIll block the main thread
        runBlocking {
            //job.join()
            //Log.d(TAG, "Main thread is back")

            Log.d(TAG, "Start runBlocking")
            //When using delay here, it will actually delay the main UI updates
            //unlike GlobalScope even we add delay in this scope we can still use the UI in the main thread
            //can  be useful you don't necessary need the coroutine behavior but still need to cause suspend function
            //in main thread
            delay(3000L)
            Log.d(TAG, "End runBlocking")

            //can also call another coroutine scope
            launch {
                delay(3000L)
                Log.d(TAG, "coroutine inside another coroutine")
            }
            launch {
                delay(3000L)
                Log.d(TAG, "coroutine inside another coroutine 2")
            }
        }

        Log.d(TAG, "After runBlocking")
        Thread.sleep(2000L)*/

        //Same here
        Log.d(TAG, "Before runBlocking")
        Log.d(TAG, "Start runBlocking")
        Thread.sleep(5000L)
        Log.d(TAG, "End runBlocking")
        Log.d(TAG, "After runBlocking")
    }

}