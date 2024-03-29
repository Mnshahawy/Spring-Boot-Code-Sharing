# Code Sharing Platform Using Spring Framework

This project is part of the Java Backend Developer track as offered on JetBrains Academy. 

**The following is the project description and objectives as seen on JetBrains Academy 
followed by test results from my implementations**

## Description

Programming is full of little secrets. Sometimes, even within the same company, there is some secret code that should be hidden from most of the colleagues. This piece of code should only be available to certain people, and it may be deleted in the future to hide the traces.

In this stage, let's make such functionality possible. You will need to add two limitations on the code snippet's visibility:

* **A limit on the number of views will allow viewing the snippet** only a certain number of times, after which the snippet is deleted from the database. 
* **A limit on the viewing time will allow viewing a code snippet for a certain period of time**, and after its expiration, the code snippet is deleted from the database.

If both restrictions are applied to a certain code snippet, it has to be deleted when at least one of these limits is reached.

Obviously, the hidden pieces of code should not be shown on the `GET /code/latest` page or available by the `GET /api/code/latest` request. They should only be available through a link. So, remember that the latest snippets should not contain any restrictions.

If you are a true spy, you might object: if all the links have numeric identifiers, can't we find the secret snippets just by looking through the different numbers? Indeed, it is quite easy to access secret links this way. To avoid this, let's generate links not with consecutive numbers but with UUID's (Universally Unique IDentifiers). To implement this, see a tutorial on generating the UUID. Now, no snippets can be obtained without a direct link!

## Objectives

In this stage, your program should support the same endpoints as in the previous stage. Here is what's going to be different:

Code snippets should be accessible via UUID links. `POST /api/code/new` should return a UUID of the snippet instead of a number.
`POST /api/code/new` should take a JSON object with a field code and two other fields:
1. `time` field contains the time (in seconds) during which the snippet is accessible.
2. `views` field contains a number of views allowed for this snippet.

*Remember, that 0 and negative values should correspond to the absence of the restriction.*

`GET /code/new` should contain two elements on top of the others:
   1. `<input id="time_restriction" type="text"/>` should contain the time restriction.
   2. `<input id="views_restriction" type="text"/>` should contain the views restriction. Remember that `POST` request should contain numbers, not strings.
   3. `GET /api/code/latest` and `GET /code/latest` should not return any restricted snippets. 
   4. `GET /api/code/UUID` should not be accessible if one of the restrictions is triggered. Return 404 Not Found in this case and all the cases when no snippet with such a UUID was found. 
   5. `GET /api/code/UUID` should show what restrictions apply to the code piece. Use the keys time and views for that. A zero value (0) should correspond to the absence of the restriction.
   6. `time` field contains the time (in seconds) during which the snippet is accessible.
   7. `views` field shows how many additional views are allowed for this snippet (excluding the current one). 
   8. `GET /code/UUID` should contain the following elements:
      * `<span id="time_restriction"> ... </span>` in case the time restriction is applied.
      * `<span id="views_restriction"> ... </span>` in case the views restriction is applied.
      
   
*Note: if only one of the restrictions is applied, you should show only one of the above elements.*

## Web Testing Output
The following is the output for the three main views in the project. I will be adding results for the REST API testing as well.
1. The latest view

   <img src="./screenshots/latest_snippets.png" alt="The latest snippets view" width="600"/>
2. The code view

   <img src="./screenshots/view_snippet.png" alt="The single snippet view" width="600"/>
3. The create view

   <img src="./screenshots/create_snippet.png" alt="The view for adding new snippets" width="600"/>
