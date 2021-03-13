# Movie App

[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.4-blue.svg)](https://kotlinlang.org)

Showcase is a sample project that presents a modern, 2021 approach to [Android](https://en.wikipedia.org/wiki/Android_(operating_system)) application development.

The goal of the project is to combine popular libraries/tools and demonstrate best developement practices by utilizing up to date tech-stack and presenting modern Android application [Architecture](#architecture) that is modular, scalable, maintainable, and testable. This application may look simple,
but it has all the pieces that will provide the rock-solid foundation for the larger app suitable for bigger teams
and long [application lifecycle](https://en.wikipedia.org/wiki/Application_lifecycle_management).

## Project characteristics and tech-stack

<img src="misc/image/application_anim.gif" width="336" align="right" hspace="20">

This project takes advantage of best practices, many popular libraries and tools in the the Android ecosystem. Most of the libraries are in the stable version unless there is a good reason to use non-stable dependency.

* Tech-stack
    * [100% Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    * [Retrofit](https://square.github.io/retrofit/) - networking
    * [Jetpack](https://developer.android.com/jetpack)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notify views about database change
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
        * [Room](https://developer.android.com/jetpack/androidx/releases/room) - store offline cache
    * [Kodein](https://kodein.org/Kodein-DI/) - dependency injection
    * [Lottie](http://airbnb.io/lottie) - animation library
* Modern Architecture
    * Clean Architecture (at feature module level)
    * Single activity architecture ( with[Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started))
    * MVVM
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
    * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
* Testing
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit 5](https://junit.org/junit5/) via
    [android-junit5](https://github.com/mannodermaus/android-junit5))
    * [UT Tests](https://en.wikipedia.org/wiki/Graphical_user_interface_testing) ([Espresso](https://developer.android.com/training/testing/espresso))
    * [Mockk](https://mockk.io/) - mocking framework
    * [Kluent](https://github.com/MarkusAmshove/Kluent) - assertion framework
* UI
    * [Material design](https://material.io/design)
    * Reactive UI
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
    * Custom tasks
    * Plugins ([SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args),
    [android-junit5](https://github.com/mannodermaus/android-junit5))

## Architecture

Feature related code is placed inside one of the feature modules.
We can think about each feature as the equivalent of [microservice](https://en.wikipedia.org/wiki/Microservices) or private library.

The modularized code-base approach provides few benefits:
- better [separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns). Each module has a clear API., Feature related classes life in different modules and can't be referenced without explicit module dependency.
- features can be developed in parallel eg. by different teams
- each feature can be developed in isolation, independently from other features
- faster compile time

### Module types and module dependencies

This is a diagram present dependencies between project modules (Gradle sub-projects).

![module_dependencies](https://github.com/igorwojda/android-showcase/blob/main/misc/image/module_dependencies.png?raw=true)

Note that due usage of Android `dynamic-feature` module dependencies are reversed (feature modules are depending on `app` module, not another way around).

We have three kinds of modules in the application:

- `app` module - this is the main module. It contains code that wires multiple modules together (dependency injection setup, `NavHostActivity`, etc.) and fundamental application configuration (retrofit configuration, required permissions setup, custom application class, etc.).
- application-specific `library_x` modules that some of the features could depend on. This is helpful if you want to share some assets or code only between few feature modules (currently app has no such modules)
- feature modules - the most common type of module containing all code related to a given feature.

### Feature module structure

`Clean architecture` is the "core architecture" of the application, so each `feature module` contains own set of Clean architecture layers:

![module_dependencies_layers](https://github.com/igorwojda/android-showcase/blob/main/misc/image/module_dependencies_layers.png?raw=true)

> Notice that `app` module and `library_x` modules structure differs a bit from feature module structure.

Each feature module contains non-layer components and 3 layers with distinct set of responsibilities.

![feature_structure](https://github.com/igorwojda/android-showcase/blob/main/misc/image/feature_structure.png?raw=true)

#### Presentation layer

This layer is closest to what the user sees on the screen. The `presentation` layer is a mix of `MVVM` (Jetpack `ViewModel` used to preserve data across activity restart) and
`MVI` (`actions` modify the `common state` of the view and then new state is edited to a view via `LiveData` to be rendered).

> `common state` (for each view) approach derives from
> [Unidirectional Data Flow](https://en.wikipedia.org/wiki/Unidirectional_Data_Flow_(computer_science)) and [Redux
> principles](https://redux.js.org/introduction/three-principles).

Components:
- **View (Fragment)** - presents data on the screen and pass user interactions to View Model. Views are hard to test, so they should be as simple as possible.
- **ViewModel** - dispatches (through `LiveData`) state changes to the view and deals with user interactions (these view models are not simply [POJO classes](https://en.wikipedia.org/wiki/Plain_old_Java_object)).
- **ViewState** - common state for a single view
- **NavManager** - singleton that facilitates handling all navigation events inside `NavHostActivity` (instead of separately, inside each view)

#### Domain layer

This is the core layer of the application. Notice that the `domain` layer is independent of any other layers. This allows to make domain models and business logic independent from other layers.
In other words, changes in other layers will have no effect on `domain` layer eg. changing database (`data` layer) or screen UI (`presentation` layer) ideally will not result in any code change withing `domain` layer.

Components:
- **UseCase** - contains business logic
- **DomainModel** - defies the core structure of the data that will be used within the application. This is the source of truth for application data.
- **Repository interface** - required to keep the `domain` layer independent from the `data layer` ([Dependency inversion](https://en.wikipedia.org/wiki/Dependency_inversion_principle)).

#### Data layer

Manages application data and exposes these data sources as repositories to the `domain` layer. Typical responsibilities of this layer would be to retrieve data from the internet and optionally cache this data locally.

Components:
- **Repository** is exposing data to the `domain` layer. Depending on application structure and quality of the external APIs repository can also merge, filter, and transform the data. The intention of
these operations is to create high-quality data source for the `domain` layer, not to perform any business logic (`domain` layer `use case` responsibility).

- **Mapper** - maps `data model` to `domain model` (to keep `domain` layer independent from the `data` layer).
- **RetrofitService** - defines a set of API endpoints.
- **DataModel** - defines the structure of the data retrieved from the network and contains annotations, so Retrofit (Moshi) understands how to parse this network data (XML, JSON, Binary...) this data into objects.

### Data flow

Below diagram presents application data flow when a user interacts with `album list screen`:

![app_data_flow](https://github.com/igorwojda/android-showcase/blob/main/misc/image/app_data_flow.png?raw=true)

## Dependency management

This project utilizes multiple mechanics to eaisly share the same versions of dependencies. 

## Getting started

There are a few ways to open this project.

### Android Studio

1. `Android Studio` -> `File` -> `New` -> `From Version control` -> `Git`
2. Enter `https://github.com/olucasmoro/movie-app.git` into URL field an press `Clone` nutton

## Author

[![Follow me](https://github.com/igorwojda/android-showcase/raw/main/misc/image/avatar.png)](https://instagram.com/olucasmoro)

[![Follow me](https://img.shields.io/badge/-LinkedIn-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://linkedin.com/in/olucasmoro)](https://linkedin.com/in/olucasmoro) &nbsp;
[![Instagram Badge](https://img.shields.io/badge/-Instagram-E1306C?style=flat-square&labelColor=E1306C&logo=instagram&logoColor=white&link=https://www.instagram.com/olucasmoro/)](https://www.instagram.com/olucasmoro/)

## License
```
MIT License

Copyright (c) 2021 Lucas Moro

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
associated documentation files (the "Software"), to deal in the Software without restriction, including 
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to 
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial 
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN 
NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
