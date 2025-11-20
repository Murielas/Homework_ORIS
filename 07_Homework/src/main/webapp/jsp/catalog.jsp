<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Цветочный Everbloom</title>
    <link rel="stylesheet" href="/src/main/webapp/css/styles.css">
    <link rel="stylesheet" href="/src/main/webapp/css/catalog.css">
    <link rel="stylesheet" href="/src/main/webapp/css/menu.css">

    <link rel="icon" type="image/x-icon" href="/src/main/webapp/images/icon-sunflower.ico">
</head>
<body>
    <%@ include file="header.jsp"%>


    <h1 id="h1_catalog">
        <c:choose>
            <c:when test="${requiredCategory == 'Букеты'}"> Каталог букетов </c:when>
            <c:when test="${requiredCategory == 'Цветы'}"> Каталог цветов </c:when>
            <c:when test="${requiredCategory == 'Подарки'}"> Каталог подарков </c:when>
            <c:when test="${requiredCategory == 'Открытки'}"> Каталог открыток </c:when>
            <c:otherwise>Каталог товаров</c:otherwise>
        </c:choose>
    </h1>

    <div class="catalog_section">

        <div class="products_container">
            <c:forEach var="product" items="${products}">
                <div class="product_card">
                    <a href="/product/${product.name}">
                    <img class="product_image" src="${product.photoPath}">
                    </a>
                    <p> ${product.name} </p>
                    <p> ${product.price} руб </p>
                    <form action="/add-cart" method="post">
                        <input type="hidden" name="id" value="${product.id}">
                        <input type="hidden" name="returnUrl" value="${returnPath}">
                        <button type="submit" class="buy_button"> В корзину </button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>

    <%@ include file="footer.jsp"%>

    <script src="/src/main/webapp/js/script.js"></script>
    <script src="/src/main/webapp/js/catalog-button.js"></script>
</body>
</html>