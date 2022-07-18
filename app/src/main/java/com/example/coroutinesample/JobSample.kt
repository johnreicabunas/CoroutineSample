package com.example.coroutinesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class JobSample : AppCompatActivity() {

    val TAG = "JobSample"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_sample)

        val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Starting long running calculations")

            /*for(i in 30..50) {
                Log.d(TAG, "Result for i = $i: ${fib(i)}")
            }*/

            //use this to present timeout -- ps remove runBlocking
            withTimeout(3000L) {
                for(i in 30..100) {
                    if(isActive) {
                        Log.d(TAG, "Result for i = $i: ${fib(i)}")
                    }
                }
            }

            //to display that the job has been cancelled
            /*for(i in 30..50) {
                if(isActive) {
                    Log.d(TAG, "Result for i = $i: ${fib(i)}")
                }
            }*/
            Log.d(TAG, "Ending long running calculations")
        }


        runBlocking {
            delay(3000L)
            job.cancel()
            Log.d(TAG, "Coroutine cancelled")
            Log.d(TAG, "Main thread continues...")
        }
    }

    fun fib(n: Int) : Long {
        return if(n == 0) 0
        else if (n == 1) 1
        else fib(n-1) + fib(n -2)
    }
}