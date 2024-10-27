package com.example.workmanagerexample

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MyWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        withContext(Dispatchers.Main) {
            val toast = Toast.makeText(applicationContext, "Started Work", Toast.LENGTH_SHORT)
            toast.show()
        }

        //Emulate data required for the task.
        val veryBigData = ByteArray(10 * 1024 ) // Allocate 1 MB of memory

        // Emulate 10 second work
        delay(10000)

        val resultData = Data.Builder().putByte("workResult", veryBigData[10]).build()

        withContext(Dispatchers.Main) {
            val toast = Toast.makeText(applicationContext, "Done some Work", Toast.LENGTH_SHORT)
            toast.show()
        }

        return Result.success(resultData)
    }
}