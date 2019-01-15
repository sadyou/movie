# movie

**Linux**  
`./mvnw clean package`  
**Windows**  
`mvnw.cmd clean package`  

`mvnw spring-boot:run`
[http://localhost:8080/trainingapp/swagger-ui.html](http://localhost:8080/trainingapp/swagger-ui.html).

[http://localhost:8080/trainingapp/h2-console](http://localhost:8080/trainingapp/h2-console).


## To run collection iteration-loop-using-variables_csv.postman_collection.json

Note: to run following command you need to be in the root folder i.e. download repo and then `cd movie`

### 1: create multiple users reading data from user.csv
newman run src/test/postman/collections/iteration-loop-using-variables_csv.postman_collection.json -e src/test/postman/env/movie-local.postman_environment.json --folder 1-create-users_csv -d src/test/postman/csv/user.csv

### 2: save multiple movies , each movie is two different postcodes
newman run src/test/postman/collections/iteration-loop-using-variables_csv.postman_collection.json -e src/test/postman/env/movie-local.postman_environment.json --folder 2-save-movies-two-postcodes_csv -d src/test/postman/csv/movie2code.csv

### 3: save multiple movies , each movie is one postcode
newman run src/test/postman/collections/iteration-loop-using-variables_csv.postman_collection.json -e src/test/postman/env/movie-local.postman_environment.json --folder 3-save-movies-one-postcodes_csv -d src/test/postman/csv/movie1code.csv

