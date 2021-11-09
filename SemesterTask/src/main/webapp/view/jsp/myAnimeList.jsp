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

    <link href="/view/css/bootstrap.min.css" rel="stylesheet">




</head>
<body >
<%@include file="/view/jsp/components/header.jsp" %>
<main class="container">
    <h1 class="text-center">Мой Аниме Лист</h1>
    <table class="table table-bordered border-dark">
        <tr>
            <th>#</th>
            <th>Название</th>
            <th>Статус</th>
            <th>Рейтинг</th>
        </tr>
        <c:forEach varStatus="i" var="anime" items="${animeList}">

            <tr>
                <td>${i.count}</td>
                <td>${anime.getTitle()}</td>
                <c:choose>
                    <c:when test="${anime.getStatus()==1}">
                        <td>Смотрю</td>
                    </c:when>
                    <c:when test="${anime.getStatus()==2}">
                        <td>Просмотрено</td>
                    </c:when>
                    <c:when test="${anime.getStatus()==3}">
                        <td>Буду Смотреть</td>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${anime.getStatus()==2}">
                        <td>
                           <form action="<c:url value="/addList"/>" method="post">
                               <input type="number" value="${anime.getUserRating()}" min="0" max="10" class="form-control" name="rating">
                               <input type="text" name="animeId" value="${anime.getAnimeId()}" style="display: none;">
                               <input type="submit"  style="display: none;">
                           </form>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>Без оценки</td>
                    </c:otherwise>
                </c:choose>
            </tr>

        </c:forEach>


    </table>
</main>


<%@include file="/view/jsp/components/footer.jsp" %>


</body>
</html>
