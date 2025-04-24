Android Project with MVVM, Use Cases, Data Sources, and Unit Tests
This project demonstrates the architecture of an Android application using MVVM (Model-View-ViewModel) with Use Cases and Data Sources to manage business logic and data flow. The project includes unit tests to ensure the reliability and correctness of the implementation.

Project Structure
MVVM: The architecture follows the MVVM pattern to separate UI concerns from business logic.

Use Cases: Business logic is organized into use case classes that act as an intermediary between the ViewModel and the data sources.

Data Sources: Data is fetched from various sources (local database, remote API, e.g.).

Unit Tests: Unit tests are written to validate the behavior of ViewModels, Use Cases, and Repository.

Technologies Used
Kotlin: The programming language for the project.

Jetpack Compose : Used for building the UI.

Hilt: For dependency injection.

JUnit: For unit testing.

Mockito: For mocking dependencies in unit tests.

Coroutines: For asynchronous programming.

Project Structure Overview
1. Model
Contains data models, including entities, DTOs (Data Transfer Objects), and domain models. These models represent the data being used and manipulated by the application.

2. View
Contains Composables or Views responsible for displaying UI elements. It communicates with the ViewModel to update the UI with changes.

3. ViewModel
Contains the presentation logic. The ViewModel acts as the intermediary between the View and the Use Cases. It provides data to the View and processes user actions.

4. Use Case
Contains business logic that interacts with Data Sources (either remote, local, or both). Use cases are invoked by the ViewModel to process specific tasks.

5. Data Sources
Handles data fetching, either from a remote API, local database, or other data sources. Includes the repository pattern that is responsible for providing data to the Use Cases.

Local Data Source: A source such as Room or SharedPreferences.

Remote Data Source: API calls using Retrofit or similar libraries.

6. Repository
The Repository is responsible for abstracting the data sources. It provides an interface for the Use Cases to interact with data, without worrying about where the data is coming from (local or remote).

7. Unit Tests
Unit tests are organized to test the ViewModel, Use Cases, and Repository independently. These tests ensure that the business logic and data handling behave correctly.
