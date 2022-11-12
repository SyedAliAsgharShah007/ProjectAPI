The repository contains the code files.

Login using the following method
-Use the postman to send POST request http://localhost:8080/api/login
-In body section, select x-www-form-urlencoded, use 2 Keys
1- KEY: username | Value: haider
2- password | Value: 1234
-Send the request
-In body section you will have two tokens, access_token and refresh_token

Send following GET request ( In Header Section use KEY: Authorization  | Value: Bearer {access_token}
http://localhost:8080/api/users
http://localhost:8080/api/posts
http://localhost:8080/api/comments
