package io.numbers.infosnapshotsample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.numbers.infosnapshot.model.Snapshot
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber

private const val REQUEST_PERMISSIONS_REQUEST_CODE = 1

class MainActivity : AppCompatActivity() {

    private var job: Job? = null
    private var dataClassText: String? = null
    private var json: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkPermissions()) {
            snapButton.isEnabled = true
        } else requestPermissions()

        snapButton.setOnClickListener { snap() }

        cancelButton.setOnClickListener {
            job?.cancel()
            progressBar.isIndeterminate = false
        }
    }

    private fun snap() {
        val snapshotBuilder = Snapshot.Builder(this).apply {
            duration = 5000
        }
        progressBar.isIndeterminate = true
        job = lifecycleScope.launch(Dispatchers.IO) {
            try {
                val snapshot = snapshotBuilder.snap()
                dataClassText = "$snapshot"
                Timber.i("$snapshot")
                json = JSONObject(snapshot.toJson()).toString(2)
                Timber.i(json)
            } catch (e: Exception) {
                Timber.e(e)
                dataClassText = e.toString()
                json = e.toString()
            }

            withContext(Dispatchers.Main) {
                dataClassTextView.text = dataClassText
                jsonTextView.text = json
                progressBar.isIndeterminate = false
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
}
