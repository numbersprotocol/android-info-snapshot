package io.numbers.infosnapshot

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.json.JSONObject
import timber.log.Timber

private const val REQUEST_PERMISSIONS_REQUEST_CODE = 1

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkPermissions()) {
            snapButton.isEnabled = true
        } else requestPermissions()

        snapButton.setOnClickListener {
            val snapshotBuilder = InfoSnapshotBuilder(this).apply {
                duration = 2000
            }
            job = launch(Dispatchers.IO) {
                try {
                    val snapshot = snapshotBuilder.snap()
                    Timber.i("$snapshot")
                    Timber.i(JSONObject(snapshot.toJson()).toString(2))
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }

        cancelButton.setOnClickListener { job?.cancel() }
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
            Timber.i("Displaying permission rationale to provide additional context.")
        } else {
            Timber.i("Requesting permission")
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
        Timber.i("onRequestPermissionResult")

        if (requestCode != REQUEST_PERMISSIONS_REQUEST_CODE) return

        when {
            grantResults.isEmpty() -> Timber.i("User interaction was cancelled.")
            grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                Timber.i("Permissions granted.")
                snapButton.isEnabled = true
            }
            else -> Timber.e("Permissions denied.")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
