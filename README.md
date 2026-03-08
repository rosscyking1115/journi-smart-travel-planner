# Journi – Smart Travel Planner

Journi is a **JavaFX desktop application** designed to help users discover travel destinations based on their preferences such as place type and travel time.

The application recommends destinations and displays useful information including travel duration, best season, and destination description.

---

## Features

- Destination filtering (City, Beach, Mountain)
- Travel time categories (Short, Medium, Long)
- Smart destination recommendation
- User authentication (Sign Up / Sign In)
- Favourite destinations system
- Detailed destination view
- Sorting destinations by travel time
- Google search integration for weather and transport

---

## Technologies Used

- Java 17
- JavaFX
- Object-Oriented Programming
- File-based data storage (.txt)
- IntelliJ IDEA

---

## Project Architecture

### GUI & Navigation

- Home
- SignInView
- SignUpView
- SearchView
- ResultView
- DetailView
- FavouritesView

### Planner Models

- Destination
- PlaceType
- TravelTime
- LocationInfo
- AbstractLocation

### Account System

- Account
- AccountRepository
- AuthController

### Utilities

- DestinationReader
- DestinationSorter
- FavouritesManager

---

## Screenshots

(Add screenshots here if available)

---

## Running the Application

Requirements:

- Java 17
- IntelliJ IDEA (recommended)

Run the application using:

```bash
java Main
```

---

## Future Improvements

- Integrate real-time weather APIs
- Add map integration
- Replace text files with database storage
- Improve recommendation algorithm

---

## Author

Cheng-Yuan King
