<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Цветочный Everbloom</title>
  <link rel="stylesheet" href="/src/main/webapp/css/styles.css">
  <link rel="stylesheet" href="/src/main/webapp/css/product.css">
  <link rel="stylesheet" href="/src/main/webapp/css/menu.css">

  <link rel="icon" type="image/x-icon" href="/src/main/webapp/images/icon-sunflower.ico">
</head>
<body>
  <%@ include file="header.jsp"%>

  <div class="Bouquet_photos">
    <a> <img class="Bouquet_week_photo" src="${product.photoPath}"/> </a>
    <div class="Information">
      <h2> ${product.name} </h2>
      <p> ${product.description} </p>
      <p> Стоимость: ${product.price} руб </p>
      <form action="/add-cart" method="post">
        <input type="hidden" name="id" value="${product.id}">
        <input type="hidden" name="returnUrl" value="${returnPath}">
        <button type="submit" id="buy_button"> В корзину </button>
      </form>
    </div>
  </div>

  <%@ include file="footer.jsp"%>

  <script src="/src/main/webapp/js/script.js"></script>
</body>
</html>