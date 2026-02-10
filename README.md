# University Companion App 📚🎓

University Companion App is an Android application built to simulate a real-life university assistant for students.  
The project focuses on clean architecture, proper separation of concerns, and working with real data from the internet.

This project represents a transition from local/fake data to real API-based data handling using modern Android development practices.

---

## 🚀 Features

- Display university courses and schedules
- Fetch data from a real REST API
- Clean separation between UI, business logic, and data layers
- Handle loading, success, and error states gracefully
- Modern UI built with Jetpack Compose

---

## 🏗️ Architecture

The project follows **MVVM (Model–View–ViewModel)** architecture with a clear structure:

- **UI Layer (Compose Screens)**  
  Responsible only for displaying data and reacting to UI states.

- **ViewModel Layer**  
  Manages UI state and communicates with the Repository using Coroutines.

- **Repository Layer**  
  Single source of truth for data.  
  Fetches data from the API and maps it into models suitable for the UI.

- **Networking Layer**  
  Implemented using Retrofit and Gson for API communication and JSON parsing.

---

## 🔄 Data Flow

API → Repository → ViewModel → UI

UI never talks directly to the data source.  
All data is exposed through observable state objects.

---

## ⚙️ Technologies Used

- Kotlin
- Jetpack Compose
- MVVM Architecture
- Retrofit
- Gson
- Coroutines
- State Management (Loading / Success / Error)

---

## 🧠 What I Learned

- How REST APIs work and how to consume them in Android
- Proper error handling and UI state management
- Clean architecture and responsibility separation
- Working with asynchronous code using Coroutines
- Building scalable and readable Android projects

---

## 📌 Project Status

✅ Completed  
The project is finished as part of a structured learning challenge and serves as a portfolio piece.


## 👨‍💻 Author

**Muhammad Barakat**  
Android Developer (Learning & Building 🚀)

---

## 📄 License

This project is for educational purposes.

---<img width="120" height="240" alt="Screenshot_20260210_185705" src="https://github.com/user-attachments/assets/dc880626-cfb8-4f1c-bfdb-0accc5dd9a37" />
<img width="120" height="240" alt="Screenshot_20260210_185654" src="https://github.com/user-attachments/assets/6a414001-7a8a-46da-82b4-5a60c5f05bff" />
<img width="120" height="240" alt="Screenshot_20260210_185638" src="https://github.com/user-attachments/assets/14fe831f-71a7-4d77-813c-d26e3f4d8721" />
