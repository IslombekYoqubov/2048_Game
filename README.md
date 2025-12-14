# 2048 Game (Android)

A simple Android implementation of the classic **2048 puzzle game**, written in **Kotlin**. The goal is to combine tiles with the same values and reach the 2048 tile while getting the highest score possible.

## About the Project

This project is an Android version of the popular 2048 game created mainly for practice and learning purposes. It focuses on basic game logic, swipe handling, and core Android development concepts.

## Features

- Classic 2048 gameplay
- Swipe-based tile movement
- Score calculation
- Simple and clean user interface
- Runs fully offline

## Technologies


- **Programming Language:** Kotlin
- **Platform:** Android
- **Architecture:** MVC / simple game-based architecture
- **UI:** Android XML Layouts
- **Build System:** Gradle
- **IDE:** Android Studio
- **Minimum SDK:** Android API 21+

## Installation


1. Clone the repository:
   ```bash
   git clone https://github.com/IslombekYoqubov/2048_Game.git
   ```

2. Open the project in **Android Studio**.

3. Let Gradle sync complete.

4. Run the app on an emulator or physical Android device.

## Usage

- Swipe **up, down, left, or right** to move tiles.
- Tiles with the same number merge into one when they collide.
- After each move, a new tile appears on the board.
- The game ends when no more valid moves are available.

## Game Rules

- The game is played on a **4x4 grid**.
- Each move slides all tiles in one direction.
- When two tiles with the same number touch, they merge into a single tile with double the value.
- The objective is to create a tile with the value **2048**.

## Project Structure

```
2048_Game/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## Contributing

Contributions are welcome.

1. Fork the repository
2. Create a new branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add new feature"
   ```
4. Push to the branch and open a Pull Request

## License

This project is open source. You may add a license file (MIT, Apache 2.0, etc.) if needed.

## Author

**Islombek Yoqubov**  
GitHub: https://github.com/IslombekYoqubov

---

If you find this project useful, consider giving it a star on GitHub.

