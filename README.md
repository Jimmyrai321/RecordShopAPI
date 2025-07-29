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

### <img width="34" height="15" alt="GET" src="https://github.com/user-attachments/assets/348be0ea-8445-49bd-9fd2-b8ee7e81d9ee" /> `/albums`

Returns a list of all albums in database.

Response:
```json
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
### <img width="34" height="15" alt="GET" src="https://github.com/user-attachments/assets/348be0ea-8445-49bd-9fd2-b8ee7e81d9ee" /> `/albums/:id`

Returns a single album with given ID as path variable e.g `http://localhost:8080/api/v1/albums/2`. Invalid ID will throw 404 and error message.

Response:
```json
{
        "id": 2,
        "name": "Money Can't Buy Happiness",
        "artist": "Fredo",
        "release_year": 2021,
        "genre": "HIPHOP",
        "album_info": "Money Can't Buy Happiness is the second studio album by British rapper Fredo, released on January 29, 2021, by Since 93, RCA, and Sony Music UK. The album includes guest appearances from Dave, Pop Smoke, Summer Walker & Young Adz.",
        "stock_count": 5
    }
```
Invalid ID:
```
[!] No album exist at id: (10)!
```
### <img width="34" height="15" alt="GET" src="https://github.com/user-attachments/assets/348be0ea-8445-49bd-9fd2-b8ee7e81d9ee" /> `/albums?` (query parameters)

The GET endpoint accepts 4 query parameters.

| Key | Description | Example |
| ------------- | ------------- | ------------ |
| name  | Returns all albums of specified name  | `/albums?name=Loose` |
| artist  | 	Returns all albums of specified artist  | `/albums?artist=Fredo` |
| release_year  | Returns all albums of specified release year  | `/albums?release_year=2016` |
| genre  | Returns all albums of specified genre  | `/albums?genre=HIPHOP` |

> [!NOTE]
> - API handles one query parameter request at a time.
> - Only these following genres are permitted to be queried: **COUNTRY, ELECTRONIC, RNB, HOUSE, DISCO, EDM, JAZZ, METAL, HIPHOP, LATIN, DUBSTEP, REGGAE, ROCK, POP, INDIE.**


### <img width="46" height="15" alt="POST" src="https://github.com/user-attachments/assets/aedb9d01-ed2f-4fe6-93ab-20e90e2fc9ff" /> `/albums`

Adds new albums to the inventory.

Request Body:

```json
{
"name": "Psychodrama",
"artist":"Dave",
"genre":"HIPHOP",
"stock_count": 10,
"album_info":"Psychodrama is the debut studio album by British rapper Dave, released on 8 March 2019. The album features guest appearances from J Hus, Burna Boy and Ruelle. Psychodrama was executive produced by both Dave and Fraser T. Smith.",
"release_year":2006
}
```
> [!NOTE]
> album_info should not exceed more than 255 characters.

Response: 

```json
{
    "id": 4,
    "name": "Psychodrama",
    "artist": "Dave",
    "release_year": 2006,
    "genre": "HIPHOP",
    "album_info": "Psychodrama is the debut studio album by British rapper Dave, released on 8 March 2019. The album features guest appearances from J Hus, Burna Boy and Ruelle. Psychodrama was executive produced by both Dave and Fraser T. Smith.",
    "stock_count": 10,
    "message": "✔ Album added successfully"
}
```

### <img width="34" height="15" alt="PUT" src="https://github.com/user-attachments/assets/9639ac4a-a370-44eb-bc2a-10daacdb4449" /> `/albums/:id`

Updates album of selected ID with any field requiring update present in request body. Invalid ID will throw 404 and error message.

Request Body:

```json
{
"stock_count":2
}
```

Response: 

```json
{
    "id": 3,
    "name": "Loose",
    "artist": "Nelly furtado",
    "release_year": 2006,
    "genre": "POP",
    "album_info": "Loose is the third studio album by Canadian singer-songwriter Nelly Furtado, released on 7 June 2006, by Geffen and Mosley Music Group. Recording sessions for Loose took place from 2005 to 2006.",
    "stock_count": 2,
    "message": "✔ Updated Album id: 3 successfully!"
}
```

Invalid ID:

```
[!] Album at id: 6 doesn't exist!
```
### <img width="70" height="15" alt="DELETE" src="https://github.com/user-attachments/assets/c282e969-4537-478e-964a-a3670f307a61" /> `/albums/:id`

Deletes album at the specified ID, and returns a success message. Invalid ID will throw 404 and error message.

Response: 

```
✔ Album 1 deleted successfully!
```

Invalid ID:

```
[!] Cannot delete album 6 as it doesn't exist!
```

### Health Status

Server status can be checked at `http://localhost:8080/actuator/health`

## Database Connections

### Dev Mode

This API can be configured to run using a H2 in-memory database connection. To utilise this, clone this repository and set `spring.profiles.active=dev` in the application.properties file.

### Connection to PostgreSQL database

To persist data, a PostgreSQL database connection is recommended. Set `spring.profiles.active=prod` in the `application.properties` file. Connection information will need to be configured in a new `application-prod.properties` file as follows:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/<YOUR_DB_NAME>
spring.datasource.username=<YOUR_USERNAME>
spring.datasource.password=<YOUR_PASSWORD>
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.hibernate.ddl-auto= update
```

> [!NOTE]
> For cloud hosted databases change url to `spring.datasource.url=<CLOUD_HOSTED_URL>` with corresponding username and password.

## Future Considerations
- [ ] Further error handling and messaging to handle edge cases
- [x] Integration with mobile front-end
