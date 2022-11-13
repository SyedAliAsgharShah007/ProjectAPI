The repository contains the code files.

At first setup the MySQL server at your Local Machine

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

New Added Code

Integration of 3rd Party API ( Endpoints secured with JWT tokens - access_token and refresh_token)

Send following GET request ( In Header Section use KEY: Authorization  | Value: Bearer {access_token}
http://localhost:8080/api/getUsers
http://localhost:8080/api/getPosts
http://localhost:8080/api/getComments

