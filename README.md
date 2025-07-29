 <img width="820" height="312" alt="3" src="https://github.com/user-attachments/assets/555dd2cc-486a-44c2-a81e-d64acdae59e9" />
 
## Project Brief
The Rai Record store wants to modernise its inventory management from physical to digital. A modern digital inventory management systen would streamline operations and help Rai Records to compete with competitors. The store requires a strong robust RESTful API that querys, filters and updates it's inventory of records and the avaliable stock. Utilising Springboot to create RESTful endpoints following a MVC architecture.

## Technology
- Spring Boot
- PostgreSQL
- TDD/Mockito
- JPA & Hibernate
- MVC architecture

## API

### Accessing the API
The API can be accessed at base URL: `http://localhost:8080/api/v1/albums`

### Endpoints

#### **GET** `/albums`
Returns a list of all albums in database

```java
[
    {
        "id": 1,
        "name": "Anti",
        "artist": "Rihanna",
        "release_year": 2016,
        "genre": "RNB",
        "album_info": "Anti is the eighth studio album by Barbadian singer Rihanna. It was released on 28 January 2016 by Roc Nation and Westbury Road.",
        "stock_count": 5
    },
    {
        "id": 2,
        "name": "Money Can't Buy Happiness",
        "artist": "Fredo",
        "release_year": 2021,
        "genre": "HIPHOP",
        "album_info": "Money Can't Buy Happiness is the second studio album by British rapper Fredo, released on January 29, 2021, by Since 93, RCA, and Sony Music UK. The album includes guest appearances from Dave, Pop Smoke, Summer Walker & Young Adz.",
        "stock_count": 5
    },
    {
        "id": 3,
        "name": "Loose",
        "artist": "Nelly Furtado",
        "release_year": 2006,
        "genre": "POP",
        "album_info": "Loose is the third studio album by Canadian singer-songwriter Nelly Furtado, released on 7 June 2006, by Geffen and Mosley Music Group. Recording sessions for Loose took place from 2005 to 2006.",
        "stock_count": 8
    }
... etc.
]
```
