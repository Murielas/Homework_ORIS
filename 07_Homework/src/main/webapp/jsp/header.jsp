<%@ page contentType="text/html; charset=UTF-8" %>
<header>
    <nav>
        <ul class="menu">
            <li> <a href="/index"> <img id="nav_icon" src="/src/main/webapp/images/icon-sunflower.png"/> </a> </li>
            <li> <a href="/index"> Главная </a> </li>
            <li class="button_catalog"> <a id="button_menu" class="close" onclick="openCatalog()"> Каталог </a>
                <ul class="catalog">
                    <li> <a href="/catalog/Букеты"> Букеты </a> </li>
                    <li> <a href="/catalog/Цветы"> Цветы </a> </li>
                    <li> <a href="/catalog/Подарки"> Подарки </a> </li>
                    <li> <a href="/catalog/Открытки"> Открытки </a> </li>
                </ul>
            </li>
            <li> <a href="about.html"> О нас </a> </li>
            <li> <a> Доставка </a> </li>
            <li> <a href="contact.html"> Контакты </a> </li>
            <li> <a href="/account"> Личный кабинет </a> </li>
            <li> <a href="/cart"> Корзина </a> </li>
            <li> <img onclick="changeThemeDark()" id="theme_icon_dark" src="/src/main/webapp/images/icon-theme-d.png"> </li>
            <li> <img onclick="changeThemeLight()" id="theme_icon_light" src="/src/main/webapp/images/icon-theme-l.png"> </li>
        </ul>
    </nav>
</header>