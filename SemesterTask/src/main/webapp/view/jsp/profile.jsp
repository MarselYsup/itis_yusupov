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




    <%@include file="/view/jsp/components/links.jsp" %>



    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            max-width: 400px;
            max-height: 500px;
            margin: auto;
            text-align: center;
        }
        .avatar {
            width: 124px;
            height: 124px;
            display: flex;
            margin-left: auto;
            margin-right: auto;
            margin-bottom: 1.5em;
            border-radius: 100%;

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
<body >
<%@include file="/view/jsp/components/header.jsp" %>
<div class="card">
<h1>${user.getUsername()}</h1>
<img class="avatar" alt="IMAGE" src=" <c:choose>
    <c:when test = "${path==null}"><c:url value="/view/img/no_avatar.png"/></c:when>
    <c:otherwise><c:url value="${path}"/></c:otherwise>
    </c:choose>
    "/>
<div class="mb-3">
    <form  action="<c:url value="/profile"/>" method="post" enctype="multipart/form-data">
        <input class="form-control" type="file" id="formFile"name="avatar" accept="image/*" required>
        <input type="submit" value="File Upload">
    </form>
</div>

</div>

<%@include file="/view/jsp/components/footer.jsp" %>


</body>
</html>
