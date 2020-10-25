
This SpringBoot Application integrates Basic Authentication(i.e. Username and password)
 and OAuth2(i.e.Google SignIn).
 
JWT token is generated upon successful login. 

Secured resources (apis) can be accessed by using JWT. 

[Front-end respository](https://github.com/pratsonii/AngularBasicAndOAuth2)

----------

**OAuth URL** : http://localhost:8080/oauth/autherization/google

(Above url will be called from UI on click of 'Google Sign In' button)

(/oauth/autherization is mapped in SecurityConfig file. This can be anything you want.)

(Multiple OAuth servers i.e Github or Facebook can be mapped. URL to be called from UI : http://localhost:8080/oauth/autherization/facebook)


**H2 Console** : http://localhost:8080/h2-console

-----------

**_APIs_**

**New User :**

curl -X POST \
  http://localhost:8080/app/register \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: c9307978-f4df-b134-ffe0-4292b9b66266' \
  -d '{
	"email":"john@gmail.co",
	"fistName":"John2",
	"lastName":"Cena",
	"password":"wwe"
}'

**To get Token**

curl -X GET \
  http://localhost:8080/app/token \
  -H 'authorization: Basic dHJpcHBsZUhAZ21haWwuY28xOnd3ZQ==' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 5609851e-cc83-2d27-9fed-9de786f6be9f' \
  -d '{
	"email":"trippleH@gmail.co",
	"fistName":"TrippleH",
	"lastName":"ronaldo",
	"password":"wwe"
}'

**To get UserInfo**
curl -X GET \
  http://localhost:8080/app/userInfo \
  -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0cmlwcGxlSEBnbWFpbC5jbyIsImlhdCI6MTYwMzUyNjc3NywiZXhwIjoxNjAzNTQ0Nzc3fQ.Wwt6sM2yCHtrLaMQcT8hzNmeJgWzc9vmGl498XXkwO5kCC4qbX_fPr4OvKgNOX72ZgLoGB_Nf5l9EKo-bxDS7w' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json;charset=UTF-8' \ 
  
-----------------
