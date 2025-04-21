# MVI_Class-Task_D2

This is a simple Android application built using the MVI (Model-View-Intent) architectural pattern with **Jetpack Compose**.

## Architecture

The app follows the MVI pattern:

- **Model**: Represents the state and data layer.
- **View**: Jetpack Compose UI that observes and renders the state.
- **Intent**: User actions that trigger state changes via a `ViewModel`.

The state is managed using `StateFlow` in `ViewModel` to ensure unidirectional data flow and reactive UI updates.

## Features

- Clean MVI structure using Kotlin and Jetpack Compose.
- State management with `StateFlow`.
- Intent handling through sealed classes.
- ViewModel acts as a bridge between the UI and data logic.
