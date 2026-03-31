# FieldTrack
This is for my dad, my favourite stakeholder, he wanted me to build him an app because he is always forgetting when to reapply bug or fungal treatment to his farm. So here is, a simple app to track product treatments in different zones in your land

## Features
- Create and manage log entries
- Local persistence using Room
- Reactive UI built with Jetpack Compose
- State-driven UI updates using Kotlin Flow
- Dependency injection with Hilt

## Tech (planned / in progress)

- Kotlin
- Jetpack Compose
- Coroutines & Flow (StateFlow)
- Room Database
- Hilt (Dependency Injection)
- MVVM Architecture
- Mockk (Testing)

## Architecture

The app follows a layered architecture with a clear separation of concerns:

- UI Layer: Built with Jetpack Compose, responsible for rendering state
- ViewModel Layer: Manages UI state and user interactions using StateFlow
- Data Layer: Handles data persistence and retrieval via Room and a repository abstraction

The ViewModel exposes a single source of truth for the UI through immutable state, enabling predictable and reactive updates.

The current implementation keeps the architecture relatively simple for clarity, but it is designed to evolve as complexity grows.

## Future Improvements

To further evolve the architecture and make it more production-ready, the following improvements could be introduced:

- Introduce a domain layer (use cases) to better encapsulate business logic
- Implement a more robust offline-first strategy with data synchronization
- Refactor into a multi-module architecture for better scalability

## Goals of the project

- Provide a simple interface for tracking treatments across farm zones
- Explore modern Android development practices
- Build a practical tool for a real user
