# Android Information Snapshot

[![JitPack version](https://jitpack.io/v/numbersprotocol/android-info-snapshot.svg)](https://jitpack.io/#numbersprotocol/android-info-snapshot)

A simple library to capture information of an Android smartphone at a certain time.

## Usage

Create `InfoSnapshotBuilder` with `duration` setting.

``` kotlin
val snapshotBuilder = InfoSnapshotBuilder(this).apply {
    duration = 2000
}
```

You can specify which information to collect when creating `snapshotBuilder`. The default values of `enable*Info` are all `true`s.

``` kotlin
val snapshotBuilder = InfoSnapshotBuilder(this).apply {
    duration = 2000
    enableDeviceInfo = true
    enableLocaleInfo = true
    enableLocationInfo = true  // require [ACCESS_FINE_LOCATION] permission
    enableSensorInfo = true
    enableSettingsInfo = true
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
    "value": {
      "board": "sdm845",
      "bootloader": "1.0.0.0000",
      "brand": "htc",
      "device": "htc_exodugl",
      "display": "OPR1.170623.032 release-keys",
      "fingerprint": "htc\/exodugl_00709\/htc_exodugl:8.0.0\/OPR1.170623.032\/1091479.2:user\/release-keys",
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
    }
  },
  "localeInfo": {
    "value": {
      "country": "United States",
      "variant": "",
      "language": "English",
      "script": "",
      "name": "English (United States)"
    }
  },
  "locationInfo": {
    "value": {
      "lastKnown": {
        "value": {
          "accuracy": 29.555,
          "altitude": 26.30000114440918,
          "bearing": 311.93915,
          "bearingAccuracyDegrees": {
            "value": 0
          },
          "latitude": 25.0360848,
          "longitude": 121.5579271,
          "provider": "fused",
          "speed": 0.25277704,
          "speedAccuracyMetersPerSecond": {
            "value": 0
          },
          "time": 1574835188000,
          "verticalAccuracyMeters": {
            "value": 2
          },
          "isFromMockProvider": false,
          "address": {
            "value": "No. 4, Alley 2, Lane 421, Guangfu South Road, Xinyi District, Taipei City, Taiwan 110"
          }
        }
      },
      "current": {
        "value": {
          "accuracy": 28.397,
          "altitude": 0,
          "bearing": 0,
          "bearingAccuracyDegrees": {
            "value": 0
          },
          "latitude": 25.0360477,
          "longitude": 121.5578969,
          "provider": "fused",
          "speed": 0,
          "speedAccuracyMetersPerSecond": {
            "value": 0
          },
          "time": 1574839387381,
          "verticalAccuracyMeters": {
            "value": 0
          },
          "isFromMockProvider": false,
          "address": {
            "value": "No. 429-1, Guangfu South Road, Xinyi District, Taipei City, Taiwan 110"
          }
        }
      }
    }
  },
  "sensorInfo": {
    "value": {
      "accelerometer": {
        "value": {
          "accuracy": {
            "value": 3
          },
          "value": [
            0.07664102,
            0.019160256,
            9.869992
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
            "value": 3
          },
          "value": [
            -6.35922E-4,
            -0.0055800546,
            -0.011479687,
            0.9999163
          ]
        }
      },
      "geomagneticRotationVector": {
        "value": {
          "accuracy": {
            "value": 3
          },
          "value": [
            0.0042034322,
            -0.0044389903,
            0.790616,
            0.6122892,
            0
          ]
        }
      },
      "gravity": {
        "value": {
          "accuracy": {
            "value": 3
          },
          "value": [
            0.110811785,
            -0.011867724,
            9.80597
          ]
        }
      },
      "gyroscope": {
        "value": {
          "accuracy": {
            "value": 3
          },
          "value": [
            0.0050228126,
            0.0011342609,
            0.004654825
          ]
        }
      },
      "gyroscopeUncalibrated": {
        "value": {
          "accuracy": {
            "value": 3
          },
          "value": [
            -0.002434015,
            0.0032647562,
            -0.0012041484,
            0,
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
            57.494713,
            0,
            0
          ]
        }
      },
      "linearAcceleration": {
        "value": {
          "accuracy": {
            "value": 3
          },
          "value": [
            0.018869782,
            0.04401975,
            -0.0033977134
          ]
        }
      },
      "lowLatencyOffbodyDetect": {
        "nullReason": "UNSUPPORTED"
      },
      "magneticField": {
        "value": {
          "accuracy": {
            "value": 3
          },
          "value": [
            31.222242,
            -8.883545,
            -16.315708
          ]
        }
      },
      "magneticFieldUncalibrated": {
        "value": {
          "accuracy": {
            "value": 3
          },
          "value": [
            68.02247,
            -16.388193,
            39.97906,
            34.67578,
            -7.337952,
            57.103165
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
            "value": 3
          },
          "value": [
            9,
            9,
            0
          ]
        }
      },
      "relativeHumidity": {
        "nullReason": "UNSUPPORTED"
      },
      "rotationVector": {
        "value": {
          "accuracy": {
            "value": 3
          },
          "value": [
            0.0041284566,
            -0.004110455,
            0.79066783,
            0.612217,
            0
          ]
        }
      },
      "significantMotion": {
        "nullReason": "NO_UPDATE_RECEIVED_DURING_SNAP"
      },
      "stationaryDetect": {
        "nullReason": "UNSUPPORTED"
      },
      "stepCounter": {
        "nullReason": "NO_UPDATE_RECEIVED_DURING_SNAP"
      },
      "stepDetector": {
        "nullReason": "NO_UPDATE_RECEIVED_DURING_SNAP"
      }
    }
  },
  "settingsInfo": {
    "value": {
      "adbEnabled": {
        "value": "1"
      },
      "airplaneModeOn": {
        "value": "1"
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
        "value": "1"
      },
      "deviceName": {
        "value": "EXODUS 1"
      },
      "deviceProvisioned": {
        "value": "1"
      },
      "httpProxy": {
        "nullReason": "NOT_FOUND"
      },
      "modeRinger": {
        "value": "1"
      },
      "wifiOn": {
        "value": "2"
      },
      "androidIdHash": {
        "value": "2b9b17c05c6779ef55da84df30af7e38b42f206ed7258cb49d5168089f0e4c4a"
      }
    }
  }
}
```
