# Android Information Snapshot

[![](https://jitpack.io/v/numbersprotocol/android-info-snapshot.svg)](https://jitpack.io/#numbersprotocol/android-info-snapshot)

A simple library to capture information of an Android smartphone at a certain time.

## Usage

Create `InfoSnapshotBuilder` with `duration` setting.

``` kotlin
val snapshotBuilder = InfoSnapshotBuilder(this).apply {
    duration = 2000
}
```

Launch a Kotlin coroutine to gather information.

``` kotlin
launch {
    val snapshot = snapshotBuilder.snap()

    Log.i(TAG, "$snapshot")  // Print out the snapshot directly.
    Log.i(TAG, snapshot.toJson()) // Serialize the snapshot to JSON format.
}
```

> Remember to give permissions (e.g. `Manifest.permission.ACCESS_FINE_LOCATION`) before calling `snap()` to avoid security exception.

You can see an Android example project [here](./app/src/main/java/io/numbers/infosnapshot/MainActivity.kt).

## Installation

Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories.

``` gradle
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```

Add the dependency.

``` gradle
dependencies {
  implementation "com.github.numbersprotocol:android-info-snapshot:[release-version]"
}
```

## Snapshot Example

``` json
{
  "deviceInfo": {
    "board": "unknown",
    "bootloader": "unknown",
    "brand": "google",
    "device": "generic_x86",
    "display": "NYC",
    "fingerprint": "google\/sdk_google_phone_x86\/generic_x86:7.0\/NYC\/4409132:user\/release-keys",
    "hardware": "ranchu",
    "host": "wphr6.hot.corp.google.com",
    "id": "NYC",
    "manufacturer": "Google",
    "model": "Android SDK built for x86",
    "product": "sdk_google_phone_x86",
    "supportedAbis": [
      "x86"
    ],
    "tags": "release-keys",
    "buildTime": 1508534163000,
    "type": "user",
    "user": "android-build"
  },
  "localeInfo": {
    "country": "United States",
    "variant": "",
    "language": "English",
    "script": "",
    "name": "English (United States)"
  },
  "locationInfo": {
    "lastKnown": {
      "value": {
        "accuracy": 20,
        "altitude": 0,
        "bearing": 90,
        "bearingAccuracyDegrees": {
          "nullReason": "ANDROID_SDK_TOO_OLD"
        },
        "latitude": 25.04718,
        "longitude": 121.5609533,
        "provider": "fused",
        "speed": 0,
        "speedAccuracyMetersPerSecond": {
          "nullReason": "ANDROID_SDK_TOO_OLD"
        },
        "time": 1574713372000,
        "verticalAccuracyMeters": {
          "nullReason": "ANDROID_SDK_TOO_OLD"
        },
        "isFromMockProvider": false,
        "address": {
          "value": "No. 4-1, Alley 8, Lane 106, Section 4, Bade Road, Songshan District, Taipei City, Taiwan 105"
        }
      }
    },
    "current": {
      "value": {
        "accuracy": 20,
        "altitude": 0,
        "bearing": 90,
        "bearingAccuracyDegrees": {
          "nullReason": "ANDROID_SDK_TOO_OLD"
        },
        "latitude": 25.04718,
        "longitude": 121.5609533,
        "provider": "fused",
        "speed": 0,
        "speedAccuracyMetersPerSecond": {
          "nullReason": "ANDROID_SDK_TOO_OLD"
        },
        "time": 1574713711000,
        "verticalAccuracyMeters": {
          "nullReason": "ANDROID_SDK_TOO_OLD"
        },
        "isFromMockProvider": false,
        "address": {
          "value": "No. 4-1, Alley 8, Lane 106, Section 4, Bade Road, Songshan District, Taipei City, Taiwan 105"
        }
      }
    }
  },
  "sensorInfo": {
    "accelerometer": {
      "value": {
        "accuracy": {
          "nullReason": "NO_UPDATE_RECEIVED_DURING_SNAP"
        },
        "value": [
          -6.79493E-6,
          7.645264,
          6.1470146
        ]
      }
    },
    "accelerometerUncalibrated": {
      "nullReason": "ANDROID_SDK_TOO_OLD"
    },
    "ambientTemperature": {
      "value": {
        "accuracy": {
          "value": 3
        },
        "value": [
          0,
          0,
          0
        ]
      }
    },
    "gameRotationVector": {
      "value": {
        "accuracy": {
          "value": 3
        },
        "value": [
          0.31441018,
          -0.29631326,
          -0.61855906,
          0.65630126
        ]
      }
    },
    "geomagneticRotationVector": {
      "value": {
        "accuracy": {
          "value": 3
        },
        "value": [
          0.42428592,
          0.081714615,
          0.17055161,
          0.88555914,
          0
        ]
      }
    },
    "gravity": {
      "value": {
        "accuracy": {
          "nullReason": "NO_UPDATE_RECEIVED_DURING_SNAP"
        },
        "value": [
          -2.022448E-4,
          7.641998,
          6.1457424
        ]
      }
    },
    "gyroscope": {
      "value": {
        "accuracy": {
          "nullReason": "NO_UPDATE_RECEIVED_DURING_SNAP"
        },
        "value": [
          0,
          0,
          0
        ]
      }
    },
    "gyroscopeUncalibrated": {
      "value": {
        "accuracy": {
          "value": 3
        },
        "value": [
          0,
          0,
          0,
          4.2E-45,
          0,
          0
        ]
      }
    },
    "heartBeat": {
      "nullReason": "UNSUPPORTED"
    },
    "heartRate": {
      "nullReason": "UNSUPPORTED"
    },
    "light": {
      "value": {
        "accuracy": {
          "value": 3
        },
        "value": [
          0,
          0,
          0
        ]
      }
    },
    "linearAcceleration": {
      "value": {
        "accuracy": {
          "nullReason": "NO_UPDATE_RECEIVED_DURING_SNAP"
        },
        "value": [
          1.9544976E-4,
          0.0032572746,
          0.0012874603
        ]
      }
    },
    "lowLatencyOffbodyDetect": {
      "nullReason": "ANDROID_SDK_TOO_OLD"
    },
    "magneticField": {
      "value": {
        "accuracy": {
          "value": 3
        },
        "value": [
          17.976254,
          32.75646,
          -31.324705
        ]
      }
    },
    "magneticFieldUncalibrated": {
      "nullReason": "NO_UPDATE_RECEIVED_DURING_SNAP"
    },
    "motionDetect": {
      "nullReason": "UNSUPPORTED"
    },
    "pose6Dof": {
      "nullReason": "UNSUPPORTED"
    },
    "pressure": {
      "value": {
        "accuracy": {
          "value": 3
        },
        "value": [
          1013.25,
          0,
          0
        ]
      }
    },
    "proximity": {
      "value": {
        "accuracy": {
          "value": 3
        },
        "value": [
          1,
          0,
          0
        ]
      }
    },
    "relativeHumidity": {
      "value": {
        "accuracy": {
          "value": 3
        },
        "value": [
          0,
          0,
          0
        ]
      }
    },
    "rotationVector": {
      "value": {
        "accuracy": {
          "value": 3
        },
        "value": [
          0.42428592,
          0.08171464,
          0.17055163,
          0.8855592,
          0
        ]
      }
    },
    "significantMotion": {
      "nullReason": "UNSUPPORTED"
    },
    "stationaryDetect": {
      "nullReason": "UNSUPPORTED"
    },
    "stepCounter": {
      "nullReason": "UNSUPPORTED"
    },
    "stepDetector": {
      "nullReason": "UNSUPPORTED"
    }
  },
  "settingsInfo": {
    "adbEnabled": {
      "value": "0"
    },
    "airplaneModeOn": {
      "value": "0"
    },
    "airplaneModeRadios": {
      "value": "cell,bluetooth,wifi,nfc,wimax"
    },
    "autoTime": {
      "value": "1"
    },
    "autoTimeZone": {
      "value": "1"
    },
    "bluetoothOn": {
      "value": "0"
    },
    "dataRoaming": {
      "value": "0"
    },
    "developmentSettingsEnabled": {
      "nullReason": "NOT_FOUND"
    },
    "deviceName": {
      "nullReason": "ANDROID_SDK_TOO_OLD"
    },
    "deviceProvisioned": {
      "value": "1"
    },
    "httpProxy": {
      "nullReason": "NOT_FOUND"
    },
    "modeRinger": {
      "value": "2"
    },
    "wifiOn": {
      "value": "1"
    },
    "androidIdHash": {
      "value": "8045bfaa2dc84358f5464ce7842a207756ddf350859edadf7ebbe7096495934a"
    }
  }
}
```
