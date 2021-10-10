<%@ page import="ru.itis.sign.models.User" %>
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
    <title>Profile</title>






    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <style>

        p{
            font-size: 5.125rem;
            text-anchor: middle;
            color:#FFD700;

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


</head>
<body class="text-center">
    <p>${user.getUsername()}</p>
    <img class="avatar" alt="IMAGE" src=" <c:choose>
    <c:when test = "${path==null}">../img/no_avatar.png</c:when>
    <c:otherwise>${path}</c:otherwise>
    </c:choose>
    "
    width="300" height="300" />
    <div class="mb-3">
    <form  action="/profile" method="post" enctype="multipart/form-data">
        <input class="form-control" type="file" id="formFile"name="avatar" accept="image/*" required>
        <input type="submit" value="File Upload">
    </form>
    </div>




</body>
</html>
