<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Anime List</title>


    <style>
        .img-anime{
            width: 225px;
            height: 350px;
            margin-left: auto;
            margin-right: auto;
        }
    </style>

    <%@include file="/view/jsp/components/links.jsp" %>



</head>
<body>
<%@include file="/view/jsp/components/header.jsp" %>
    <main class="text-center">
        <h2 class="mt-5">Аниме</h2>
        <div class="py-5 bg-image-full" style="background-image: url('<c:url value="/view/img/anime-collage.jpg"/>')">
            <div style="height: 20rem"></div>
        </div>
        <div class="album py-5 bg-light">
            <div class="container">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
        <c:forEach var="anime" items="${animeList}">

            <div class="col">
                <div class="card shadow-sm">
                   <img class="img-anime" src="<c:url value="${avatars.get(anime.getAnimeId())}"/>">

                    <div class="card-body">
                        <p class="card-title">${anime.getAnimeTitle()}</p>
                        <p class="card-text"><c:choose>
                            <c:when test="${anime.getDescription().length()>200}">
                                ${anime.getDescription().substring(0,200)}...
                            </c:when>
                            <c:otherwise>
                                ${anime.getDescription()}
                            </c:otherwise>
                        </c:choose></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <a href="<c:url value="/anime?animeId=${anime.getAnimeId()}"/>" class="btn btn-sm btn-outline-secondary">Смотреть</a>
                            </div>
                            <small class="text-dark">Рейтинг аниме: ${anime.getRating()}</small>
                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>
                </div>

                </div>
            </div>


    </main>



<%@include file="/view/jsp/components/footer.jsp" %>
</body>
</html>
