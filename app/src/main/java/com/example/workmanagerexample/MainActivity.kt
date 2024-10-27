package com.example.workmanagerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest

class MainActivity : AppCompatActivity() {
    private lateinit var startWorkButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startWorkButton = findViewById(R.id.startWorkButton)

        startWorkButton.setOnClickListener {

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()

            val myWorkRequest: OneTimeWorkRequest =
                OneTimeWorkRequestBuilder<MyWorker>()
                    .setConstraints(constraints)
                    .build()

            WorkManager.getInstance(this)
                .enqueueUniqueWork(WORK_NAME, ExistingWorkPolicy.KEEP, myWorkRequest)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        WorkManager.getInstance(this)
            .cancelUniqueWork(WORK_NAME)
    }

    companion object{
        const val WORK_NAME = "one work"
    }
}