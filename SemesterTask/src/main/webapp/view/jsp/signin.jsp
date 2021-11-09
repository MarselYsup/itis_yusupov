<%--
  Created by IntelliJ IDEA.
  User: пк
  Date: 29.09.2021
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
  <meta name="generator" content="Hugo 0.88.1">
  <title>Sign In</title>




  <%@include file="/view/jsp/components/links.jsp" %>

  <link href="/view/css/bootstrap.min.css" rel="stylesheet">

  <style>
      .btn-primary{
        background: #1e2125;

      }
      .btn-primary:hover{
        background: #000000;
        border-color: #1e2125;
      }

    .image-b{
      border: 3px solid black;
      margin-bottom:1.5rem!important;
    }
    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      user-select: none;
    }


    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
    }
  </style>


  <!-- Custom styles for this template -->
  <link href="<c:url value="/view/css/signup.css"/>" rel="stylesheet">
</head>
<body class="text-center">

<%@include file="/view/jsp/components/header.jsp" %>

<main class="form-signin">
  <form action="<c:url value="/sign-in"/>" method="post" class = "container">
    <img class="image-b" src="<c:url value="/view/img/anime.png"/>" alt="Anime" width="200" height="200">
    <h1 class="h3 mb-3 fw-normal">Sign in</h1>

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


<%@include file="/view/jsp/components/footer.jsp" %>
</body>
</html>
