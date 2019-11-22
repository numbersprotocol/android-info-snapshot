package io.numbers.infosnapshot

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

const val TAG = "InfoSnapshotExampleApp"

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        snapButton.setOnClickListener {
            val snapshotBuilder = InfoSnapshotBuilder(this).apply {
                duration = 5000
            }
            job = launch {
                try {
                    Log.i(TAG, "${snapshotBuilder.snap()}")
                } catch (e: Exception) {
                    Log.e(TAG, Log.getStackTraceString(e))
                }
            }
        }

        cancelButton.setOnClickListener { job.cancel() }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
