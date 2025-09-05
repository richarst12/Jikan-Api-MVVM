# Jikan Anime App

An Android app built using Kotlin + Jetpack Compose + MVVM + Hilt + Room , which fetches data from the [Jikan API](https://docs.api.jikan.moe/) and show information.

---

##  Agenda
- Show top anime list from Jikan API.
- Create with using MVVM architecture pattern.
- View details of selected anime (title, synopsis, genres, rating, episodes, trailer, and cast).
- Support offline mode using Room.

---

##  Features Implemented
- **Anime List (Dashboard)**
    - Fetches top anime list from Jikan API (`/v4/top/anime`).
    - Displays poster, title, episodes, and rating.
    - Click on an anime to view details.

- **Anime Detail Screen**
    - Shows trailer in WebView (if available), otherwise poster image.
    - Displays title, synopsis, episodes, rating, genres, and main cast.

- **Offline Support (Bonus)**
    - Anime list is cached in Room database.
    - If offline, app shows last fetched list from cache.
    - Data syncs when app goes online.

- **Error Handling**
    - Gracefully handles API errors and shows retry option.
    - Fallback to cached data if network is unavailable.

- **Architecture**
    - **MVVM (non-clean)** with Repository pattern.
    - **Hilt** for dependency injection.
    - **StateFlow + UiState** for reactive UI updates.

---

## Tech Stack
- Jetpack Compose
- Hilt (DI)
- Retrofit + OkHttp
- Room (Offline DB)
- Coil 
- Navigation Compose

---

## APK Link
( https://drive.google.com/file/d/1RfaAp3skZxkiEKHeX9DCqwD6qsOJKXzM/view?usp=share_link )


