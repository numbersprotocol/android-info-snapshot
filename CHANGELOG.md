# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## 0.4.1 - 2019-12-02

__NOTE__: This is an internal release for testing.

### Fixed

- The package names of the sample app and library are different now.

## 0.4.0 - 2019-09-27

__NOTE__: This is an internal release for testing.

### Added

- Add configuration to specify which information to collect.

## 0.3.1 - 2019-09-26

__NOTE__: This is an internal release for testing.

### Fixed

- GPS provider can gather information on background threads or coroutines.

## 0.3.0 - 2019-09-26

__NOTE__: This is an internal release for testing.

### Added

- Gather locale information.
- Gather global settings information.
- Gather the hash (SHA-256) of Android ID.

## 0.2.0 - 2019-09-25

__NOTE__: This is an internal release for testing.

### Added

- Gather device information from `android.os.Build`.
- Gather location information from Google Play Service with fused GPS provider.
- Gather sensor information from `SensorManager`.

## 0.1.0 - 2019-09-25

__NOTE__: This is an internal release for testing.

This version does NOT support JitPack for Gradle dependency.