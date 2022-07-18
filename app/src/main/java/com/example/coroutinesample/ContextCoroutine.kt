package com.example.coroutinesample

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*

class ContextCoroutine : AppCompatActivity() {

    val TAG = "ContextCoroutine"
    private lateinit var tvCoroutineText: TextView
    private lateinit var tvCoroutineText1: TextView
    private lateinit var tvCoroutineText2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_coroutine)

        tvCoroutineText = findViewById(R.id.tv_coroutine)
        tvCoroutineText1 = findViewById(R.id.tv_coroutine1)
        tvCoroutineText2 = findViewById(R.id.tv_coroutine2)

        //Use for complex and long running calculations that could block the main thread
        //eg. need to store a list of 10000 elements
        GlobalScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Main) {
                val networkCall = doNetworkCall2()
                tvCoroutineText1.text = networkCall
            }
        }

        //Not confined to specific thread
        GlobalScope.launch(Dispatchers.Unconfined) {
            withContext(Dispatchers.Main) {
                val networkCall = doNetworkCall3()
                tvCoroutineText2.text = networkCall
            }
        }

        //Start coroutine in the main thread
        //useful in dong ui operation within your coroutine
        GlobalScope.launch(Dispatchers.Main) {
            Log.d(TAG, "I'm working in thread Main: ${Thread.currentThread().name}")
        }

        //Use for data operation such as networking, writing in database or read and writing to files
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "I'm working in thread IO: ${Thread.currentThread().name}")

            sampleCoroutineMain()
        }


        GlobalScope.launch(newSingleThreadContext("MyThread")) {
            Log.d(TAG, "I'm working in thread ${Thread.currentThread().name}")
        }
    }

    private suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is doNetworkCall suspend function"
    }

    private suspend fun doNetworkCall2(): String {
        delay(5000L)
        return "This is doNetworkCall2 suspend function"
    }

    private suspend fun doNetworkCall3(): String {
        delay(6000L)
        return "This is doNetworkCall3 suspend function"
    }

    private suspend fun sampleCoroutineMain() {
        withContext(Dispatchers.Main) {
            val networkCall = doNetworkCall()
            tvCoroutineText.text = networkCall
        }
    }

    suspend fun sampleCoroutineIO() {
        withContext(Dispatchers.IO) {

        }
    }

    suspend fun sampleCoroutineUnConfined() {
        withContext(Dispatchers.Unconfined) {

        }
    }

    suspend fun sampleCoroutineDefault() {
        withContext(Dispatchers.Default) {

        }
    }
}