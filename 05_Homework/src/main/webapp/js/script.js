function openCatalog() {
    let block = document.getElementById("button_menu");
    let catalog = document.querySelector('.catalog');

    if (block.classList.contains("close")) {
       block.classList.remove("close");
       block.classList.add("open");
       catalog.style.opacity = "1";
       catalog.style.transform = "scaleY(1)";
       catalog.style.visibility = "visible";
    } else {
       block.classList.remove("open");
       block.classList.add("close");
       catalog.style.opacity = "0";
       catalog.style.transform = "scaleY(0)";
       catalog.style.visibility = "hidden";
    }
}

function changeThemeDark() {
    let moon = document.getElementById("theme_icon_dark");
    moon.style.visibility = "hidden";
    let sun = document.getElementById("theme_icon_light");
    sun.style.visibility = "visible";

    document.body.style.backgroundColor = "#363636";
    document.body.style.color = "#FFFFFF";

    const form = document.querySelector('.personal_account form');
    form.style.backgroundColor = "#8E8E8E";
}

function changeThemeLight() {
    let moon = document.getElementById("theme_icon_dark");
    moon.style.visibility = "visible";
    let sun = document.getElementById("theme_icon_light");
    sun.style.visibility = "hidden";

    document.body.style.backgroundColor = "#FBFCFD";
    document.body.style.color = "#000000";

    const form = document.querySelector('.personal_account form');
    form.style.backgroundColor = "#FFFFFF";
}

