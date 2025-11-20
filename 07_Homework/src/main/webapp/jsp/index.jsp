<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Цветочный Everbloom</title>
    <link rel="stylesheet" href="/src/main/webapp/css/styles.css">
    <link rel="stylesheet" href="/src/main/webapp/css/index.css">
    <link rel="stylesheet" href="/src/main/webapp/css/menu.css">

    <link rel="icon" type="image/x-icon" href="/src/main/webapp/images/icon-sunflower.ico">
</head>
<body>
    <%@ include file="header.jsp"%>

    <div class="Banner">
        <img class="Banner_photo" src="/src/main/webapp/images/flower-shop.jpeg"/>
        <div class="Banner_text">
            <p> Подарите тепло золотой осени с нашей новой коллекцией букетов! </p>
            <i> Главные цветы этого сезона: георгины, хризантемы и целозии</i>
            <br>
            <br>
            <p> До конца осенней коллекции: </p>
            <p> <span id="countdown"></span> </p>
        </div>
    </div>

    <div class="Bouquet_week">
        <h1> Букеты недели </h1>
        <i> Выберите свой и нажмите </i>
    </div>

    <div class="Slider">
        <div class="Slides">
            <div class="Slide_photo"> <a href="/bouquet-1"> <img src="/src/main/webapp/images/bouquet_1.jpg"> </a> </div>
            <div class="Slide_photo"> <a href="/bouquet-2"> <img src="/src/main/webapp/images/bouquet_2.jpg"> </a> </div>
            <div class="Slide_photo"> <a href="/bouquet-3"> <img src="/src/main/webapp/images/bouquet_3.jpg"> </a> </div>
            <div class="Slide_photo"> <img src="/src/main/webapp/images/bouquet_4.jpg"> </div>
            <div class="Slide_photo"> <img src="/src/main/webapp/images/bouquet_5.jpg"> </div>
        </div>

        <button class="prev" onclick="showPrev()"> &#10094 </button>
        <button class="next" onclick="showNext()"> &#10095 </button>
    </div>

    <div class="Advices">
        <h1> Советы по уходу за цветами </h1>
        <div id="advices-container">
            <p id="advice"> &#9999; Сразу после покупки снимите упаковку, удалите лишние листья, которые могут соприкасаться с водой, и подрежьте стебли под углом 45 градусов острым ножом или секатором. </p>
        </div>
        <button onclick="nextAdvice()" class="button_advice"> Ещё совет </button>
    </div>

    <%@ include file="footer.jsp"%>

    <script src="/src/main/webapp/js/script.js"></script>
    <script src="/src/main/webapp/js/slider.js"></script>
    <script src="/src/main/webapp/js/countdown.js"></script>
    <script src="/src/main/webapp/js/dynamic_loadin.js"></script>
</body>
</html>