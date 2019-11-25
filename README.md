# Android Information Snapshot

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
  implementation "com.github.numbersprotocol:android-info-snapshot:0.2.0"
}
```

## Snapshot Example

``` json
{
  "deviceInfo": {
    "board": "sdm845",
    "bootloader": "1.0.0.0000",
    "brand": "htc",
    "device": "htc_exodugl",
    "display": "OPR1.170623.032 release-keys",
    "fingerprint": "htc/exodugl_00709/htc_exodugl:8.0.0/OPR1.170623.032/1091479.2:user/release-keys",
    "hardware": "htc_exo",
    "host": "ABM124",
    "id": "OPR1.170623.032",
    "manufacturer": "HTC",
    "model": "EXODUS 1",
    "product": "exodugl_00709",
    "supportedAbis": [
      "arm64-v8a",
      "armeabi-v7a",
      "armeabi"
    ],
    "tags": "release-keys",
    "buildTime": 1568020752000,
    "type": "user",
    "user": "buildteam"
  },
  "locationInfo": {
    "lastKnown": {
      "value": {
        "accuracy": 24.059,
        "altitude": 42.79999923706055,
        "bearing": 0.0,
        "bearingAccuracyDegrees": {
          "value": 0.0
        },
        "latitude": 25.0723367,
        "longitude": 121.6090836,
        "provider": "fused",
        "speed": 0.0,
        "speedAccuracyMetersPerSecond": {
          "value": 0.0
        },
        "time": 1574675417145,
        "verticalAccuracyMeters": {
          "value": 2.0
        },
        "isFromMockProvider": false,
        "address": {
          "value": "332 Corona Ave. Owings Mills, MD 21117"
        }
      }
    },
    "current": {
      "value": {
        "accuracy": 27.423,
        "altitude": 42.89999771118164,
        "bearing": 0.0,
        "bearingAccuracyDegrees": {
          "value": 0.0
        },
        "latitude": 25.0723299,
        "longitude": 121.6090929,
        "provider": "fused",
        "speed": 0.0,
        "speedAccuracyMetersPerSecond": {
          "value": 0.0
        },
        "time": 1574675438665,
        "verticalAccuracyMeters": {
          "value": 2.0
        },
        "isFromMockProvider": false,
        "address": {
          "value": "332 Corona Ave. Owings Mills, MD 21117"
        }
      }
    }
  },
  "sensorInfo": {
    "accelerometer": {
      "value": {
        "accuracy": {
          "value": 3.0
        },
        "value": [
          -0.062270835,
          0.08622004,
          9.853057
        ]
      }
    },
    "accelerometerUncalibrated": {
      "nullReason": "UNSUPPORTED"
    },
    "ambientTemperature": {
      "nullReason": "UNSUPPORTED"
    },
    "gameRotationVector": {
      "value": {
        "accuracy": {
          "value": 3.0
        },
        "value": [
          0.0039436733,
          0.0055385535,
          -0.0063705374,
          0.9999588
        ]
      }
    },
    "geomagneticRotationVector": {
      "value": {
        "accuracy": {
          "value": 3.0
        },
        "value": [
          -0.0021281645,
          0.006246302,
          0.82789326,
          0.5608482,
          0.0
        ]
      }
    },
    "gravity": {
      "value": {
        "accuracy": {
          "value": 3.0
        },
        "value": [
          -0.10837596,
          0.07830476,
          9.805722
        ]
      }
    },
    "gyroscope": {
      "value": {
        "accuracy": {
          "value": 3.0
        },
        "value": [
          0.004490194,
          0.0032647678,
          -0.0012041504
        ]
      }
    },
    "gyroscopeUncalibrated": {
      "value": {
        "accuracy": {
          "value": 3.0
        },
        "value": [
          0.0034249069,
          0.0021995283,
          0.006785356,
          0.0,
          0.0,
          0.0
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
          "value": 3.0
        },
        "value": [
          117.60282,
          0.0,
          0.0
        ]
      }
    },
    "linearAcceleration": {
      "value": {
        "accuracy": {
          "value": 3.0
        },
        "value": [
          0.03047655,
          -0.03117207,
          0.0052615167
        ]
      }
    },
    "lowLatencyOffbodyDetect": {
      "nullReason": "UNSUPPORTED"
    },
    "magneticField": {
      "value": {
        "accuracy": {
          "value": 3.0
        },
        "value": [
          16.547108,
          -5.8184834,
          -18.761345
        ]
      }
    },
    "magneticFieldUncalibrated": {
      "value": {
        "accuracy": {
          "value": 3.0
        },
        "value": [
          62.541004,
          -3.3567228,
          39.579853,
          45.993885,
          2.4617617,
          58.34127
        ]
      }
    },
    "motionDetect": {
      "nullReason": "UNSUPPORTED"
    },
    "pose6Dof": {
      "nullReason": "UNSUPPORTED"
    },
    "pressure": {
      "nullReason": "UNSUPPORTED"
    },
    "proximity": {
      "value": {
        "accuracy": {
          "value": 3.0
        },
        "value": [
          9.0,
          9.0,
          0.0
        ]
      }
    },
    "relativeHumidity": {
      "nullReason": "UNSUPPORTED"
    },
    "rotationVector": {
      "value": {
        "accuracy": {
          "value": 3.0
        },
        "value": [
          -0.0024333159,
          0.0063095144,
          0.8272192,
          0.56184274,
          0.0
        ]
      }
    },
    "significantMotion": {
      "nullReason": "NO_CHANGE_DURING_SNAP"
    },
    "stationaryDetect": {
      "nullReason": "UNSUPPORTED"
    },
    "stepCounter": {
      "nullReason": "NO_CHANGE_DURING_SNAP"
    },
    "stepDetector": {
      "nullReason": "NO_CHANGE_DURING_SNAP"
    }
  }
}
```
