# /account

## 회원가입 

* Request

  ```
  GET http://localhost:8080/account/register
  ```

* Response

  ```
  <Response body is empty>
  ```

## 회원가입 처리

* Request

  ```
  POST http://localhost:8080/api/account/register

  {
   "id" : "",
   "email" : "",
   "password" : ""
  }
  ```

* Response

  ```
  {
   "id" : ""
  }
    ```
  ## 로그인 

* Request

  ```
  GET http://localhost:8080/account/login
  ```

* Response

  ```
  <Response body is empty>
  ```

## 로그인 처리

* Request

  ```
  POST http://localhost:8080/api/login

  {
   "id" : "",
   "password" : ""
  }
  ```

* Response

  ```
  {
   "id" : "",
   "password" : ""
  }
    ```
## 회원정보 조회

* Request

  ```
  GET http://localhost:8080/api/mypage/{userId}
  ```

* Response

  ```
  {
    "id": "",
    "userId": "",
    "password": "",
    "email" : "",
    "last_login_at" : "",
    "status" : ""
  }
  ```

## 회원정보 수정

* Request

  ```
  PUT http://localhost:8080/api/edit/{userId}

  {
    "id": ,
    "userId": "",
    "email": "",
    "password": "",
    "status": ""
  }
  ```

* Response

  ```
  {
    "id": "",
    "userId": "",
    "password": "",
    "email" : "",
    "status" : ""
  }
    ```

## 회원 탈퇴

* Request

  ```
  DELETE http://localhost:8080/api/resign/{userID}
  ```

* Response

  ```
  <Response body is empty>
  ```
