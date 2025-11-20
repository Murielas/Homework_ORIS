<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.itis.entities.Product" %>
<%@ page import="ru.itis.services.ProductData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Цветочный Everbloom</title>
  <link rel="stylesheet" href="/src/main/webapp/css/styles.css">
  <link rel="stylesheet" href="/src/main/webapp/css/cart.css">
  <link rel="stylesheet" href="/src/main/webapp/css/menu.css">

  <link rel="icon" type="image/x-icon" href="/src/main/webapp/images/icon-sunflower.ico">
</head>
<body>
  <%@ include file="header.jsp"%>

  <h1> Ваша корзина <h1>

  <c:choose>
        <c:when test="${cart == null}"> <p> В корзине пусто, добавьте товары </p> </c:when>
        <c:otherwise>
            <div class="Cart">
                <c:set var="totalPrice" value="0"/>
                <c:forEach var="entry" items="${cart.listForCart}">
                    <c:set var="product" value="<%= ProductData.findProductById((Long) pageContext.getAttribute(\"entry\").getKey()) %>"/>
                    <c:if test="${product != null}">
                    <c:set var="itemTotal" value="${product.price * entry.value}"/>
                    <c:set var="totalPrice" value="${totalPrice + itemTotal}"/>
                    <div class="Product">
                    <p> ${product.name}   x  ${entry.value} + "   -----  " + ${product.price} + "  руб </p>
                    </div>
                    </c:if>
                </c:forEach>

                <div class="Price">
                <p> Итого:  ${totalPrice} рублей </p>
                </div>

                <form action="/do-order" method="post">
                <button id="order_button"> Сделать заказ </button>
                </form>
            </div>
        </c:otherwise>
  </c:choose>

  <%@ include file="footer.jsp"%>

  <script src="/src/main/webapp/js/script.js"></script>
</body>
</html>