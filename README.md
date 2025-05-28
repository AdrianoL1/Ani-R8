
# Ani-R8 API

A REST API developed with **Java** and **Spring Boot** that allows users to manage their personal anime lists, inspired by MyAnimeList.

## Features

- Following **MVCS** (Model-View-Controller-Service) and **DTO** (Data Transfer Object) design patterns for a clean architecture
- User registration and management
- Anime catalog management
- Genre management
- Personal user-anime lists with:
  - Status (Watching, Plan to Watch, On-hold, Completed, Dropped)
  - Number of episodes watched
  - Personal ratings
- Secure authentication with JWT

## Technologies Used

- Java 21
- Spring Boot 3
- Spring Data JPA
- MySQL Database
- Flyway for Migrations
- Lombok

## Database Model Overview

- **User**: Represents an application user.
- **Anime**: Represents an anime entry.
- **anime_genre**: Represents an associative table for Animes and Genres.
- **Genre**: Represents a genre (e.g., Action, Drama, Comedy).
- **Manga**: Represents a manga entry.
- **manga_genre**: Represents an associative table for Mangas and Genres.
- **UserAnime**: Represents the user's interaction with an anime.
- **UserManga**: Represents the user's interaction with a manga.


![](https://github.com/AdrianoL1/Ani-R8/blob/main/static/db_diagram.png)

## Running the Project Locally

1. **Clone the repository**
   ```bash
   git clone https://github.com/AdrianoL1/Ani-R8.git
   cd Ani-R8
   ```

2. **Set up the environment**
   - Java 21 or later installed
   - Maven installed

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API**
   - Localhost: `http://localhost:8080`

## TODO List

- [x] Add JWT Authentication for secure endpoints
- [x] User registration and login
- [x] Personal user-anime lists
- [x] Personal user-manga lists
- [x] Add Swagger for documentation
- [ ] Implement pagination and sorting
- [ ] Enable filtering animes by genre, rating, and status
- [ ] Build a frontend client (React)

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License

This project is open-source and available under the MIT License.