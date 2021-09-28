<%--
  Created by IntelliJ IDEA.
  User: пк
  Date: 29.09.2021
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
  <meta name="generator" content="Hugo 0.88.1">
  <title>Sign Up</title>






  <link href="../css/bootstrap.min.css" rel="stylesheet">

  <style>
    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      user-select: none;
    }
    h2{
      color:red;
    }

    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
    }
  </style>


  <!-- Custom styles for this template -->
  <link href="../css/signup.css" rel="stylesheet">
</head>
<body class="text-center">

<main class="form-signin">
  <form action="/sign-in" method="post">
    <img class="mb-4" src="../img/anime.png" alt="" width="200" height="200">
    <h1 class="h3 mb-3 fw-normal"><%
      if(null!=request.getAttribute("errorMessage"))
      {
        out.print(request.getAttribute("errorMessage"));
      }
      else if (null!=request.getAttribute("errorMessagePassword")){
        out.print(request.getAttribute("errorMessagePassword"));
      }
      else {
        out.print("Sign in");
      }
    %></h1>

    <div class="form-floating">
      <input name="username" class="form-control" id="floatingInput" placeholder="Username">
      <label for="floatingInput">Username</label>
    </div>
    <div class="form-floating">
      <input name="password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
      <label for="floatingPassword">Password</label>
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy;2021</p>
  </form>
</main>



</body>
</html>
