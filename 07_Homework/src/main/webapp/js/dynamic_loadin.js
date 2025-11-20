const advices = [
    "&#9999; Вымойте вазу, чтобы избавиться от бактерий, и наполните ее чистой прохладной водой. Добавьте в воду специальную подкормку из магазина цветов, чтобы продлить жизнь букету.",
    "&#9999; Поставьте букет в прохладное место, подальше от прямых солнечных лучей, источников тепла (например, батарей) и сквозняков. ",
    "&#9999; Ежедневно меняйте воду и подрезайте стебли цветов на 1-2 см, чтобы они продолжали получать влагу. Удаляйте увядшие цветы и листья из букета, чтобы они не заразили остальные растения. "
]

let adviceIndex = 0;

function nextAdvice() {
    if (adviceIndex < advices.length) {
        const container = document.getElementById('advices-container');
        const newAdvice = document.createElement('p');
        newAdvice.className = 'advice';
        newAdvice.innerHTML = advices[adviceIndex];
        container.appendChild(newAdvice);
        adviceIndex++;
    } else {
        document.querySelector('.button_advice').disabled = true;
        document.querySelector('.button_advice').textContent = 'Советы усё';
    }
}