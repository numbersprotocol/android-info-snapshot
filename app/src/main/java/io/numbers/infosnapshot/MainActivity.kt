package io.numbers.infosnapshot

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.numbers.infosnapshot.factories.LocationInfoFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

const val TAG = "InfoSnapshotExampleApp"
private const val REQUEST_PERMISSIONS_REQUEST_CODE = 1

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkPermissions()) {
            snapButton.isEnabled = true
            debugButton.isEnabled = true
        } else requestPermissions()

        snapButton.setOnClickListener {
            val snapshotBuilder = InfoSnapshotBuilder(this).apply {
                duration = 2000
            }
            job = launch {
                try {
                    val snapshot = snapshotBuilder.snap()
                    Log.i(TAG, snapshot.toJson())
                    Log.i(TAG, "$snapshot")
                } catch (e: Exception) {
                    Log.e(TAG, Log.getStackTraceString(e))
                }
            }
        }

        cancelButton.setOnClickListener { job?.cancel() }

        debugButton.setOnClickListener {
            launch {
                val locationInfo = LocationInfoFactory.newLocationInfo(this@MainActivity, 10000)
                Log.i(TAG, "$locationInfo")
            }
        }
    }

    private fun checkPermissions(): Boolean {
        val permissionState = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        val shouldProvideRationale = shouldShowRequestPermissionRationale(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.")
        } else {
            Log.i(TAG, "Requesting permission")
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.i(TAG, "onRequestPermissionResult")

        if (requestCode != REQUEST_PERMISSIONS_REQUEST_CODE) return

        when {
            grantResults.isEmpty() -> Log.i(TAG, "User interaction was cancelled.")
            grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                Log.i(TAG, "Permissions granted.")
                snapButton.isEnabled = true
                debugButton.isEnabled = true
            }
            else -> Log.e(TAG, "Permissions denied.")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
