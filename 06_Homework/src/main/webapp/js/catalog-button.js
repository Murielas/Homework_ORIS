function openCreatingMenu() {
    let button = document.getElementById("create_button");
    button.style.visibility = "hidden";
    button.style.position = "absolute";
    let createMenu = document.getElementById("creating_menu");
    createMenu.style.visibility = "visible";
    createMenu.style.position = "static";
}

function openUpdatingMenu(productId) {
    let updateMenu = document.getElementById("updating_menu_" + productId);
    updateMenu.style.visibility = "visible";
    updateMenu.style.position = "static";
}