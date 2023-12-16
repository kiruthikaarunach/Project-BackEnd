# Spring Boot MongoDB CRUD example - Restful CRUD API

API Development:
I have used React js for the Front End and Spring Boot for the Back end development.
Below are the list of all the  APIs developed-
URL – localhost:3000:/api/auth
1.User Registration and Authentication-
POST-
/signin – to Login to application 
/signup – to register to application

2.CRUD on the BlogPosts-
1.GET
/blogposts – to fetch all the blogposts
/blogposts/{id} – to get blogpostbyid
/comments – to fetch all the comments
/comments/{id} – to get commentbyid

2.PUT
/blogposts/{title}  - to update blogpost by title
/comments/{name}-  to update comment by name

3.POST  - 
/blogposts – to create a blogpost
/comments – to create a comment

4.DELETE
/blogposts/{title}  - to delete blogpost by title
/blogposts – to delete all blogposts
/comments/{name}-  to delete comment by name
/comments – to delete all comments

3.List all blogposts filtered by title category and all comments filtered by name category
/comments – to filterbynamecontating the comment
/blogposts- to filterbytitlecontaining the blogpost

Script for Data Population-
I have used Junit for Datapopulation and this can be run multiple times to populate data as per the requirement. This can perform all the CRUD operations by calling the corresponding  BlogPost and Comment Controller.
