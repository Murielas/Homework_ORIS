const form = document.querySelector('form');
const nameInput = form.querySelector('#username');
const passwordInput = form.querySelector('#password');

form.addEventListener('submit', (a) => {
    a.preventDefault();
    const name = nameInput.value;
    const password = passwordInput.value;
    let warning = document.getElementById("warning_message");

    if (!name || !password) {
        warning.textContent = "Не все поля заполнены";
        return;
    }

    if (!isValidName(name)) {
        warning.textContent = "Имя пользователя может содержать только латинские буквы, цифры и нижнее подчёркивание. Длина: 4-20 символов";
        return;
    }

    if (!isValidPassword(password)) {
         warning.textContent = "Пароль должен содержать как минимум одну заглавную букву, одну строчную букву и одну цифру. Длина: 6-12 символов";
         return;
    }

    form.submit();
    alert('Вы зашли в личный кабинет')
})

function isValidName(name) {
    const pattern = /^[a-zA-Z0-9_]{4,20}$/;
    return pattern.test(name);
}

function isValidPassword(password) {
    const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,12}$/;
    return pattern.test(password);
}