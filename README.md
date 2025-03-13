# Concert Tracker API

Final Spring Boot API project for the Promineo Tech Back End Development course.

**Project participant(s):** Elliot Hill

**Title:** Concert Tracking API

### Executive Summary

The Concert Tracking API is a RESTful web service designed to help users catalog and manage concert events, venues, and artists. Built using Spring Boot and JPA, the API allows users to store, retrieve, update, and delete information about concerts, ensuring seamless tracking of past and upcoming events.

The API features a structured database with three core entities: Concert, Venue, and Artist. It supports essential CRUD operations, enabling users to query concerts by ID, associate them with specific venues and artists, and modify event details. A many-to-many relationship is established between concerts and artists, reflecting real-world scenarios where multiple artists perform at a single concert.

Future enhancements may include a SetList entity to track songs played at each concert, along with search functionality for concerts by song.

### Initial Features

-   **Database Design:** Create an Entity Relationship Diagram (ERD) to illustrate relationships between Concerts, Venues, and Artists.
-   **Concert Management:** Users can create, retrieve, update, and delete concert records.
-   **Venue Management:** Users can create, retrieve, update, and delete venue records.
-   **Artist Management:** Users can create, retrieve, update, and delete artist records.
-   **Relationships:**
    -   Implement a **many-to-one** relationship between Concerts and Venues (each concert occurs at one venue, but a venue can host many concerts).
    -   Implement a **many-to-many** relationship between Concerts and Artists (a concert can feature multiple artists, and an artist can perform at multiple concerts).
-   **REST API Endpoints:**
    -   **Concert Endpoints**:
        -   `GET /concerts` – Retrieve all concerts.
        -   `GET /concerts/{id}` – Retrieve a concert by ID.
        -   `POST /concerts` – Create a new concert.
        -   `PUT /concerts/{id}` – Update an existing concert.
        -   `DELETE /concerts/{id}` – Delete a concert.
    -   **Venue Endpoints:**
        -   `GET /venues` – Retrieve all venues.
        -   `GET /venues/{id}` – Retrieve a venue by ID.
        -   `POST /venues` – Create a new venue.
        -   `PUT /venues/{id}` – Update an existing venue.
        -   `DELETE /venues/{id}` – Delete a venue.
    -   **Artist Endpoints:**
        -   `GET /artists` – Retrieve all artists.
        -   `GET /artists/{id}` – Retrieve an artist by ID.
        -   `POST /artists` – Create a new artist.
        -   `PUT /artists/{id}` – Update an existing artist.
        -   `DELETE /artists/{id}` – Delete an artist.

### Stretch Goals

-   Include a SetList entity and allow users to look up sets by ID
-   Allow users to look up concerts by song(s)