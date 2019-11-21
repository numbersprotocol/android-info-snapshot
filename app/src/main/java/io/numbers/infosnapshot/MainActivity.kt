package io.numbers.infosnapshot

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        snap.setOnClickListener {
            val snapshotBuilder = InfoSnapshotBuilder()
            Log.i(TAG, "${snapshotBuilder.snap()}")
        }
    }
}
