# Application Scope

ComicApp is a user-friendly platform for comic enthusiasts. It offers the following features:

- **Browse and Search Comics**: Users can explore a vast collection of comics based on various criteria, making it easy to discover new titles.
  
- **Read Comics**: Users can select comics from the available list and read them directly within the app, providing a seamless reading experience.

The app is developed following the Clean Architecture, Modularization, and MVVM design principles, ensuring a well-structured and maintainable codebase.

# Tech-Stack

The project utilizes a robust tech-stack comprised of various libraries and tools commonly used in modern Android development. Here are the key technologies employed:

- **Retrofit**: Networking library for handling API requests.
- **Coroutine**: Facilitates background operations and asynchronous programming.
- **Koin**: Dependency injection framework for managing dependencies.
- **Coil**: Image loading library for displaying images efficiently.
- **Room**: Provides offline caching and data persistence.
- **LiveData**: Part of Android Architecture Components for observing data changes.
  
# Architecture

The application follows the Clean Architecture principles, promoting modularity, scalability, and maintainability. It is structured using a modular architecture with MVVM (Model-View-ViewModel) pattern. Here's an overview of the architecture:

- **Presentation Layer**: Implements MVVM pattern. Activities/ Fragments act as views, ViewModel manages UI-related data.
- **Domain Layer**: Contains business logic and domain models independent of other layers.
- **Data Layer**: Encapsulates data access and storage, utilizing repositories to interact with external data sources such as APIs and databases.

# Dependency Management

Dependency management is centralized using Gradle versions catalog. This ensures consistency across modules and facilitates easier dependency updates. Dependencies are shared between modules, reducing the need for redundant declarations.

