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
    <title>Home page</title>




    <%@include file="/view/jsp/components/links.jsp" %>




</head>
<body class="text-center">
<%@include file="/view/jsp/components/header.jsp" %>
<div class="container">
    <h1>Welcome to AniFox!</h1>
    <img src="<c:url value="/view/img/anifox.png"/>" class="img-fluid">
    <section class="py-5">
        <div class="container my-5">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <p class="lead">Вот уже много лет японская анимация пользуется огромным успехом по всему миру, включая Россию. Эти ленты любят за яркий сюжет, оригинальную рисовку и неизменный накал эмоций.</p>
                </div>
            </div>
        </div>
    </section>


    <h2 class="mt-5">О чем этот сайт?</h2>
    <div class="py-5 bg-image-full" style="background-image: url('<c:url value="/view/img/anime-font.jpg"/>')">
        <div style="height: 20rem"></div>
    </div>
    <section class="py-5">
        <div class="container my-5">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <p class="lead">Здесь вы можете найти любое аниме по вкусу, добавить его в ваш список аниме и поставить ему рейтинг</p>
                </div>
            </div>
        </div>
    </section>
    <h2>Создай свой аниме-лист</h2>
    <br>
    <br>
        <img src="<c:url value="/view/img/list.png"/>" class="img-fluid"  alt="List" >
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <h4 >Что ты посмотрел?</h4>
                <p class="lead"> Создайте свой личный список из большого количество аниме в нашей базе данных</p>

            </div>
        </div>
    </div>
    </section>

    <img src="<c:url value="/view/img/calendar.png"/>" calt="Calendar"> <section class="py-5">
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <h4 >Будь в курсе!</h4>
                <p class="lead"> Используйте свой список, чтобы систематизировать и отслеживать, какие тайтлы вы закончили, ваш текущий прогресс, что вы планируете посмотреть или прочитать и многое другое.</p>

            </div>
        </div>
    </div>
</section>

</div>

<%@include file="/view/jsp/components/footer.jsp" %>


</body>
</html>
